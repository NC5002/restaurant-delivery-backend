-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS restaurant_delivery_db;
USE restaurant_delivery_db;

-- Tabla de Usuarios con soporte para inicio de sesión con Google
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    -- La contraseña es opcional para permitir el inicio de sesión con Google
    password VARCHAR(255) NULL,
    -- Campo para almacenar el ID único de Google del usuario
    google_id VARCHAR(255) NULL UNIQUE,
    phone_number VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Restaurantes (diseñada para contener una sola fila con los datos del local)
CREATE TABLE restaurants (
    restaurant_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    cuisine_type VARCHAR(50),
    rating DECIMAL(3, 2) DEFAULT 0.00,
    image_url VARCHAR(255)
);

-- Tabla de Categorías de Comida
CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Tabla de Platillos
CREATE TABLE dishes (
    dish_id INT AUTO_INCREMENT PRIMARY KEY,
    -- FK al único restaurante en la tabla 'restaurants'
    restaurant_id INT,
    category_id INT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    image_url VARCHAR(255),
    -- Indica si el platillo está disponible o no
    is_available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- Tabla de Pedidos (sin tabla de direcciones separada)
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    -- FK al único restaurante en la tabla 'restaurants'
    restaurant_id INT,
    -- La dirección de entrega se almacena directamente en el pedido
    delivery_address TEXT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    -- Estados: 'pending', 'confirmed', 'on_the_way', 'delivered', 'cancelled'
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id)
);

-- Tabla de Detalles del Pedido (los platillos de cada pedido)
CREATE TABLE order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    dish_id INT,
    quantity INT NOT NULL,
    -- Se guarda el precio al momento de la compra para mantener un histórico correcto
    price_per_unit DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (dish_id) REFERENCES dishes(dish_id)
);