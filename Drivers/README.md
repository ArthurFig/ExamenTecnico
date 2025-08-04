# Drivers

Este microservicio es responsable de gestionar conductores de transporte, incluyendo la creación y consulta de los coductores mediante una API REST construida con Spring Boot.

---

## 📦 Tecnologías usadas

- Java 17+
- Spring Boot 2.7+
- Spring Data JPA (Hibernate)
- Base de datos relacional (Oracle, MySQL, PostgreSQL, etc.)
- Maven
- Lombok
- Jakarta Validation (Bean Validation)
- Spring Web (REST API)

---

## 🚀 Características principales

- Gestión de conductores con atributos: id, name, licenseNumber, y active.
- Endpoints para consultar conductores por distintos criterios: id, name, licenseNumber, y active.
- Creación y visualizacion de los conductores.
- Validaciones en los datos de entrada.
- Manejo de respuestas HTTP con códigos adecuados (200, 404, 500, etc.).
- Respuestas estandarizadas con objeto `Respuesta`.
- Integración con otros microservicios vía REST (planificado para comunicación con Assignment Service).

---

## 📑 API Endpoints

| Método | URL                        | Descripción                               | Parámetros                          | Request Body       | Response                  |
|--------|----------------------------|-------------------------------------------|-----------------------------------|--------------------|---------------------------|
| GET    | `/drivers/listarConductores`        | Obtiene una lista de conductores               |                         | -                  | `List<Conductor>` o 404             |
| GET    | `/drivers/conductorPorId`    | Obtiene Obtiene conductores por id                 | `id` (int)                 | -                  | Lista de órdenes          |
| POST   | `/drivers/crearConductor`        | Crea un nuevo conductor                       | -                                 | JSON con conductor      | `Respuesta` indicando éxito o error |
| POST    | `/auth/login` | Regresa el token necesario para poder usar los puertos          | -                                 | JSON con `username` y `password` | `Respuesta` indicando éxito o error |


---

## 📄 Modelo de datos - Entidad Order

| Campo       | Tipo            | Descripción                       | Validaciones                   |
|-------------|-----------------|-----------------------------------|-------------------------------|
| `id`        | `int`           | Identificador único del conductor | Positivo, no nulo             |
| `name`    | `String`        | Nombre del conductor              | No nulo                      |
| `licenseNumber` | `String`      | Numero de la licencia             | No nulo                      |
| `active` | `String` | Estado del conductor              | No nulo                     |


---

## 🧪 Ejemplo de petición y respuesta

### Crear orden (POST `/drivers/crearConductor`)

**Request JSON:**

```json
{
    "id": 1,
    "name": "Juan Pérez",
    "licenseNumber": "LIC123456",
    "active": "true"
  }
```

## 🧪 Body para la autenticacion

### Crear orden (POST `/auth/login`)

**Request JSON:**

```json
{
  "username": "user",
  "password": "12345"
}
```

## ⚙️ Propiedades (application.properties)
Cambiar los puertos segun las necesidades y configuraciones

```properties
spring.application.name=Drivers
server.port=9002

#Configuracion Eureka
eureka.client.service-url.defaultZone=http://localhost:9000/eureka
eureka.instance.prefer-ip-address=true
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.initial-instance-info-replication-interval-seconds=5
eureka.client.registry-fetch-interval-seconds=5

#Configuracion Oracle
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=System
spring.datasource.password=12345
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

#Configuracion fecha y hora
spring.jackson.time-zone=America/Mexico_City

#Spring security
spring.security.user.name=user
spring.security.user.password=12345

```
