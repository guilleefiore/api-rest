# 🛒 API REST para Gestión de Productos

Desarrollado por: **Guillermina Fiore**  
Legajo: **50024**

---

## 📝 Descripción del Proyecto

API REST completa para un sistema de e-commerce básico que permite gestionar productos. El sistema implementa operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre productos, con validaciones, manejo de excepciones y documentación automática con Swagger/OpenAPI.

---

## ⚙️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA** - Persistencia de datos
- **H2 Database** - Base de datos en memoria
- **Spring Validation** - Validación de datos
- **Lombok** - Reducción de código boilerplate
- **SpringDoc OpenAPI** - Documentación automática de la API
- **Maven** - Gestión de dependencias

---

## 🏗️ Arquitectura del Proyecto

El proyecto sigue una arquitectura en capas:

```
com.utn.productosapi
├── model/              # Entidades JPA
│   ├── Producto.java
│   └── Categoria.java
├── dto/                # Data Transfer Objects
│   ├── ProductoDTO.java
│   ├── ProductoResponseDTO.java
│   └── ActualizarStockDTO.java
├── repository/         # Capa de acceso a datos
│   └── ProductoRepository.java
├── service/            # Lógica de negocio
│   └── ProductoService.java
├── controller/         # Controladores REST
│   └── ProductoController.java
└── exception/          # Manejo de excepciones
    ├── ProductoNotFoundException.java
    ├── StockInsuficienteException.java
    ├── ErrorResponse.java
    └── GlobalExceptionHandler.java
```

---

## 🚀 Instrucciones de Instalación

### Prerrequisitos

- Java 17 o superior
- Maven 3.6+

### Pasos para Ejecutar

1. **Clonar el repositorio:**
```bash
git clone https://github.com/tu-usuario/productos-api.git
cd productos-api
```

2. **Compilar el proyecto:**
```bash
./mvnw clean package
```

3. **Ejecutar la aplicación:**
```bash
./mvnw spring-boot:run
```

4. **La aplicación estará disponible en:**
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - H2 Console: `http://localhost:8080/h2-console`

---

## 🌐 Tabla de Endpoints

| Método | Ruta | Descripción | Códigos de Respuesta |
|--------|------|-------------|---------------------|
| `GET` | `/api/productos` | Listar todos los productos | 200 OK |
| `GET` | `/api/productos/{id}` | Obtener producto por ID | 200 OK, 404 Not Found |
| `GET` | `/api/productos/categoria/{categoria}` | Filtrar por categoría | 200 OK |
| `POST` | `/api/productos` | Crear nuevo producto | 201 Created, 400 Bad Request |
| `PUT` | `/api/productos/{id}` | Actualizar producto completo | 200 OK, 404 Not Found, 400 Bad Request |
| `PATCH` | `/api/productos/{id}/stock` | Actualizar solo el stock | 200 OK, 404 Not Found, 400 Bad Request |
| `DELETE` | `/api/productos/{id}` | Eliminar producto | 204 No Content, 404 Not Found |

### Categorías Disponibles

- `ELECTRONICA`
- `ROPA`
- `ALIMENTOS`
- `HOGAR`
- `DEPORTES`

---

