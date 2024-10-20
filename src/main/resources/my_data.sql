-- my_data.sql

-- Insert sample customers
INSERT INTO customers (id, name, email, password) VALUES
                                                      (1, 'John Doe', 'john.doe@example.com', 'password123'),
                                                      (2, 'Jane Smith', 'jane.smith@example.com', 'password456');

-- Insert sample products
INSERT INTO products (id, name, price) VALUES
                                           (1, 'Widget A', 19.99),
                                           (2, 'Widget B', 29.99),
                                           (3, 'Gadget X', 99.99),
                                           (4, 'Gadget Y', 149.99);

-- Insert sample orders
INSERT INTO orders (id, order_number, total_price, customer_id) VALUES
                                                                    (1, 'ORD1001', 49.98, 1), -- Order by John Doe
                                                                    (2, 'ORD1002', 249.98, 2); -- Order by Jane Smith

-- Associate products with orders (order_products)
INSERT INTO order_products (order_id, product_id) VALUES
                                                      (1, 1), -- Order 1 includes Widget A
                                                      (1, 2), -- Order 1 includes Widget B
                                                      (2, 3), -- Order 2 includes Gadget X
                                                      (2, 4); -- Order 2 includes Gadget Y
