# El Proceso de Abstracción en el Sistema de Citas Psicológicas

En el contexto de este proyecto (Spring Boot + JPA), el proceso de **abstracción** consiste en ocultar la complejidad de las capas inferiores para exponer interfaces limpias, seguras y fáciles de usar en las capas superiores.

A continuación, se detalla cómo se ha aplicado este principio capa por capa:

---

## 1. Nivel de Datos: ORM (Object-Relational Mapping)

**¿Qué se abstrae (oculta)?**
Las tablas relacionales de SQL, las claves foráneas (FK), los `JOINs` complejos y las filas de la base de datos.

**¿Qué se expone?**
Objetos Java (`@Entity`) con relaciones orientadas a objetos.

* **Antes de la abstracción:** Para obtener el nombre de un paciente desde una cita, tendrías que pensar en SQL:
    `SELECT p.nombre FROM citas c JOIN pacientes p ON c.paciente_id = p.id WHERE...`
* **Tu Abstracción (`Cita.java`):**
    Hibernate convierte esa complejidad relacional en una navegación simple de objetos:
    ```java
    cita.getPaciente().getNombre();
    ```
    **Resultado:** Transformamos un problema de Base de Datos Relacional en un modelo de Programación Orientada a Objetos.

---

## 2. Nivel de Acceso a Datos: Repositorios

**¿Qué se abstrae (oculta)?**
La escritura manual de consultas SQL (`SELECT * FROM...`), el manejo de conexiones JDBC (`Connection`, `Statement`, `ResultSet`) y el manejo de errores de SQL.

**¿Qué se expone?**
Interfaces simples con métodos semánticos (`JpaRepository`).

* **Antes de la abstracción:** Tendrías que escribir cientos de líneas de código "boilerplate" para abrir conexiones, ejecutar queries y mapear resultados manualmente.
* **Tu Abstracción (`CitaRepository`):**
    Tú defines el *qué* quieres hacer, y Spring Data implementa el *cómo*:
    ```java
    // Tú declaras la intención, Spring genera el SQL automáticamente
    List<Cita> buscarPorEmailYNombreDePaciente(String email, String nombre);
    ```
    **Resultado:** Accedes a la base de datos como si fuera una simple colección en memoria, olvidándote del lenguaje SQL.

---

## 3. Nivel de Negocio: Servicios (Orquestación)

**¿Qué se abstrae (oculta)?**
La complejidad de las reglas de negocio, validaciones múltiples, transacciones y la coordinación entre distintas entidades.

**¿Qué se expone?**
Un único método público que realiza una operación atómica.

* **El detalle complejo:** Para agendar una cita correctamente, el sistema debe:
    1.  Validar si el horario existe.
    2.  Verificar si el horario está libre.
    3.  Buscar al paciente o crearlo si no existe.
    4.  Crear la cita.
    5.  Marcar el horario como ocupado.
    6.  Guardar todo o hacer *rollback* si algo falla.
* **Tu Abstracción (`CitaService`):**
    El Controlador no sabe nada de esta lógica. Solo ve y utiliza un método limpio:
    ```java
    citaService.agendarCita(request);
    ```
    **Resultado:** Encapsulamiento total de la lógica. Si las reglas de negocio cambian, el resto del sistema (Controladores/Frontend) no se ve afectado.

---

## 4. Nivel de API / Vista: DTOs (Data Transfer Objects)

**¿Qué se abstrae (oculta)?**
La estructura interna de la base de datos, los proxies de "Carga Perezosa" (`Lazy Loading`) de Hibernate, los interceptores (`ByteBuddyInterceptor`) y los bucles infinitos de referencias circulares.

**¿Qué se expone?**
Un contrato JSON limpio, plano y seguro (`Request` y `Response`).

* **El problema (Sin abstracción):** Exponer la Entidad `Cita` directamente al exterior provocaba errores `500 Internal Server Error` debido a que la herramienta de JSON (Jackson) no podía serializar los proxies complejos de Hibernate.
* **Tu Abstracción (`CitaResponse`):**
    Creamos una clase intermedia que actúa como el "Menú" para el cliente, separada del "Inventario" (Entidad).
    ```java
    public CitaResponse convertirACitaResponse(Cita cita) {
        // Traduce de la Entidad compleja al DTO simple
        // Elimina datos sensibles y resuelve proxies
        return dto;
    }
    ```
    **Resultado:** Desacoplamiento total entre la Base de Datos y la Interfaz de Usuario (Frontend). Esto permite cambiar la estructura interna de la BD sin romper la aplicación web, siempre que el DTO se mantenga igual.

---

## Resumen Visual de la Arquitectura

```mermaid
graph TD
    User["Usuario / Frontend"] -->|"JSON Limpio"| Controller["Controlador (API)"]
    Controller -->|DTO| Service["Servicio (Lógica)"]
    Service -->|Entidad| Repository["Repositorio (Datos)"]
    Repository -->|SQL| DB[("Base de Datos")]
