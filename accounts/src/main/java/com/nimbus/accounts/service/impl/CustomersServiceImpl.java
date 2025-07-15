package com.nimbus.accounts.service.impl;

import com.nimbus.accounts.dto.AccountsDto;
import com.nimbus.accounts.dto.CardsDto;
import com.nimbus.accounts.dto.CustomerDetailsDto;
import com.nimbus.accounts.dto.LoansDto;
import com.nimbus.accounts.entity.Accounts;
import com.nimbus.accounts.entity.Customer;
import com.nimbus.accounts.exception.ResourceNotFoundException;
import com.nimbus.accounts.mapper.AccountsMapper;
import com.nimbus.accounts.mapper.CustomerMapper;
import com.nimbus.accounts.repository.AccountsRepository;
import com.nimbus.accounts.repository.CustomerRepository;
import com.nimbus.accounts.service.ICustomersService;
import com.nimbus.accounts.service.client.CardsFeignClient;
import com.nimbus.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}