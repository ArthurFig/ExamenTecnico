# 1. Usar una imagen base oficial de OpenJDK 21
FROM openjdk:21-slim

# 2. Argumento para la ruta del archivo JAR
ARG JAR_FILE=target/*.jar

# 3. Copiar el JAR al contenedor
COPY ${JAR_FILE} app.jar

# 4. Exponer el puerto de la aplicación
EXPOSE 9003

# 5. Definir el comando de inicio
ENTRYPOINT ["java", "-jar", "/app.jar"]