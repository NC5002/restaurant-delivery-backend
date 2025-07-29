# Usar una imagen base de OpenJDK para Java 17
FROM openjdk:17-jdk-slim

# Instalar curl para el healthcheck
# Es necesario actualizar los índices de paquetes y luego instalar curl
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el ejecutable Maven (JAR) generado de tu aplicación
# Asume que ya has construido el proyecto Maven (mvn clean package)
# Antes de ejecutar docker compose up, necesitas construir tus jars localmente
COPY target/*.jar app.jar

# Exponer el puerto en el que la aplicación Spring Boot se ejecutará
EXPOSE 8761

# Comando para ejecutar la aplicación cuando el contenedor se inicie
ENTRYPOINT ["java", "-jar", "app.jar"]