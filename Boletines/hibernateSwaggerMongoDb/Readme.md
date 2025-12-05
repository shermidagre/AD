```markdown
# 🎓 Microservicio de Gestión de Tutores y Alumnos

Este microservicio proporciona una API RESTful para gestionar tutores y sus alumnos, implementando una relación **one-to-many** (un titor puede tener múltiples alumnos). Está construido siguiendo buenas prácticas basadas en los proyectos **Adestrador (one2many)** y **Exemplo API REST con Swagger**.

---

## 📦 Funcionalidades

La API permite:

- ✅ Crear tutores  
- ✅ Crear alumnos (asociados a un titor)  
- ✅ Leer un titor junto con todos sus alumnos (relación *one2many*)  
- ✅ Modificar tutores y alumnos  
- ✅ Eliminar tutores (*restringido si tienen alumnos*)  
- ✅ Eliminar alumnos  

---

## 🗃️ Modelo de Datos

### Tabla `titor`
```sql
CREATE TABLE titor (
  id_titor SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  apelidos VARCHAR(150) NOT NULL
);
```

### Tabla `alumno`
```sql
CREATE TABLE alumno (
  id_alumno SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  apelidos VARCHAR(150) NOT NULL,
  id_titor INTEGER NOT NULL,
  CONSTRAINT fk_titor
    FOREIGN KEY (id_titor)
    REFERENCES titor (id_titor)
    ON UPDATE CASCADE
    ON DELETE RESTRICT  -- No se permite borrar tutores con alumnos asignados
);
```

> 🔒 **Restricción importante**: No se permite eliminar un titor si tiene alumnos asociados (`ON DELETE RESTRICT`).

---

## 🧪 Datos de ejemplo pre-cargados

### Tutores
| id_titor | nome   | apelidos            |
|----------|--------|---------------------|
| 1        | María  | López García        |
| 2        | Xosé   | Pérez Fernández     |

### Alumnos
| id_alumno | nome   | apelidos            | id_titor |
|-----------|--------|---------------------|----------|
| 1         | Ana    | Sánchez Varela      | 1        |
| 2         | Brais  | Lamas Rodríguez     | 1        |
| 3         | Clara  | Núñez Castro        | 1        |
| 4         | Diego  | Torres Iglesias     | 1        |
| 5         | Eva    | Mato Suárez         | 1        |
| 6         | Hugo   | Rivas Domínguez     | 2        |
| 7         | Iría   | Costa Rial          | 2        |
| 8         | Jorge  | Fraga Doval         | 2        |
| 9         | Lara   | Rey Santín          | 2        |
| 10        | Martiño| Carballeira Soto    | 2        |

---

## 🌐 Endpoints de la API

| Método | Ruta                    | Descripción                                         |
|--------|-------------------------|-----------------------------------------------------|
| `POST`   | `/titor`                | Crear un nuevo titor                                |
| `POST`   | `/alumno`               | Crear un nuevo alumno (asociado a un `id_titor`)   |
| `GET`    | `/titor/{id}`           | Obtener un titor **con todos sus alumnos**         |
| `PUT`    | `/titor/{id}`           | Actualizar un titor                                 |
| `PUT`    | `/alumno/{id}`          | Actualizar un alumno                                |
| `DELETE` | `/alumno/{id}`          | Eliminar un alumno                                  |
| `DELETE` | `/titor/{id}`           | Eliminar un titor (**solo si no tiene alumnos**)   |

> ✅ Todos los endpoints devuelven respuestas en formato **JSON**.  
> 📝 Los errores devuelven códigos HTTP adecuados (400, 404, 409, etc.).

---

## Como iniciarlo en una maquina virtual

### Instala docker si no lo tienes instalado para subir el contendor

````dotenv
sudo apt update
sudo apt install docker.io -y
````

#### Crea el contenedor
````dotenv
sudo docker run -d -p 27017:27017 --name mi-mongo mongo:latest
````

#### Comprueba que esta subido

````dotenv
sudo docker ps
````

# 🚀 Guía Rápida: Probando tu API con Swagger UI

Ahora que tu aplicación está conectada, puedes acceder y probar tus endpoints usando **Swagger UI**, una interfaz web interactiva ya instalada y configurada en tu proyecto. Swagger te genera botones para probar cada método **sin necesidad de escribir código**.

---

## 🔗 1. Abrir Swagger UI

Tu aplicación Java corre localmente en Windows (no en la VM) y está configurada en el puerto `8082`.  
Ingresa aquí:

👉 [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

---

## ⚠️ 2. Instrucciones de Uso (Orden Importante)

Al abrir Swagger, verás dos secciones principales:

- `titor-controller`
- `alumno-controller`

> ❗ **Importante**: Según la lógica implementada en `AlumnoService`, **un alumno NO puede existir sin un Titor**. Si intentas crear un alumno primero, obtendrás un error.

Sigue **estrictamente este orden**:

---

### ✅ Paso A: Crear un Titor

1. Despliega el grupo **`titor-controller`**.
2. Busca el endpoint:  
   🟩 `POST /api/titor`
3. Haz clic en **"Try it out"** (a la derecha).
4. En el campo **Request body**, reemplaza el contenido por:
   ```json
   {
     "nome": "Pepe",
     "apelidos": "Pérez"
   }
   

## 📚 Documentación API (Swagger)

La API incluye documentación interactiva mediante **Swagger UI** en:

```
GET /swagger-ui/index.html

```

o accede directamente a la especificación OpenAPI en:

```
GET /v3/api-docs
```

> 🖼️ Swagger permite probar los endpoints directamente desde el navegador.

---

## 🛠️ Tecnologías utilizadas

- **Lenguaje**: Java / Kotlin / Python (según implementación)
- **Framework**: Spring Boot / FastAPI / Express (ej. basado en proyectos de referencia)
- **Base de datos**: PostgreSQL
- **ORM**: JPA (Hibernate) / SQLAlchemy / TypeORM
- **Validación**: Bean Validation / Pydantic
- **Documentación**: Swagger/OpenAPI 3
- **Pruebas**: JUnit / pytest

---


✅ Servidor disponible en: `http://localhost:8080`

---
