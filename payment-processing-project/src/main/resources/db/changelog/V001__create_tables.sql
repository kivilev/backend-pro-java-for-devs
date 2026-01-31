-- Sequences
CREATE SEQUENCE client_id_seq START WITH 1 INCREMENT BY 5;
CREATE SEQUENCE client_data_id_seq START WITH 1 INCREMENT BY 5;
CREATE SEQUENCE account_id_seq START WITH 1 INCREMENT BY 5;
CREATE SEQUENCE payment_id_seq START WITH 1 INCREMENT BY 5;
CREATE SEQUENCE payment_detail_id_seq START WITH 1 INCREMENT BY 5;

-- Currency Table
CREATE TABLE CURRENCY (
    currency_id INTEGER NOT NULL,
    alfa3 VARCHAR(3) NOT NULL,
    description VARCHAR(100) NOT NULL,
    CONSTRAINT CURRENCY_PK PRIMARY KEY (currency_id),
    CONSTRAINT CURRENCY_alfa3_CHK CHECK (alfa3 = UPPER(alfa3))
);

CREATE UNIQUE INDEX currency_alfa3_UNQ ON CURRENCY(alfa3);

-- ClientDataField Table
CREATE TABLE client_data_field (
    field_id INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(200) NOT NULL,
    CONSTRAINT client_data_field_PK PRIMARY KEY (field_id),
    CONSTRAINT client_data_field_name_UNQ UNIQUE (name),
    CONSTRAINT client_data_field_name_CHK CHECK (name = upper(name))
);

-- Client Table
CREATE TABLE client (
    id BIGINT NOT NULL DEFAULT nextval('client_id_seq'),
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    status INTEGER NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT client_PK PRIMARY KEY (id),
    CONSTRAINT client_email_UNQ UNIQUE (email)
);

CREATE INDEX client_status_I ON client(status);

-- ClientData Table
CREATE TABLE client_data (
    id BIGINT NOT NULL DEFAULT nextval('client_data_id_seq'),
    client_id BIGINT NOT NULL,
    field_id INTEGER NOT NULL,
    field_value VARCHAR(500) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT client_data_PK PRIMARY KEY (id),
    CONSTRAINT fk_client_data_client FOREIGN KEY (client_id) 
        REFERENCES client(id) ON DELETE CASCADE,
    CONSTRAINT fk_client_data_field FOREIGN KEY (field_id) 
        REFERENCES client_data_field(field_id),
    CONSTRAINT client_data_client_id_field_id_UNQ UNIQUE (client_id, field_id)
);

CREATE INDEX client_data_field_id_I ON client_data(field_id);

-- Account Table
CREATE TABLE account (
    id BIGINT NOT NULL DEFAULT nextval('account_id_seq'),
    client_id BIGINT NOT NULL,
    account_number VARCHAR(36) NOT NULL,
    currency_id INTEGER NOT NULL,
    balance DECIMAL(19,2) NOT NULL,
    status INTEGER NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT account_PK PRIMARY KEY (id),
    CONSTRAINT fk_account_client FOREIGN KEY (client_id) 
        REFERENCES client(id) ON DELETE CASCADE,
    CONSTRAINT fk_account_currency FOREIGN KEY (currency_id) 
        REFERENCES CURRENCY(currency_id),
    CONSTRAINT account_client_id_currency_id_UNQ UNIQUE (client_id, currency_id),
    CONSTRAINT account_account_number_UNQ UNIQUE (account_number)
);

CREATE INDEX account_client_id_I ON account(client_id);

-- Payment Table
CREATE TABLE payment (
    id BIGINT NOT NULL DEFAULT nextval('payment_id_seq'),
    payment_date TIMESTAMPTZ NOT NULL,
    source_account_id BIGINT NOT NULL,
    target_account_id BIGINT NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    currency_code VARCHAR(3) NOT NULL,
    status INTEGER NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    processed_at TIMESTAMPTZ,
    CONSTRAINT payment_PK PRIMARY KEY (id),
    CONSTRAINT fk_payment_source_account FOREIGN KEY (source_account_id) 
        REFERENCES account(id),
    CONSTRAINT fk_payment_target_account FOREIGN KEY (target_account_id) 
        REFERENCES account(id)
);

CREATE INDEX payment_source_account_id_I ON payment(source_account_id);
CREATE INDEX payment_target_account_id_I ON payment(target_account_id);
CREATE INDEX payment_status_I ON payment(status);
CREATE INDEX payment_payment_date_I ON payment(payment_date);

-- PaymentDataField Table
CREATE TABLE payment_data_field (
    field_id INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(200) NOT NULL,
    CONSTRAINT payment_data_field_PK PRIMARY KEY (field_id),
    CONSTRAINT payment_data_field_name_UNQ UNIQUE (name),
    CONSTRAINT payment_data_field_name_CHK CHECK (name = upper(name))
);

-- PaymentDetail Table
CREATE TABLE payment_detail (
    id BIGINT NOT NULL DEFAULT nextval('payment_detail_id_seq'),
    payment_id BIGINT NOT NULL,
    field_id INTEGER NOT NULL,
    field_value VARCHAR(500) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    CONSTRAINT payment_detail_PK PRIMARY KEY (id),
    CONSTRAINT fk_payment_detail_payment FOREIGN KEY (payment_id) 
        REFERENCES payment(id) ON DELETE CASCADE,
    CONSTRAINT fk_payment_detail_field FOREIGN KEY (field_id) 
        REFERENCES payment_data_field(field_id),
    CONSTRAINT payment_detail_payment_id_field_id_UNQ UNIQUE (payment_id, field_id)
);

