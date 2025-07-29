# --- Etapa 1: Construcción (Build Stage) ---
# Usa una imagen de Maven que incluye Java 17 para compilar el proyecto.
# La nombramos "build" para poder referenciarla después.
FROM maven:3.9-amazoncorretto-17 AS build

# Establece el directorio de trabajo para la compilación.
WORKDIR /app

# Copia solo el pom.xml para descargar las dependencias.
# Esto aprovecha el cache de Docker: si no cambias las dependencias,
# este paso no se vuelve a ejecutar, haciendo el build más rápido.
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copia el resto del código fuente de tu aplicación.
COPY src ./src

# Compila la aplicación y crea el archivo JAR.
# Saltamos las pruebas porque ya se habrán ejecutado en un paso anterior del CI.
RUN mvn package -DskipTests


# --- Etapa 2: Ejecución (Final Stage) ---
# Usa una imagen ligera que solo contiene Java para ejecutar la aplicación.
# Esto hace que la imagen final sea mucho más pequeña y segura.
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo final.
WORKDIR /app

# Copia SOLAMENTE el archivo JAR resultante de la etapa 'build'.
# Asegúrate de que el artifactId en tu pom.xml sea 'catalog'.
COPY --from=build /app/target/catalog-*.jar app.jar

# Expone el puerto que usa la aplicación.
EXPOSE 8080

# Comando para correr la aplicación cuando el contenedor inicie.
ENTRYPOINT ["java","-jar","app.jar"]