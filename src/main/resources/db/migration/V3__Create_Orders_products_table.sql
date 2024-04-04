-- Создание таблицы связи order_product для связи между заказами и продуктами
--
CREATE TABLE orders_products
(
    order_id   INTEGER REFERENCES orders (order_id),  -- Идентификатор заказа, ссылается на таблицу orders
    product_id INTEGER REFERENCES products (product_id),  -- Идентификатор продукта, ссылается на таблицу products
    CONSTRAINT orders_products_pk PRIMARY KEY (order_id, product_id)  -- Определение составного первичного ключа
);
