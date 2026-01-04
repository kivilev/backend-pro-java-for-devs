-- Insert Currencies (ISO-4217 codes)
-- USD: 840, EUR: 978, RUB: 643
INSERT INTO CURRENCY (currency_id, alfa3, description) VALUES (840, 'USD', 'US Dollar');
INSERT INTO CURRENCY (currency_id, alfa3, description) VALUES (978, 'EUR', 'Euro');
INSERT INTO CURRENCY (currency_id, alfa3, description) VALUES (643, 'RUB', 'Russian Ruble');

-- Insert Client Data Fields
INSERT INTO client_data_field (field_id, name, description) 
VALUES (1, 'FIRST_NAME', 'First name');

INSERT INTO client_data_field (field_id, name, description) 
VALUES (2, 'LAST_NAME', 'Last name');

INSERT INTO client_data_field (field_id, name, description) 
VALUES (3, 'BIRTHDAY', 'Birthday');

INSERT INTO client_data_field (field_id, name, description) 
VALUES (4, 'PASSPORT_SERIES', 'Passport series');

INSERT INTO client_data_field (field_id, name, description) 
VALUES (5, 'PASSPORT_NUMBER', 'Passport number');

INSERT INTO client_data_field (field_id, name, description) 
VALUES (8, 'MIDDLE_NAME', 'Middle name');

-- Insert Payment Data Fields
INSERT INTO payment_data_field (field_id, name, description) 
VALUES (1, 'CLIENT_SOFTWARE', 'Софт, через который совершался платеж');

INSERT INTO payment_data_field (field_id, name, description) 
VALUES (2, 'IP', 'IP адрес плательщика');

INSERT INTO payment_data_field (field_id, name, description) 
VALUES (3, 'NOTE', 'Примечание к переводу');

INSERT INTO payment_data_field (field_id, name, description) 
VALUES (4, 'IS_CHECKED_FRAUD', 'Проверен ли платеж в системе "АнтиФрод"');
