
---

# 📚 Sistema de Gestión de Lecturas

## 🎯 Objetivos

Este proyecto tiene dos objetivos principales:

### Objetivo principal
Implementar un sistema que permita la **inserción y lectura de datos de libros** en diferentes bases de datos (relacional y no relacional).

### Objetivo secundario (opcional)
Crear una **interfaz web** que facilite la visualización de los registros almacenados.  
En caso de no cumplirse este objetivo, se deberá implementar una **API REST** que permita consultar los registros.

---

## 👤 Actores

### Usuario
- **Nombre**: Usuario
- **Descripción**:  
  Un profesor olvidadizo que desea almacenar sus lecturas en distintas bases de datos.  
  No confía plenamente en sus alumnos, por lo que también quiere poder **ver los registros en archivos** y **revisar los errores** en caso de fallos.

#### Casos de uso asociados:
- Registro de Libro
- Consulta de Registros del Sistema
- Consulta de Libro

---

## 📋 Casos de Uso

### 1. Registro de Libro
- **Descripción**:  
  El usuario registra un libro mediante una API expuesta por el sistema. Esto puede hacerse a través de:
    - Interfaz web (recomendado: Swagger)
    - Página web dedicada
    - Publicación de JSON en un topic de Kafka (sistema más complejo)

- **Precondiciones**:  
  —

- **Éxito**:  
  El libro queda registrado en:
    - Base de datos relacional (PostgreSQL)
    - Base de datos no relacional (MongoDB)
    - Archivo XML en el sistema

- **Resultado**:  
  ✅ Registro en ambas bases de datos  
  ✅ Archivo XML con los datos del libro

---

### 2. Consulta de Libro (Base de Datos Relacional)
- **Descripción**:  
  El usuario consulta datos de un libro a través de la API. Puede hacerse mediante:
    - Web simple (preferible)
    - Interfaz Swagger
    - Consola (ej. `curl`)

- **Funcionalidades de consulta**:
    - Libro por **ISBN** o **título**
    - Lista de libros por **autor**
    - Lista de libros por **título**
    - Lista de libros entre **fechas de lectura**
    - Lista de libros entre **fechas de registro**

- **Precondiciones**:  
  El registro debe existir.

- **Éxito**:  
  El usuario obtiene los datos solicitados.

- **Resultado**:  
  📄 Se muestran los datos consultados.

---

### 3. Consulta de Libro (Base de Datos No Relacional)
- **Descripción**:  
  Igual que el caso anterior, pero consultando la base de datos **no relacional** (MongoDB).

- **Funcionalidades de consulta**:  
  Mismas que en el caso relacional.

- **Precondiciones**:  
  El registro debe existir.

- **Éxito**:  
  El usuario obtiene los datos solicitados.

- **Resultado**:  
  📄 Se muestran los datos consultados.

---

### 4. Consulta de Registros del Sistema
- **Descripción**:  
  El usuario consulta los datos almacenados en el **archivo XML** del sistema.

- **Funcionalidades de consulta**:
    - Libro por **ISBN** o **título**

- **Precondiciones**:  
  El registro debe existir en el archivo XML.

- **Éxito**:  
  El usuario obtiene los datos solicitados.

- **Resultado**:  
  📄 Se muestran los datos consultados del archivo XML.

---

## 🏗️ Arquitectura

El sistema sigue una **arquitectura de microservicios**, desplegable en una máquina con **Ubuntu**, que aloja:

- **PostgreSQL** (base de datos relacional)
- **MongoDB** (base de datos no relacional)

### Tipos de microservicios

| Tipo          | Servicios                              | Descripción |
|---------------|----------------------------------------|-------------|
| **Productores** | `prd-rex`<br>`relational-prd-query`<br>`nonrelational-prd-query` | Exponen APIs para interactuar con archivos y bases de datos |
| **Consumidor**  | `con-external`                         | Interfaz de entrada para peticiones de registro |

> 🔍 En la vida real, los servicios podrían diferenciarse por si exponen APIs o responden a eventos. En este sistema, **todos exponen una API** para facilitar la interconexión.

---

## 🧩 Diseño por Componentes

### Microservicios

- **`con-external`**
    - Expone la interfaz de entrada para el registro de libros.
    - Formato de entrada: **JSON**

- **`prd-rex`**
    - Escribe nuevos registros en archivos XML.
    - Registra logs del resultado de las operaciones en las bases de datos.

- **`relational-prd-query`**
    - Se comunica con la base de datos **PostgreSQL**.

- **`nonrelational-prd-query`**
    - Se comunica con la base de datos **MongoDB**.

### Web (Opcional)

- Interfaz web simple para consultar los registros almacenados en `prd-rex`.
- Permite especificar la "tabla" (o fuente) consultada y devuelve los resultados.

---

> ✨ **Nota**: Si no se implementa la interfaz web, se garantizará una API REST completa para todas las operaciones de consulta y registro.

--- 

