CREATE TABLE superschema.CUSTOMERS (
                           id serial PRIMARY KEY,
                           name varchar(30) NOT NULL,
                           surname varchar(30) NOT NULL,
                           age INTEGER,
                           phone_number varchar(15)
);

CREATE TABLE superschema.ORDERS (
                        id serial PRIMARY KEY,
                        date varchar(30) NOT NULL,
                        customer_id INTEGER NOT NULL,
                        product_name varchar(100) NOT NULL,
                        amount float NOT NULL,
                        FOREIGN KEY (customer_id) REFERENCES superschema.CUSTOMERS(id)
);
