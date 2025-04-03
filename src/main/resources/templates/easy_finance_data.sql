drop database if exists easy_finance_data;

create database if not exists easy_finance_data;

use easy_finance_data;

CREATE TABLE IF NOT EXISTS period (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    initial_date TIMESTAMP NOT NULL,
    final_date TIMESTAMP NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active')
);

CREATE TABLE IF NOT EXISTS money_account_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(50) NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active')
);

CREATE TABLE IF NOT EXISTS money_account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(50) NOT NULL,
    balance DECIMAL(17,2) NOT NULL DEFAULT(0),
    debt DECIMAL(17,2) DEFAULT NULL,
    available_balance DECIMAL(17,2) DEFAULT NULL,
    max_balance DECIMAL(17,2) DEFAULT NULL,
    id_money_account_type BIGINT NOT NULL,
    id_owner BIGINT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active'),
    FOREIGN KEY (id_money_account_type) REFERENCES money_account_type(id),
    FOREIGN KEY (id_owner) REFERENCES easy_finance_security.user(id)
);

CREATE TABLE IF NOT EXISTS category_expense (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    description VARCHAR(50) NOT NULL,
    acronym CHAR(2) NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active')
);

CREATE TABLE IF NOT EXISTS budget (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    value DECIMAL(17, 2) NOT NULL,
    description VARCHAR(50) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    alert_percentage DECIMAL(3,2) DEFAULT(90) NOT NULL,
    id_category BIGINT NOT NULL,
    id_period BIGINT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active'),
    FOREIGN KEY (id_category) REFERENCES category_expense(id),
    FOREIGN KEY (id_period) REFERENCES period(id)
);

CREATE TABLE IF NOT EXISTS budget_has_responsible (
    id_budget BIGINT NOT NULL,
    id_account BIGINT NOT NULL,
    id_responsible BIGINT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active'),
    PRIMARY KEY (id_budget, id_account, id_responsible),
    FOREIGN KEY (id_budget) REFERENCES budget(id),
    FOREIGN KEY (id_account, id_responsible) REFERENCES easy_finance_security.account_has_user(id_account, id_user)
);

CREATE TABLE IF NOT EXISTS transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type ENUM('income','expense') NOT NULL,
    value DECIMAL(17, 2) NOT NULL,
    description VARCHAR(50) NOT NULL,
    date TIMESTAMP NOT NULL,
    currency VARCHAR(10) NOT NULL,
    id_period BIGINT NOT NULL,
    id_money_account BIGINT NOT NULL,
    id_category BIGINT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active'),
    FOREIGN KEY (id_category) REFERENCES category_expense(id),
    FOREIGN KEY (id_money_account) REFERENCES money_account(id),
    FOREIGN KEY (id_period) REFERENCES period(id)
);

CREATE TABLE IF NOT EXISTS transaction_has_responsible (
    id_transaction BIGINT NOT NULL,
    id_account BIGINT NOT NULL,
    id_responsible BIGINT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active'),
    PRIMARY KEY (id_transaction, id_account, id_responsible),
    FOREIGN KEY (id_transaction) REFERENCES transaction(id),
    FOREIGN KEY (id_account, id_responsible) REFERENCES easy_finance_security.account_has_user(id_account, id_user)
);

CREATE TABLE IF NOT EXISTS transaction_has_payer (
    id_transaction BIGINT NOT NULL,
    id_account BIGINT NOT NULL,
    id_payer BIGINT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active'),
    PRIMARY KEY (id_transaction, id_account, id_payer),
    FOREIGN KEY (id_transaction) REFERENCES transaction(id),
    FOREIGN KEY (id_account, id_payer) REFERENCES easy_finance_security.account_has_user(id_account, id_user)
);

CREATE TABLE IF NOT EXISTS debt (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    value DECIMAL(17, 2) NOT NULL,
    description VARCHAR(50) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    initial_date TIMESTAMP NOT NULL,
    final_date TIMESTAMP NOT NULL,
    id_account BIGINT NOT NULL,
    id_responsible BIGINT NOT NULL, /* OJO, ACA NO ES SOLO UN USUARIO, PUEDEN SER VARIOS */
    id_category BIGINT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active'),
    FOREIGN KEY (id_category) REFERENCES category_expense(id),
    FOREIGN KEY (id_account, id_responsible) REFERENCES easy_finance_security.account_has_user(id_account, id_user)
);

CREATE TABLE IF NOT EXISTS debt_has_responsible (
    id_debt BIGINT NOT NULL,
    id_account BIGINT NOT NULL,
    id_responsible BIGINT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active'),
    PRIMARY KEY (id_debt, id_account, id_responsible),
    FOREIGN KEY (id_debt) REFERENCES debt(id),
    FOREIGN KEY (id_account, id_responsible) REFERENCES easy_finance_security.account_has_user(id_account, id_user)
);

CREATE TABLE IF NOT EXISTS mutual_debt (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    value DECIMAL(17, 2) NOT NULL,
    description VARCHAR(50) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    id_transaction BIGINT NOT NULL,
    id_period BIGINT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active'),
    FOREIGN KEY (id_transaction) REFERENCES transaction(id),
    FOREIGN KEY (id_period) REFERENCES period(id)
);

CREATE TABLE IF NOT EXISTS mutual_debt_has_deptor (
    id_mutual_debt BIGINT NOT NULL,
    id_account BIGINT NOT NULL,
    id_deptor BIGINT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    state enum('active', 'inactive') DEFAULT('active'),
    PRIMARY KEY (id_mutual_debt, id_account, id_deptor),
    FOREIGN KEY (id_mutual_debt) REFERENCES mutual_debt(id),
    FOREIGN KEY (id_account, id_deptor) REFERENCES easy_finance_security.account_has_user(id_account, id_user)
);