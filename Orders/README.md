# Orders

Este microservicio es responsable de gestionar órdenes de transporte, incluyendo la creación, actualización y consulta de órdenes mediante una API REST construida con Spring Boot.

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

- Gestión de órdenes con atributos: id, status, origen, destino, fecha de creación y actualización.
- Endpoints para consultar órdenes por distintos criterios: ID, estado, origen, destino, fecha de creación y actualización.
- Creación y actualización del estado de una orden.
- Validaciones en los datos de entrada.
- Manejo de respuestas HTTP con códigos adecuados (200, 404, 500, etc.).
- Respuestas estandarizadas con objeto `Respuesta`.
- Integración con otros microservicios vía REST (planificado para comunicación con Assignment Service).

---

## 📑 API Endpoints

| Método | URL                        | Descripción                               | Parámetros                          | Request Body                     | Response                  |
|--------|----------------------------|-------------------------------------------|-----------------------------------|----------------------------------|---------------------------|
| GET    | `/orders/ordenPorId`        | Obtiene una orden por su ID               | `Id` (int)                        | -                                | `Order` o 404             |
| GET    | `/orders/ordenPorStatus`    | Obtiene órdenes por estado                 | `status` (String)                 | -                                | Lista de órdenes          |
| GET    | `/orders/ordenPorOrigen`    | Obtiene órdenes por origen                 | `origen` (String)                 | -                                | Lista de órdenes          |
| GET    | `/orders/ordenPorDestino`   | Obtiene órdenes por destino                | `destino` (String)                | -                                | Lista de órdenes          |
| GET    | `/orders/ordenPorCreacion`  | Obtiene órdenes por fecha de creación      | `createdAt` (ISO 8601 DateTime)  | -                                | Lista de órdenes          |
| GET    | `/orders/ordenPorActualizacion` | Obtiene órdenes por fecha de actualización | `updatedAt` (ISO 8601 DateTime)  | -                                | Lista de órdenes          |
| POST   | `/orders/crearOrden`        | Crea una nueva orden                       | -                                 | JSON con orden                   | `Respuesta` indicando éxito o error |
| PUT    | `/orders/actualizarEstadoOrden` | Actualiza el estado de una orden          | -                                 | JSON con `id` y `status`         | `Respuesta` indicando éxito o error |
| POST    | `/auth/login` | Regresa el token necesario para poder usar los puertos          | -                                 | JSON con `username` y `password` | `Respuesta` indicando éxito o error |

---

## 📄 Modelo de datos - Entidad Order

| Campo       | Tipo            | Descripción                    | Validaciones                   |
|-------------|-----------------|-------------------------------|-------------------------------|
| `id`        | `int`           | Identificador único de orden   | Positivo, no nulo             |
| `status`    | `String`        | Estado actual de la orden      | No nulo                      |
| `origin`    | `String`        | Lugar de origen                | Opcional                     |
| `destination` | `String`      | Lugar de destino               | Opcional                     |
| `createdAt` | `LocalDateTime` | Fecha de creación              | Opcional                     |
| `updatedAt` | `LocalDateTime` | Fecha de última actualización  | Opcional                     |

---

## 🧪 Ejemplo de petición y respuesta

### Crear orden (POST `/orders/crearOrden`)

**Request JSON:**

```json
{
  "id": 1,
  "status": "CREATED",
  "origin": "CDMX",
  "destination": "GDL",
  "createdAt": "2025-08-04T16:00:00",
  "updatedAt": null
}
```

## 🧪 Body para la autenticacion

### Crear orden (POST `/auth/login`)

**Request JSON:**

```json
{
  "username": "user,
  "password": "12345"
}
```

## ⚙️ Propiedades (application.properties)
Cambiar los puertos segun las necesidades y configuraciones

```properties
spring.application.name=OrderAssignment
server.port=9003

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
