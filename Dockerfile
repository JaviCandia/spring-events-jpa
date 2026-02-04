# Etapa de construcción
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias (capa de caché)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente y construir el jar
COPY src ./src
RUN mvn package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiar el jar generado desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto en el que corre la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
