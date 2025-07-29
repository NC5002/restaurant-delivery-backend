**

# 🍔 Backend para Sistema de Delivery de Restaurante

Este repositorio contiene el backend para un sistema de gestión de delivery, desarrollado como parte del Prácticum 3. La aplicación está construida sobre una arquitectura de microservicios utilizando **Spring Boot** y **Java**, completamente contenerizada con **Docker** y automatizada con **GitHub Actions**.


# 📝 Descripción del Proyecto y Proceso de Creación

### Información del Estudiante

-   **Nombre:** Nicole Alexandra Calvas Echeverría
    
-   **Universidad:** UTPL
    
-   **Carrera:** Transformación Digital de Empresas
    

### Información de la Empresa

-   **Nombre de la Empresa:** Come en Casa
    
-   **Descripción:** Restaurante
    

### Mapa de Capacidades y Aplicación Objetivo

-   **Mapa de Capacidades:** El mapa de capacidades generado para la empresa se encuentra documentado en el `README.md` del [repositorio del frontend](https://github.com/NC5002/restaurant-delivery-app).
    
-   **Aplicación Objetivo:**
    
    > La aplicación objetivo es un sistema de gestión de delivery para el restaurante. Su propósito es digitalizar y optimizar el proceso de pedidos a domicilio, abordando directamente las capacidades de 'Gestión de Menú' y 'Procesamiento de Órdenes' identificadas en el mapa de capacidades. Esto permite a la empresa expandir su alcance, mejorar la eficiencia operativa y ofrecer una mejor experiencia al cliente.
    

### Proceso de Creación del Backend

El desarrollo de este backend se centró en construir una solución robusta, escalable y mantenible, siguiendo las mejores prácticas de la industria para sistemas distribuidos.

## 🏛️ Arquitectura de Microservicios

Se optó por una arquitectura de microservicios para desacoplar las responsabilidades del sistema, permitiendo un desarrollo y despliegue independiente de cada componente. La arquitectura consta de:

-   **Servidor de Descubrimiento (Discovery Service):** Implementado con _Spring Cloud Netflix Eureka_, actúa como el directorio telefónico del sistema, permitiendo que los servicios se encuentren dinámicamente.
    
-   **API Gateway (Gateway Service):** Construido con _Spring Cloud Gateway_, sirve como el único punto de entrada para todas las peticiones externas. Se encarga de enrutar el tráfico al microservicio correspondiente y de centralizar la seguridad. Se implementó un filtro de autenticación basado en _JSON Web Tokens (JWT)_ para proteger los endpoints.
    
-   **Servicio de Catálogo (Catalog Service):** Gestiona toda la lógica de negocio relacionada con los productos, incluyendo platillos (Dish) y categorías (Category). Expone una API REST para operaciones CRUD.
    
-   **Servicio de Órdenes (Order Service):** Diseñado para gestionar las órdenes de los clientes. Se implementó la comunicación entre servicios mediante un _cliente Feign_, permitiendo que el servicio de órdenes consulte la disponibilidad y el precio de los platillos en el servicio de catálogo antes de procesar un pedido.
## ⚙️ Integración Continua y Entrega Continua (CI/CD)

Para cumplir con los objetivos de aprendizaje de la unidad, se implementó un pipeline de CI/CD completo utilizando **GitHub Actions**.

-   **Integración Continua (CI):** Cada `push` a la rama `main` dispara un workflow que compila cada microservicio y ejecuta sus pruebas unitarias y de integración de forma automática. Esto asegura que nuevo código no introduzca regresiones.
    
-   **Entrega Continua (CD):** Si las pruebas son exitosas, el workflow procede a construir imágenes de Docker optimizadas para cada servicio (usando Dockerfiles multi-etapa) y las publica en **GitHub Container Registry (GHCR)**.
    

Este pipeline garantiza que siempre haya una versión estable y probada del software lista para ser desplegada.
## 🌐 Estado Actual y Conexión con Frontend

El backend está **100% funcional**. Todos los servicios se comunican correctamente, la seguridad está implementada y el pipeline de CI/CD automatiza el proceso de construcción y publicación.

El frontend, desarrollado en React, se encuentra en un repositorio separado. Aunque la conexión final entre ambos no está implementada en este proyecto, el backend expone una API REST completamente funcional y documentada, lista para ser consumida.

-   **Repositorio Frontend (React):** [https://github.com/NC5002/restaurant-delivery-app](https://github.com/NC5002/restaurant-delivery-app)
    

### ⚠️ Nota sobre el Despliegue en Railway

> Se intentó realizar el despliegue opcional en la plataforma Railway para obtener el punto extra. Se logró desplegar la base de datos MySQL, pero al intentar desplegar los microservicios desde las imágenes de Docker publicadas en GHCR, la opción no estaba disponible en la interfaz de usuario. El análisis sugiere que esto se debe a una restricción en las cuentas nuevas o no verificadas de Railway, que a menudo requieren la adición de un método de pago como medida de verificación de identidad para habilitar funcionalidades avanzadas como el despliegue de imágenes personalizadas. Por esta razón, no se pudo completar el despliegue y obtener una URL pública.

----------

## 🚀 Guía de Uso e Instalación Local

Este proyecto está completamente contenerizado, lo que garantiza que correrá en diferentes máquinas sin problemas. El único requisito es tener **Docker** y **Docker Compose** instalados.

### Paso 1: Clonar el Repositorio
Abre una terminal y clona este repositorio en tu máquina.

Bash

```
git clone https://github.com/NC5002/restaurant-delivery-backend.git
cd restaurant-delivery-backend

```

### Paso 2: Iniciar el Sistema

Ejecuta el siguiente comando desde la raíz del proyecto. Docker Compose leerá el archivo `docker-compose.yml`, descargará las imágenes de los microservicios desde GitHub Container Registry e iniciará todo el sistema.

Bash

```
docker-compose up -d

```

Espera uno o dos minutos para que todos los servicios arranquen y se registren en Eureka.

### Paso 3: Verificar que Todo Funciona

Puedes verificar que el sistema está corriendo de varias maneras:

-   **Ver los contenedores activos:**
    
    Bash
    
    ```
    docker ps
    
    ```
    
    Deberías ver 5 contenedores corriendo: `mysql`, `discovery`, `gateway`, `catalog` y `order`.
    
-   **Revisar el Dashboard de Eureka:** Abre tu navegador y ve a `http://localhost:8761`. Deberías ver los servicios `CATALOG`, `ORDER` y `GATEWAY` registrados y con estado `UP`.
    

### Paso 4: Probar la API con Postman

Ahora puedes interactuar con la API a través del Gateway.

**A. Obtener un Token de Autenticación** Primero, necesitas un token para acceder a los endpoints protegidos.

-   **Método:** `POST`
    
-   **URL:** `http://localhost:8080/auth/login`
    
-   **Resultado:** Recibirás un token JWT. Copia este token.
    

**B. Crear una Categoría**

-   **Método:** `POST`
    
-   **URL:** `http://localhost:8080/catalog/api/v1/categories`
    
-   **Authorization:** En la pestaña `Authorization`, selecciona `Bearer Token` y pega el token que copiaste.
    
-   **Body:** (`raw` -> `JSON`)
    
    JSON
    
    ```
    {
        "name": "Bebidas"
    }
    
    ```
    
-   **Resultado:** Un `201 Created` con la nueva categoría.
    

**C. Listar Categorías**

-   **Método:** `GET`
    
-   **URL:** `http://localhost:8080/catalog/api/v1/categories`
    
-   **Authorization:** `Bearer Token` con tu token.
    
-   **Resultado:** Una lista con la categoría "Bebidas" que acabas de crear.
    

¡Con esto, has verificado que todo el flujo, desde el Gateway hasta la base de datos, funciona correctamente!