```

**Intento de obtener producto inexistente (404 Not Found):**
```json
{
  "timestamp": "2025-11-11 00:16:45",
  "status": 404,
  "error": "Not Found",
  "mensaje": "No se encontró el producto con ID: 999",
  "path": "/api/productos/999"
}
```

---

## 📸 Pruebas y Resultados de la API

A continuación se muestran las salidas de consola de todas las pruebas realizadas sobre la API:

### 1. Creación de Productos (POST)

**Creando Laptop HP Pavilion:**
```json
{
  "id": 1,
  "nombre": "Laptop HP Pavilion",
  "descripcion": "Laptop HP Pavilion 15, Intel Core i5, 8GB RAM, 256GB SSD",
  "precio": 45999.99,
  "stock": 15,
  "categoria": "ELECTRONICA"
}
```
✅ **Código de respuesta: 201 Created**

**Creando Samsung Galaxy S23:**
```json
{
  "id": 2,
  "nombre": "Samsung Galaxy S23",
  "descripcion": "Smartphone Samsung Galaxy S23, 128GB, 8GB RAM",
  "precio": 89999.99,
  "stock": 25,
  "categoria": "ELECTRONICA"
}
```
✅ **Código de respuesta: 201 Created**

**Creando Remera Nike Deportiva:**
```json
{
  "id": 3,
  "nombre": "Remera Nike Deportiva",
  "descripcion": "Remera deportiva Nike Dri-FIT, varios colores",
  "precio": 8999.99,
  "stock": 50,
  "categoria": "ROPA"
}
```
✅ **Código de respuesta: 201 Created**

**Creando Arroz Gallo Oro:**
```json
{
  "id": 4,
  "nombre": "Arroz Gallo Oro 1kg",
  "descripcion": "Arroz blanco largo fino Gallo Oro",
  "precio": 1299.99,
  "stock": 100,
  "categoria": "ALIMENTOS"
}
```
✅ **Código de respuesta: 201 Created**

**Creando Pelota de Fútbol Adidas:**
```json
{
  "id": 5,
  "nombre": "Pelota de Fútbol Adidas",
  "descripcion": "Pelota de fútbol profesional Adidas UCL",
  "precio": 15999.99,
  "stock": 30,
  "categoria": "DEPORTES"
}
```
✅ **Código de respuesta: 201 Created**

### 2. Listar Todos los Productos (GET)

**Solicitud:** `GET /api/productos`

**Respuesta:**
```json
[
  {
    "id": 1,
    "nombre": "Laptop HP Pavilion",
    "descripcion": "Laptop HP Pavilion 15, Intel Core i5, 8GB RAM, 256GB SSD",
    "precio": 45999.99,
    "stock": 15,
    "categoria": "ELECTRONICA"
  },
  {
    "id": 2,
    "nombre": "Samsung Galaxy S23",
    "descripcion": "Smartphone Samsung Galaxy S23, 128GB, 8GB RAM",
    "precio": 89999.99,
    "stock": 25,
    "categoria": "ELECTRONICA"
  },
  {
    "id": 3,
    "nombre": "Remera Nike Deportiva",
    "descripcion": "Remera deportiva Nike Dri-FIT, varios colores",
    "precio": 8999.99,
    "stock": 50,
    "categoria": "ROPA"
  },
  {
    "id": 4,
    "nombre": "Arroz Gallo Oro 1kg",
    "descripcion": "Arroz blanco largo fino Gallo Oro",
    "precio": 1299.99,
    "stock": 100,
    "categoria": "ALIMENTOS"
  },
  {
    "id": 5,
    "nombre": "Pelota de Fútbol Adidas",
    "descripcion": "Pelota de fútbol profesional Adidas UCL",
    "precio": 15999.99,
    "stock": 30,
    "categoria": "DEPORTES"
  }
]
```
✅ **Código de respuesta: 200 OK** - Se listaron 5 productos correctamente

### 3. Obtener Producto por ID (GET)

**Solicitud:** `GET /api/productos/1`

**Respuesta:**
```json
{
  "id": 1,
  "nombre": "Laptop HP Pavilion",
  "descripcion": "Laptop HP Pavilion 15, Intel Core i5, 8GB RAM, 256GB SSD",
  "precio": 45999.99,
  "stock": 15,
  "categoria": "ELECTRONICA"
}
```
✅ **Código de respuesta: 200 OK** - Producto encontrado exitosamente

### 4. Filtrar por Categoría (GET)

**Solicitud:** `GET /api/productos/categoria/ELECTRONICA`

**Respuesta:**
```json
[
  {
    "id": 1,
    "nombre": "Laptop HP Pavilion",
    "descripcion": "Laptop HP Pavilion 15, Intel Core i5, 8GB RAM, 256GB SSD",
    "precio": 45999.99,
    "stock": 15,
    "categoria": "ELECTRONICA"
  },
  {
    "id": 2,
    "nombre": "Samsung Galaxy S23",
    "descripcion": "Smartphone Samsung Galaxy S23, 128GB, 8GB RAM",
    "precio": 89999.99,
    "stock": 25,
    "categoria": "ELECTRONICA"
  }
]
```
✅ **Código de respuesta: 200 OK** - Filtro por categoría funcionando correctamente

### 5. Actualizar Producto Completo (PUT)

**Solicitud:** `PUT /api/productos/1`
```json
{
  "nombre": "Laptop HP Pavilion 15 ACTUALIZADA",
  "descripcion": "Laptop HP Pavilion 15, Intel Core i7, 16GB RAM, 512GB SSD",
  "precio": 65999.99,
  "stock": 10,
  "categoria": "ELECTRONICA"
}
```

**Respuesta:**
```json
{
  "id": 1,
  "nombre": "Laptop HP Pavilion 15 ACTUALIZADA",
  "descripcion": "Laptop HP Pavilion 15, Intel Core i7, 16GB RAM, 512GB SSD",
  "precio": 65999.99,
  "stock": 10,
  "categoria": "ELECTRONICA"
}
```
✅ **Código de respuesta: 200 OK** - Producto actualizado completamente

### 6. Actualizar Solo Stock (PATCH)

**Solicitud:** `PATCH /api/productos/1/stock`
```json
{
  "stock": 100
}
```

**Respuesta:**
```json
{
  "id": 1,
  "nombre": "Laptop HP Pavilion 15 ACTUALIZADA",
  "descripcion": "Laptop HP Pavilion 15, Intel Core i7, 16GB RAM, 512GB SSD",
  "precio": 65999.99,
  "stock": 100,
  "categoria": "ELECTRONICA"
}
```
✅ **Código de respuesta: 200 OK** - Stock actualizado correctamente (de 10 a 100)

### 7. Eliminar Producto (DELETE)

**Solicitud:** `DELETE /api/productos/5`

**Respuesta:**
```
HTTP Status: 204 No Content
```
✅ **Código de respuesta: 204 No Content** - Producto eliminado exitosamente

### 8. Pruebas de Validación - Error 400

**Error: Nombre vacío**

**Solicitud:** `POST /api/productos` con nombre vacío
```json
{
  "nombre": "",
  "descripcion": "Test",
  "precio": 100.0,
  "stock": 10,
  "categoria": "ELECTRONICA"
}
```

**Respuesta:**
```json
{
  "path": "/api/productos",
  "mensaje": "Error de validación",
  "error": "Bad Request",
  "errores": {
    "nombre": "El nombre debe tener entre 3 y 100 caracteres"
  },
  "timestamp": "2025-11-11T00:30:23.375357941",
  "status": 400
}
```
✅ **Código de respuesta: 400 Bad Request** - Validación funcionando correctamente

**Error: Precio negativo**

**Solicitud:** `POST /api/productos` con precio negativo
```json
{
  "nombre": "Producto Test",
  "descripcion": "Test",
  "precio": -100.0,
  "stock": 10,
  "categoria": "ELECTRONICA"
}
```

**Respuesta:**
```json
{
  "path": "/api/productos",
  "mensaje": "Error de validación",
  "error": "Bad Request",
  "errores": {
    "precio": "El precio debe ser mayor a 0"
  },
  "timestamp": "2025-11-11T00:30:23.401870032",
  "status": 400
}
```
✅ **Código de respuesta: 400 Bad Request** - Validación de precio funcionando

### 9. Prueba de Error 404

**Solicitud:** `GET /api/productos/999` (producto inexistente)

**Respuesta:**
```json
{
  "timestamp": "2025-11-11 00:30:23",
  "status": 404,
  "error": "Not Found",
  "mensaje": "No se encontró el producto con ID: 999",
  "path": "/api/productos/999"
}
```
✅ **Código de respuesta: 404 Not Found** - Manejo de excepciones funcionando

### Resumen de Pruebas

| Prueba | Endpoint | Método | Resultado | Código HTTP |
|--------|----------|--------|-----------|-------------|
| Crear producto | `/api/productos` | POST | ✅ Exitoso | 201 |
| Listar productos | `/api/productos` | GET | ✅ Exitoso | 200 |
| Obtener por ID | `/api/productos/1` | GET | ✅ Exitoso | 200 |
| Filtrar por categoría | `/api/productos/categoria/ELECTRONICA` | GET | ✅ Exitoso | 200 |
| Actualizar completo | `/api/productos/1` | PUT | ✅ Exitoso | 200 |
| Actualizar stock | `/api/productos/1/stock` | PATCH | ✅ Exitoso | 200 |
| Eliminar producto | `/api/productos/5` | DELETE | ✅ Exitoso | 204 |
| Validación nombre | `/api/productos` | POST | ✅ Error esperado | 400 |
| Validación precio | `/api/productos` | POST | ✅ Error esperado | 400 |
| Producto no existe | `/api/productos/999` | GET | ✅ Error esperado | 404 |
