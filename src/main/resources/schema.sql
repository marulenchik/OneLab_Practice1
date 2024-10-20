CREATE TABLE IF NOT EXISTS customers (
                           id BIGINT PRIMARY KEY,
                           name VARCHAR(255),
                           email VARCHAR(255),
                           password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS orders (
                        id BIGINT PRIMARY KEY,
                        order_number VARCHAR(255),
                        total_price INT,
                        customer_id BIGINT,
                        FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS products (
                          id BIGINT PRIMARY KEY,
                          name VARCHAR(255),
                          price DOUBLE
);


CREATE TABLE IF NOT EXISTS order_products (
                                order_id BIGINT,
                                product_id BIGINT,
                                PRIMARY KEY (order_id, product_id),
                                FOREIGN KEY (order_id) REFERENCES orders(id),
                                FOREIGN KEY (product_id) REFERENCES products(id)
);

