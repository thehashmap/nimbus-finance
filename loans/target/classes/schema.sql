CREATE TABLE IF NOT EXISTS loans (
  loan_id SERIAL PRIMARY KEY,
  mobile_number varchar(15) NOT NULL,
  loan_number varchar(100) NOT NULL,
  loan_type varchar(100) NOT NULL,
  total_loan int NOT NULL,
  amount_paid int NOT NULL,
  outstanding_amount int NOT NULL,
  created_at date NOT NULL,
  created_by varchar(20) NOT NULL,
  updated_at date DEFAULT NULL,
  updated_by varchar(20) DEFAULT NULL
);