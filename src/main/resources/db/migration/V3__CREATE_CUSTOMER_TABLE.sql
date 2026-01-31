CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    driver_license VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL
);