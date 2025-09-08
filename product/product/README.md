# Product Management REST API

A simple Spring Boot REST API for managing products with MySQL database integration using XAMPP.

## Features

- **CRUD Operations** - Create, Read, Update, Delete products
- **Search Functionality** - Search products by name
- **MySQL Integration** - Uses MySQL database via XAMPP
- **Input Validation** - Request body validation with meaningful error messages
- **Exception Handling** - Global exception handling with proper HTTP status codes
- **Simple Architecture** - Clean and minimal code structure

## Technology Stack

- **Java 17**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **MySQL 8.0** (via XAMPP)
- **Maven**
- **Lombok**

## 🗄Database Schema

### Product Table
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key, auto-increment |
| name | VARCHAR(255) | Product name (required) |
| description | VARCHAR(255) | Product description (required) |
| price | DECIMAL(10,2) | Product price (required, > 0) |
| quantity | INT | Available stock quantity (required, >= 0) |
| created_at | TIMESTAMP | Creation timestamp (auto-generated) |
| updated_at | TIMESTAMP | Last update timestamp (auto-generated) |

## Setup Instructions

### 1. Prerequisites
- Java 17 or higher
- Maven 3.6+
- XAMPP (for MySQL)

### 2. Database Setup
1. **Start XAMPP** and ensure MySQL service is running
2. **Access phpMyAdmin** at `http://localhost/phpmyadmin`
3. **Create database:**
   ```sql
   CREATE DATABASE product_db;
   ```

### 3. Application Configuration
The application is configured to connect to MySQL at:
- **Host:** localhost:3306
- **Database:** product_db
- **Username:** root
- **Password:** (empty)

### 4. Run the Application
```bash
# Navigate to project directory
cd product

# Run the application
./mvnw spring-boot:run
```

The API will be available at: `http://localhost:8080`

## API Endpoints

### Base URL: `http://localhost:8080/products`

### 1. Create Product
- **Method:** `POST`
- **URL:** `/products`
- **Request Body:**
```json
{
  "name": "Gaming Laptop",
  "description": "High-performance gaming laptop with RTX 4080",
  "price": 1599.99,
  "quantity": 5
}
```
- **Response:** `201 Created`
```json
{
  "id": 1,
  "name": "Gaming Laptop",
  "description": "High-performance gaming laptop with RTX 4080",
  "price": 1599.99,
  "quantity": 5,
  "createdAt": "2025-01-08T18:10:30.435167",
  "updatedAt": "2025-01-08T18:10:30.435167"
}
```

### 2. Get All Products
- **Method:** `GET`
- **URL:** `/products`
- **Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Gaming Laptop",
    "description": "High-performance gaming laptop with RTX 4080",
    "price": 1599.99,
    "quantity": 5,
    "createdAt": "2025-01-08T18:10:30.435167",
    "updatedAt": "2025-01-08T18:10:30.435167"
  }
]
```

### 3. Get Product by ID
- **Method:** `GET`
- **URL:** `/products/{id}`
- **Response:** `200 OK` or `404 Not Found`
```json
{
  "id": 1,
  "name": "Gaming Laptop",
  "description": "High-performance gaming laptop with RTX 4080",
  "price": 1599.99,
  "quantity": 5,
  "createdAt": "2025-01-08T18:10:30.435167",
  "updatedAt": "2025-01-08T18:10:30.435167"
}
```

### 4. Update Product
- **Method:** `PUT`
- **URL:** `/products/{id}`
- **Request Body:**
```json
{
  "name": "Updated Gaming Laptop",
  "description": "Updated high-performance gaming laptop",
  "price": 1699.99,
  "quantity": 3
}
```
- **Response:** `200 OK` or `404 Not Found`

### 5. Delete Product
- **Method:** `DELETE`
- **URL:** `/products/{id}`
- **Response:** `204 No Content` or `404 Not Found`

### 6. Search Products by Name
- **Method:** `GET`
- **URL:** `/products/search?name={searchTerm}`
- **Example:** `/products/search?name=laptop`
- **Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Gaming Laptop",
    "description": "High-performance gaming laptop with RTX 4080",
    "price": 1599.99,
    "quantity": 5,
    "createdAt": "2025-01-08T18:10:30.435167",
    "updatedAt": "2025-01-08T18:10:30.435167"
  }
]
```

## 🧪 Postman Testing Guide

### Import Collection
Create a new Postman collection called "Product Management API" and add the following requests:

### 1. Create Product
- **Method:** `POST`
- **URL:** `http://localhost:8080/products`
- **Headers:** 
  - `Content-Type: application/json`
- **Body (raw JSON):**
```json
{
  "name": "Gaming Laptop",
  "description": "High-performance gaming laptop with RTX 4080",
  "price": 1599.99,
  "quantity": 10
}
```
- **Expected Response:** `201 Created`

### 2. Get All Products (Simple List)
- **Method:** `GET`
- **URL:** `http://localhost:8080/products`
- **Expected Response:** `200 OK` with array of products

### 3. Get All Products (Paginated & Sorted)
- **Method:** `GET`
- **URL:** `http://localhost:8080/products?paginated=true&page=0&size=5&sortBy=name&sortDir=asc`
- **Query Parameters:**
  - `paginated`: `true`
  - `page`: `0`
  - `size`: `5`
  - `sortBy`: `name` (options: id, name, price, quantity, createdAt, updatedAt)
  - `sortDir`: `asc` (options: asc, desc)
