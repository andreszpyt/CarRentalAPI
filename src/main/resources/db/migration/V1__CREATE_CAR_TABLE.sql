-- V1__CREATE_CAR_TABLE.sql
CREATE TABLE cars (
        id BIGSERIAL PRIMARY KEY,
        brand VARCHAR(100) NOT NULL,
        model VARCHAR(100) NOT NULL,
        category VARCHAR(50) NOT NULL,
        license_plate VARCHAR(20) UNIQUE NOT NULL,
        year_car INTEGER NOT NULL,
        color VARCHAR(30),
        daily_rate DECIMAL(10, 2) NOT NULL,
        available BOOLEAN DEFAULT TRUE
);