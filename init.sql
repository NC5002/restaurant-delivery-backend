-- init.sql
-- Crea la base de datos para el servicio de catálogo
CREATE DATABASE IF NOT EXISTS catalog_db;

-- Crea la base de datos para el servicio de órdenes
CREATE DATABASE IF NOT EXISTS order_db;

-- Opcional: Si tienes scripts para crear tablas o insertar datos iniciales,
-- podrías agregarlos aquí, asegurándote de usar la base de datos correcta.
-- Por ejemplo:
-- USE catalog_db;
-- CREATE TABLE IF NOT EXISTS products (id INT PRIMARY KEY, name VARCHAR(255));
-- INSERT INTO products (id, name) VALUES (1, 'Laptop');

-- USE order_db;
-- CREATE TABLE IF NOT EXISTS orders (id INT PRIMARY KEY, item VARCHAR(255));
-- INSERT INTO orders (id, name) VALUES (1, 'Order_A');