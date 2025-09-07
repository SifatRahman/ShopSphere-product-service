CREATE TABLE [dbo].[product] (
    id NVARCHAR(128) NOT NULL PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    description NVARCHAR(MAX) NULL,
    price DECIMAL(18,4) NOT NULL,
    image_url NVARCHAR(255) NOT NULL,
    discount_price DECIMAL(18,4) NULL,
    [total_price] AS ([price]-[discount_price]),
    stock_quantity INT NOT NULL,
    category_id NVARCHAR(128) NULL,
    brand_id NVARCHAR(128) NULL,
    status NVARCHAR(255) NOT NULL,
    created_at DATETIME2 NOT NULL,
    updated_at DATETIME2 NULL
);