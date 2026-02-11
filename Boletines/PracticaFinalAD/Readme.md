# PracticaFinalAD - Práctica Final para AD

Este repositorio contiene una colección de aplicaciones Spring Boot desarrolladas para el curso de "Acceso a Datos". Estas aplicaciones demuestran la interacción con bases de datos PostgreSQL y MongoDB, exponen APIs RESTful e incluyen un cargador de datos basado en consola.

## Estructura del Proyecto

El repositorio está organizado en tres proyectos principales de Spring Boot:

1.  **Executor4000**: Una aplicación de consola diseñada para una ejecución única, probablemente para la carga inicial de datos o configuración en una base de datos PostgreSQL.
2.  **JoJosGres**: Una aplicación web Spring Boot que expone APIs RESTful para gestionar datos de "Personaxes" (personajes) y "Series", interactuando con una base de datos PostgreSQL.
3.  **MongoChamador**: Una aplicación web Spring Boot que expone APIs RESTful para gestionar datos de "Personaxes" y "Series", interactuando con una base de datos MongoDB. También incluye funcionalidad para cargar datos desde archivos JSON.

## Proyectos Individuales

### 1. Executor4000

*   **Descripción**: Esta es una aplicación Spring Boot basada en consola. Está configurada para realizar una secuencia de operaciones y luego terminar. Basado en la estructura de archivos, probablemente interactúa con una base de datos PostgreSQL para administrar entidades `Personaxe` y `Series`, posiblemente para una población inicial de datos o un procesamiento por lotes específico.
*   **Flujo de Datos**:
    1.  La aplicación se inicia y `Main.java` ejecuta la lógica definida en `Secuencia.java`.
    2.  `Secuencia.java` orquesta las operaciones, que pueden incluir el uso de `PersonaxeService` y `SeriesService`.
    3.  Los servicios interactúan con `PersonaxeRepository` y `SeriesRepository` para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la base de datos PostgreSQL.
    4.  Los modelos (`Personaxe.java`, `Series.java`) representan la estructura de los datos en la base de datos.
*   **Base de Datos**: PostgreSQL
*   **Puerto**: No aplicable para una aplicación de consola, pero comparte la configuración de la base de datos con JoJosGres.
*   **Cómo Ejecutar**:
    1.  Asegúrate de tener una base de datos PostgreSQL en ejecución y accesible con las credenciales especificadas en `src/main/resources/application.properties`.
    2.  Navega al directorio `Executor4000`.
    3.  Compila el proyecto usando Maven: `mvn clean install`
    4.  Ejecuta la aplicación: `mvn spring-boot:run` (o ejecuta el archivo JAR generado). La aplicación realizará sus tareas y luego saldrá.

### 2. JoJosGres

*   **Descripción**: Una aplicación web Spring Boot que proporciona APIs RESTful. Gestiona datos de `Personaxe` y `Series`, persistiéndolos en una base de datos PostgreSQL. Incluye documentación OpenAPI para sus endpoints.
*   **Flujo de Datos**:
    1.  Las solicitudes HTTP entrantes son recibidas por los `Controller`s (`PersonaxesController`, `SeriesController`).
    2.  Los `Controller`s delegan la lógica de negocio a los `Service`s (`PersonaxeService`, `SeriesService`).
    3.  Los `Service`s interactúan con los `Repository`s (`PersonaxeRepository`, `SeriesRepository`) para realizar operaciones CRUD en la base de datos PostgreSQL.
    4.  Los `Model`os (`Personaxe.java`, `Series.java`) son utilizados para mapear los datos entre la base de datos y la aplicación, así como para las respuestas/solicitudes de la API.
*   **Base de Datos**: PostgreSQL
*   **Puerto**: `8081`
*   **Documentación de la API**: Accesible a través de Swagger UI en `http://localhost:8081/swagger-ui.html` (después de que la aplicación esté en ejecución).
*   **Cómo Ejecutar**:
    1.  Asegúrate de tener una base de datos PostgreSQL en ejecución y accesible con las credenciales especificadas en `src/main/resources/application.properties`.
    2.  Navega al directorio `JoJosGres`.
    3.  Compila el proyecto usando Maven: `mvn clean install`
    4.  Ejecuta la aplicación: `mvn spring-boot:run`
    5.  Accede a la documentación de la API en `http://localhost:8081/swagger-ui.html`.

### 3. MongoChamador

*   **Descripción**: Una aplicación web Spring Boot que expone APIs RESTful para gestionar datos de `Personaxe` y `Series`, almacenándolos en una base de datos MongoDB. También contiene archivos JSON (`Sanrio.json`, `Series.json`) que podrían usarse para la carga inicial de datos o como fuentes de datos. Incluye un controlador `apoiMoral` adicional. Este proyecto también incluye documentación OpenAPI.
*   **Flujo de Datos**:
    1.  Las solicitudes HTTP entrantes son manejadas por los `Controller`s (`PersonaxeController`, `SeriesController`, `RestApoioMoral`).
    2.  Los `Controller`s utilizan los `Service`s (implícitos o explícitos) para la lógica de negocio.
    3.  Los `Service`s interactúan con los `Repository`s (`PersonaxeRepository`, `SanrioUniverserRepository`, `SeriesRepository`) para realizar operaciones CRUD en la base de datos MongoDB.
    4.  Los `Model`os (`Personaxe.java`, `SanrioUniverse.java`, `Series.java`) representan la estructura de los datos en MongoDB y para la API.
    5.  Los archivos JSON en el directorio `Json` pueden ser utilizados por un servicio para precargar datos en MongoDB.
*   **Base de Datos**: MongoDB
*   **Puerto**: `8095`
*   **Documentación de la API**: Accesible a través de Swagger UI en `http://localhost:8095/swagger-ui/index.html` (después de que la aplicación esté en ejecución).
*   **Cómo Ejecutar**:
    1.  Asegúrate de tener una instancia de MongoDB en ejecución y accesible con la URI especificada en `src/main/resources/application.properties`.
    2.  Navega al directorio `MongoChamador`.
    3.  Compila el proyecto usando Maven: `mvn clean install`
    4.  Ejecuta la aplicación: `mvn spring-boot:run`
    5.  Accede a la documentación de la API en `http://localhost:8095/swagger-ui/index.html`.

## Configuración Común

*   **Versión de Java**: Todos los proyectos están configurados para usar Java 17.
*   **Maven**: Los proyectos se construyen utilizando Apache Maven. Asegúrate de que Maven esté instalado y configurado en tu sistema.
*   **Spring Boot**: Todos los proyectos utilizan Spring Boot 3.4.2.

---
**Nota**: El archivo `Readme.md` contenía anteriormente un enlace a un documento de examen. Esto se ha conservado al principio del archivo.