- **Expected Response:** `200 OK` with paginated response including metadata

### 4. Get Product by ID
- **Method:** `GET`
- **URL:** `http://localhost:8080/products/1`
- **Expected Response:** `200 OK` or `404 Not Found`

### 5. Update Product (with Stock Validation)
- **Method:** `PUT`
- **URL:** `http://localhost:8080/products/1`
- **Headers:** 
  - `Content-Type: application/json`
- **Body (raw JSON):**
```json
{
  "name": "Updated Gaming Laptop",
  "description": "Updated high-performance gaming laptop",
  "price": 1699.99,
  "quantity": 8
}
```
- **Expected Response:** `200 OK` or error if reducing stock below available

### 6. Delete Product
- **Method:** `DELETE`
- **URL:** `http://localhost:8080/products/1`
- **Expected Response:** `204 No Content` or `404 Not Found`

### 7. Search Products by Name
- **Method:** `GET`
- **URL:** `http://localhost:8080/products/search?name=laptop`
- **Query Parameters:**
  - `name`: `laptop`
- **Expected Response:** `200 OK` with filtered products

### 8. Check Stock Availability
- **Method:** `GET`
- **URL:** `http://localhost:8080/products/1/stock?quantity=5`
- **Query Parameters:**
  - `quantity`: `5`
- **Expected Response:** `200 OK` with `true` or `false`

### 🔧 Postman Environment Variables
Create environment variables for easier testing:
- `baseUrl`: `http://localhost:8080`
- `productId`: `1` (update after creating products)

### 📋 Test Scenarios

#### Scenario 1: Complete CRUD Flow
1. **Create** a new product
2. **Get All** products to see the created product
3. **Get by ID** to retrieve specific product
4. **Update** the product details
5. **Delete** the product
6. **Get by ID** again to confirm deletion (should return 404)

#### Scenario 2: Pagination Testing
1. Create multiple products (5-10 products)
2. Test pagination with different page sizes
3. Test sorting by different fields (name, price, quantity)
4. Test both ascending and descending order

#### Scenario 3: Search Functionality
1. Create products with different names
2. Search by partial name matches
3. Test case-insensitive search

#### Scenario 4: Stock Management
1. Create a product with quantity 10
2. Check stock availability for 5 units (should return true)
3. Check stock availability for 15 units (should return false)
4. Try to update quantity to 5 (should work)
5. Try to update quantity to 15 (should fail with stock error)

#### Scenario 5: Validation Testing
1. Try creating product with empty name (should return 400)
2. Try creating product with negative price (should return 400)
3. Try creating product with negative quantity (should return 400)
4. Try accessing non-existent product (should return 404)

### 📊 Sample Paginated Response
```json
{
  "content": [
    {
      "id": 1,
      "name": "Gaming Laptop",
      "description": "High-performance gaming laptop",
      "price": 1599.99,
      "quantity": 10,
      "createdAt": "2025-01-08T18:10:30.435167",
      "updatedAt": "2025-01-08T18:10:30.435167"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5,
    "sort": {
      "sorted": true,
      "ascending": true
    }
  },
  "totalElements": 1,
  "totalPages": 1,
  "first": true,
  "last": true,
  "numberOfElements": 1
}
```

## Testing with PowerShell (Alternative)

### Create a Product
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/products" -Method POST -ContentType "application/json" -Body '{"name": "Wireless Mouse", "description": "Ergonomic wireless gaming mouse", "price": 79.99, "quantity": 25}'
```

### Get All Products (Paginated)
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/products?paginated=true&page=0&size=3&sortBy=price&sortDir=desc" -Method GET
```

### Search Products
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/products/search?name=mouse" -Method GET
```

### Check Stock Availability
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/products/1/stock?quantity=5" -Method GET
```

## Error Handling

### Validation Errors (400 Bad Request)
```json
{
  "name": "Product name is mandatory",
  "price": "Price must be greater than 0",
  "quantity": "Quantity cannot be negative"
}
```

### Not Found Error (404 Not Found)
```json
"Product not found with id: 999"
```


## Configuration

### application.properties
```properties
spring.application.name=product
server.port=8080

# MySQL Database Configuration (XAMPP)
spring.datasource.url=jdbc:mysql://localhost:3306/product_db
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

## Validation Rules

- **Name:** Cannot be empty or blank
- **Description:** Cannot be empty or blank
- **Price:** Must be greater than 0 (BigDecimal for precision)
- **Quantity:** Must be 0 or greater (cannot be negative)

## HTTP Status Codes

- **200 OK** - Successful GET/PUT requests
- **201 Created** - Successful POST requests
- **204 No Content** - Successful DELETE requests
- **400 Bad Request** - Validation errors
- **404 Not Found** - Product not found
- **500 Internal Server Error** - Unexpected server errors

## Getting Started

1. **Clone the repository**
2. **Start XAMPP MySQL service**
3. **Create `product_db` database**
4. **Run the application:** `./mvnw spring-boot:run`
5. **Test the API** using the provided PowerShell commands or Postman

The API will automatically create the `products` table on startup with the configured schema.

---

