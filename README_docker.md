# 🐳 Dockerización de 4 Microservicios Spring Boot

Este repositorio contiene la configuración necesaria para construir y ejecutar 4 microservicios desarrollados en Spring Boot, completamente dockerizados, listos para ejecución local o despliegue.

---


Cada microservicio tiene su propio `pom.xml`, configuración `application.properties` y clase principal con `@SpringBootApplication`.

---

## 🏗️ Paso 1: Compilar y generar los `.jar` de los microservicios

Desde la raíz de cada microservicio, ejecuta:

```bash
mvn clean package 
```

## Crear DockerFile 
Se creara un dockerfile basico siguiendo la configuracion del .jar

```bash
FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

```



## Installation

Comandos utilizados para la creacion de imagenes y contenedores

### Creacion de imagenes
Se puede crear la imagen con los siguientes nombres
```bash
  docker build -t imagen-orders .
  docker build -t imagen-drivers .
  docker build -t imagen-oa .
```

| Microservicio      | Puerto externo | Puerto interno | Descripción            |
| ------------------ | -------------- | -------------- | ---------------------- |
| Orders Service     | 9001           | 9001           | Gestión de órdenes     |
| Drivers Service    | 9002           | 9002           | Gestión de conductores |
| Assignment Service | 9003           | 9003           | Asignación de órdenes  |

## 📦 Oracle XE en Docker para Microservicios Spring Boot

Este proyecto utiliza una instancia de Oracle Database Express Edition (XE) ejecutándose en un contenedor Docker, ideal para entornos de desarrollo y pruebas con aplicaciones Spring Boot.

---

## 🐳 Configuración de la Base de Datos en Docker y Microservicios

Se utilizó la imagen pública `gvenzl/oracle-xe`, que es ligera y lista para usar sin necesidad de aceptar licencias de Oracle manualmente.
Ademas se agrego la ejecucion de los 4 microservicios 

### ✅ `docker-compose.yml`

```yaml
version: "3.8"

services:
  oracle-db:
    image: gvenzl/oracle-xe
    container_name: oracle-xe
    ports:
      - "9005:1521"
      - "9006:8080"
    environment:
      ORACLE_PASSWORD: 12345
    restart: unless-stopped
    networks:
      - backend

  orders-service:
    image: imagen-orders
    container_name: orders-service
    ports:
      - "9001:9001"
    depends_on:
      - oracle-db
    environment:
      SPRING_DATASOURCE_URL: jdbc:oracle:thin:@oracle-db:1521/XEPDB1
      SPRING_DATASOURCE_USERNAME: system
      SPRING_DATASOURCE_PASSWORD: 12345
    networks:
      - backend

  drivers-service:
    image: imagen-drivers
    container_name: drivers-service
    ports:
      - "9002:9002"
    depends_on:
      - oracle-db
    environment:
      SPRING_DATASOURCE_URL: jdbc:oracle:thin:@oracle-db:1521/XEPDB1
      SPRING_DATASOURCE_USERNAME: system
      SPRING_DATASOURCE_PASSWORD: 12345
    networks:
      - backend

  assignment-service:
    image: imagen-oa
    container_name: oa-service
    ports:
      - "9003:9003"
    depends_on:
      - oracle-db
    environment:
      SPRING_DATASOURCE_URL: jdbc:oracle:thin:@oracle-db:1521/XEPDB1
      SPRING_DATASOURCE_USERNAME: system
      SPRING_DATASOURCE_PASSWORD: 12345
    networks:
      - backend

networks:
  backend:
    driver: bridge

```

### Generar los contenedores de todos los microservicios

```bash
docker-compose up -d
```

### Conectar a un gestor de BD compatible con Oracle

Esto facilitara la creacion de las tablas y del contenido prueba, al mismo tiempo que podremos comprobar la conexion a la BD

### Importar y ejecutar el script SQL en el contenedor Oracle

Para crear la base de datos y las tablas necesarias, se debe ejecutar un script SQL dentro del contenedor Oracle.

#### 1. Copiar el script al contenedor

Usa el siguiente comando para copiar el archivo `Script-BD.sql` desde tu máquina local al contenedor `oracle-xe`:

```bash
docker cp Script-BD.sql oracle-xe:/Script-BD.sql
```

#### 2. Ejecutar el script dentro del contenedor

```bash
docker exec -it oracle-xe bash
```
Luego, dentro del contenedor, ejecuta el cliente sqlplus con el usuario system y la contraseña configurada (12345), y carga el script:

```bash
sqlplus system/12345 @/Script-BD.sql
```

#### 3. Confirmar ejecución exitosa
Si el script se ejecuta sin errores, la base de datos y tablas se crearán correctamente. Puedes verificar tablas existentes con:

```bash
SELECT name FROM table_name;
```

#### 3. Confirmar ejecución exitosa

```bash
docker exec -i oracle-xe sqlplus system/12345 @- < Script-BD.sql
```


# 📄 Consulta de Archivos PDF e Imágenes - Microservicio OrderAssignment

Este documento explica cómo acceder a los archivos **PDF** e **imágenes** que genera el microservicio `order-assignment` dentro de un contenedor Docker.

---

## 📁 Ruta de almacenamiento dentro del contenedor

Los archivos generados se almacenan internamente en las siguientes rutas:

- **PDFs:** `/app/uploads/pdfs/`
- **Imágenes:** `/app/uploads/images/`

Ejemplo de nombre de archivo generado:

/app/uploads/pdfs/a5300425-6997-49fb-bd3d-0643eef2b59a_Examen de Evaluación - Backend V 1.0.pdf


---

## ✅ Acceder a los archivos desde Docker

### 1. Ingresar al contenedor

Ejecuta el siguiente comando para ingresar al contenedor `oa-service` (o el nombre que hayas definido):

```bash
docker exec -it oa-service sh
```
Si falla intenta con bash

