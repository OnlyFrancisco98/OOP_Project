# Setup y Ejecución — Módulo `modulosolicitudes`

Instrucciones para ejecutar el servicio localmente.

## Requisitos
- Java 17+ (o la versión configurada en el `pom.xml`).
- Maven instalado (`mvn`).
- Una base de datos PostgreSQL accesible (el proyecto incluye configuración de ejemplo en `application.properties`).

## Configuración principal
- Revisa `src/main/resources/application.properties` para el puerto y la conexión a la BD. En este proyecto por defecto:

```
server.port=8081
spring.datasource.url=jdbc:postgresql://.../postgres
spring.datasource.username=...
spring.datasource.password=...
```

- Opcional: configurar correo en `src/main/resources/application-mail.properties` si deseas enviar correos reales.

## Compilar y ejecutar
Desde la carpeta `modulosolicitudes` ejecuta:

```powershell
mvn clean package -DskipTests
# o para desarrollo
mvn spring-boot:run
```

o ejecutar el JAR:

```powershell
java -jar target/modulosolicitudes-*.jar
```

El servicio arrancará en `http://localhost:8081` (según `server.port`).

## Probar endpoints
- Usa `curl`, Postman o el frontend en `src/main/resources/view/solicitud.html`.
- Endpoint de horarios: `GET http://localhost:8081/api/horarios/disponibles`.

## Notas de despliegue
- La propiedad `spring.jpa.hibernate.ddl-auto=update` está activada para facilitar desarrollo; para producción considerar `validate` o `none` y usar migraciones (Flyway/Liquibase).
- Asegúrate de no subir credenciales reales al repositorio (el repo actual contiene valores de ejemplo para pruebas académicas). Reemplaza por variables de entorno en despliegues reales.
