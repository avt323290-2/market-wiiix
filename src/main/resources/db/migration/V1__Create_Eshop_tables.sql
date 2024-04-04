-- ROLES: таблица ролей пользователей
CREATE TABLE IF NOT EXISTS roles
(
    role_id SERIAL      NOT NULL,     -- Уникальный идентификатор роли
    name    varchar(20) NOT NULL,     -- Наименование роли
    PRIMARY KEY (role_id)             -- Определение первичного ключа
    );

-- USERS: таблица пользователей
CREATE TABLE IF NOT EXISTS users
(
    user_id    SERIAL,                -- Уникальный идентификатор пользователя
    name       text NOT NULL UNIQUE,  -- Имя пользователя (уникальное)
    password   text NOT NULL UNIQUE,  -- Пароль пользователя (уникальный)
    email      text NOT NULL UNIQUE,  -- Электронная почта пользователя (уникальная)
    birth_date date NOT NULL,         -- Дата рождения пользователя
    balance    NUMERIC(10, 2) DEFAULT '0.00', -- Баланс пользователя (по умолчанию 0)
    role_id INTEGER,                  -- Идентификатор роли пользователя
    PRIMARY KEY (user_id),            -- Определение первичного ключа
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES roles (role_id) -- Ограничение внешнего ключа
    );

-- CATEGORIES: таблица категорий продуктов
CREATE TABLE IF NOT EXISTS categories
(
    category_id SERIAL NOT NULL,      -- Уникальный идентификатор категории
    name        text   NOT NULL UNIQUE, -- Наименование категории (уникальное)
    rating      int4 DEFAULT 1,       -- Рейтинг категории (по умолчанию 1)
    PRIMARY KEY (category_id),        -- Определение первичного ключа
    CONSTRAINT high_rating CHECK (rating <= 5) -- Ограничение проверки высокого рейтинга
    );

-- PRODUCTS: таблица продуктов
CREATE TABLE IF NOT EXISTS products
(
    product_id  SERIAL NOT NULL,      -- Уникальный идентификатор продукта
    name        text   NOT NULL,      -- Наименование продукта
    description TEXT,                  -- Описание продукта
    price       double precision,     -- Цена продукта
    category_id INTEGER,              -- Идентификатор категории продукта
    PRIMARY KEY (product_id),         -- Определение первичного ключа
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories (category_id) -- Ограничение внешнего ключа
    );

-- ORDERS: таблица заказов
CREATE TABLE IF NOT EXISTS orders
(
    order_id   SERIAL    NOT NULL,    -- Уникальный идентификатор заказа
    price      double precision,      -- Стоимость заказа
    created_at TIMESTAMP NOT NULL,    -- Время создания заказа
    user_id    INTEGER,               -- Идентификатор пользователя (владельца заказа)
    PRIMARY KEY (order_id),           -- Определение первичного ключа
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id) -- Ограничение внешнего ключа
    );

-- CARTS: таблица корзин
CREATE TABLE IF NOT EXISTS carts
(
    cart_id    SERIAL    NOT NULL,    -- Уникальный идентификатор корзины
    price      double precision,      -- Стоимость корзины
    created_at TIMESTAMP NOT NULL,    -- Время создания корзины
    user_id    INTEGER,               -- Идентификатор пользователя (владельца корзины)
    PRIMARY KEY (cart_id),            -- Определение первичного ключа
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id) -- Ограничение внешнего ключа
    );