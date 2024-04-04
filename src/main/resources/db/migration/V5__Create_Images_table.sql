-- Создание таблицы связи изображений (images)
--
CREATE TABLE IF NOT EXISTS images
(
    image_id INTEGER NOT NULL, -- Идентификатор изображения
    link     VARCHAR(50) UNIQUE, -- Ссылка на изображение
    CONSTRAINT pk_images PRIMARY KEY (image_id) -- Ограничение PRIMARY KEY для поля image_id
);