# OrderAssigment

Este microservicio es responsable de gestionar la asignacion de ordenes a conductores, siempre y cuendo las ordenes esten creadas y los conductores activos, mediante una API REST construida con Spring Boot.

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

- Gestión de la asignacion con atributos: id, orderId, driverId, pdfPath, y imagePath.
- Creación de las asignaciones con el orderId y el driverId.
- Validaciones en los datos de entrada.
- Manejo de respuestas HTTP con códigos adecuados (200, 404, 500, etc.).
- Respuestas estandarizadas con objeto `Respuesta`.
- Integración con otros microservicios vía REST (planificado para comunicación con Assignment Service).

---

## 📑 API Endpoints

| Método | URL                        | Descripción                               | Parámetros                          | Request Body       | Response                  |
|--------|----------------------------|-------------------------------------------|-----------------------------------|--------------------|---------------------------|
| POST   | `/orderAssignment/asignarOrden`        | Asigna la orden creada a un conductor activo                      | -                                 | Params con `orderId` y `driverId` ademas de un archivo pdf y una imagen     | `Respuesta` indicando éxito o error |
| POST    | `/auth/login` | Regresa el token necesario para poder usar los puertos          | -                                 | JSON con `username` y `password` | `Respuesta` indicando éxito o error |

---

## 📄 Modelo de datos - Entidad Order

| Campo       | Tipo            | Descripción                    | Validaciones                   |
|-------------|-----------------|-------------------------------|-------------------------------|
| `id`        | `int`           | Identificador único de la asignacion   | Positivo, no nulo             |
| `orderId`    | `int`        | Id de la orden      | No nulo                      |
| `driverId` | `int`      | Id del coductor              | No nulo                      |
| `pdfPath` | `String` | path del archivo PDF              | No nulo                     |


---

## 🧪 Ejemplo de respuesta

### Crear orden (POST `/drivers/crearConductor`)

**Request JSON:**

```json
{
  "mensaje": "Asignación realizada correctamente",
  "estado": true,
  "objeto": {
    "id": 2,
    "orderId": 1,
    "driverId": 1,
    "pdfPath": "C:\\Users\\r2r11\\OneDrive\\Documentos\\ExamenTecnicoTag\\pdfsb882e7b1-aaf7-4b0c-9959-80879e399714_Examen de Evaluación - Backend V 1.0.pdf",
    "imagePath": "C:\\Users\\r2r11\\OneDrive\\Documentos\\ExamenTecnicoTag\\imagesbfe458d0-29d1-46bd-b7a0-372f55c3d8b6_Config-ApiGateway.png"
  },
  "fecha": "2025-08-03T17:48:14.3217933"
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

## Carpeta de guardado
Cambiar la ruta de guardado en el OrderAssigmentService a la ruta que sea conveniente para las pruebas


private final String UPLOAD_DIR = "C:\\Users\\r2r11\\OneDrive\\Documentos\\ExamenTecnicoTag";


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
