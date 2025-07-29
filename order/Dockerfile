# --- Etapa 1: Construcción (Build Stage) ---
FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# --- Etapa 2: Ejecución (Final Stage) ---
FROM openjdk:17-jdk-slim
WORKDIR /app
# CAMBIO: Copia el JAR del servicio 'order'
COPY --from=build /app/target/order-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]