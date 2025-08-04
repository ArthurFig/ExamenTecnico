# Usar una imagen base oficial de OpenJDK 21
FROM openjdk:21-slim

# Argumento para la ruta del archivo JAR (opcional, pero útil para personalizar la construcción)
ARG JAR_FILE=target/*.jar

# Copiar el JAR al contenedor
COPY ${JAR_FILE} app.jar

# Exponer el puerto de la aplicación
EXPOSE 9001

# Definir el comando de inicio
ENTRYPOINT ["java", "-jar", "/app.jar"]