CREATE INDEX payment_detail_field_id_I ON payment_detail(field_id);

-- Constraints
ALTER TABLE account 
ADD CONSTRAINT account_balance_check CHECK (balance >= 0);

ALTER TABLE payment 
ADD CONSTRAINT payment_amount_check CHECK (amount > 0);

-- Комментарии к таблицам
COMMENT ON TABLE CURRENCY IS 'Справочник валют';
COMMENT ON TABLE client_data_field IS 'Справочник полей данных клиента';
COMMENT ON TABLE client IS 'Клиенты системы';
COMMENT ON TABLE client_data IS 'Дополнительные данные клиентов';
COMMENT ON TABLE account IS 'Банковские счета клиентов';
COMMENT ON TABLE payment IS 'Платежи между счетами';
COMMENT ON TABLE payment_data_field IS 'Справочник полей данных платежа';
COMMENT ON TABLE payment_detail IS 'Дополнительные данные платежей';

-- Комментарии к колонкам таблицы CURRENCY
COMMENT ON COLUMN CURRENCY.currency_id IS 'Идентификатор валюты';
COMMENT ON COLUMN CURRENCY.alfa3 IS 'Трехбуквенный код валюты (ISO 4217)';
COMMENT ON COLUMN CURRENCY.description IS 'Описание валюты';

-- Комментарии к колонкам таблицы client_data_field
COMMENT ON COLUMN client_data_field.field_id IS 'Идентификатор поля';
COMMENT ON COLUMN client_data_field.name IS 'Название поля (в верхнем регистре)';
COMMENT ON COLUMN client_data_field.description IS 'Описание поля';

-- Комментарии к колонкам таблицы client
COMMENT ON COLUMN client.id IS 'Идентификатор клиента';
COMMENT ON COLUMN client.email IS 'Электронная почта клиента';
COMMENT ON COLUMN client.phone_number IS 'Номер телефона клиента';
COMMENT ON COLUMN client.status IS 'Статус клиента. Значения: 0=INACTIVE (Неактивен), 1=ACTIVE (Активен), 2=BLOCKED (Заблокирован)';
COMMENT ON COLUMN client.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN client.updated_at IS 'Дата и время последнего обновления записи';

-- Комментарии к колонкам таблицы client_data
COMMENT ON COLUMN client_data.id IS 'Идентификатор записи данных клиента';
COMMENT ON COLUMN client_data.client_id IS 'Идентификатор клиента';
COMMENT ON COLUMN client_data.field_id IS 'Идентификатор поля данных';
COMMENT ON COLUMN client_data.field_value IS 'Значение поля данных';
COMMENT ON COLUMN client_data.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN client_data.updated_at IS 'Дата и время последнего обновления записи';

-- Комментарии к колонкам таблицы account
COMMENT ON COLUMN account.id IS 'Идентификатор счета';
COMMENT ON COLUMN account.client_id IS 'Идентификатор клиента-владельца счета';
COMMENT ON COLUMN account.account_number IS 'Номер счета (UUID)';
COMMENT ON COLUMN account.currency_id IS 'Идентификатор валюты счета';
COMMENT ON COLUMN account.balance IS 'Баланс счета';
COMMENT ON COLUMN account.status IS 'Статус счета. Значения: 0=BLOCKED (Заблокирован), 1=ACTIVE (Активен), 2=CLOSED (Закрыт)';
COMMENT ON COLUMN account.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN account.updated_at IS 'Дата и время последнего обновления записи';

-- Комментарии к колонкам таблицы payment
COMMENT ON COLUMN payment.id IS 'Идентификатор платежа';
COMMENT ON COLUMN payment.payment_date IS 'Дата и время платежа';
COMMENT ON COLUMN payment.source_account_id IS 'Идентификатор счета отправителя';
COMMENT ON COLUMN payment.target_account_id IS 'Идентификатор счета получателя';
COMMENT ON COLUMN payment.amount IS 'Сумма платежа';
COMMENT ON COLUMN payment.currency_code IS 'Код валюты платежа';
COMMENT ON COLUMN payment.status IS 'Статус платежа. Значения: 0=PENDING (Ожидает обработки), 1=COMPLETED (Завершен), 2=FAILED (Ошибка), 3=CANCELLED (Отменен)';
COMMENT ON COLUMN payment.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN payment.updated_at IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN payment.processed_at IS 'Дата и время обработки платежа';

-- Комментарии к колонкам таблицы payment_data_field
COMMENT ON COLUMN payment_data_field.field_id IS 'Идентификатор поля';
COMMENT ON COLUMN payment_data_field.name IS 'Название поля (в верхнем регистре)';
COMMENT ON COLUMN payment_data_field.description IS 'Описание поля';

-- Комментарии к колонкам таблицы payment_detail
COMMENT ON COLUMN payment_detail.id IS 'Идентификатор записи деталей платежа';
COMMENT ON COLUMN payment_detail.payment_id IS 'Идентификатор платежа';
COMMENT ON COLUMN payment_detail.field_id IS 'Идентификатор поля данных';
COMMENT ON COLUMN payment_detail.field_value IS 'Значение поля данных';
COMMENT ON COLUMN payment_detail.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN payment_detail.updated_at IS 'Дата и время последнего обновления записи';

