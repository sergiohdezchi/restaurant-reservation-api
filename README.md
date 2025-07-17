# Restaurant Reservation API

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.4-brightgreen?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-12+-blue?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Auth-purple?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![PayPal](https://img.shields.io/badge/PayPal-API-blue?style=for-the-badge&logo=paypal&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.6+-red?style=for-the-badge&logo=apache-maven&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-green?style=for-the-badge&logo=swagger&logoColor=white)

A comprehensive REST API for restaurant reservation management built with Spring Boot, featuring JWT authentication, PayPal integration, and PostgreSQL database.

## 🚀 Features

- **User Authentication**: JWT-based authentication with sign-up and sign-in endpoints
- **Restaurant Management**: Browse restaurants by district with pagination support
- **Reservation System**: Create and manage restaurant reservations
- **Payment Integration**: PayPal integration for secure payment processing
- **District Management**: Organized restaurant listings by Lima districts
- **Security**: Spring Security with JWT token validation
- **API Documentation**: Interactive API documentation with Swagger/OpenAPI

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 3.4.4**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **JWT (JSON Web Tokens)**
- **PayPal API**
- **Maven**
- **Lombok**
- **ModelMapper**
- **Swagger/OpenAPI**

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- PostgreSQL 12+
- PayPal Developer Account (for payment features)

## ⚙️ Installation

1. **Clone the repository**
```bash
git clone <repository-url>
cd restaurant_reservation
```

2. **Configure PostgreSQL Database**
```sql
CREATE DATABASE restaurant_db;
```

3. **Update application.properties**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. **Configure PayPal Credentials**
```properties
paypal.client-id=your_paypal_client_id
paypal.client-secret=your_paypal_client_secret
```

5. **Build and Run**
```bash
mvn clean install
mvn spring-boot:run
```

The API will be available at `http://localhost:8080/api/v1`

## 📚 API Documentation

Interactive API documentation is available at:
- **Swagger UI**: `http://localhost:8080/api/v1/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/api/v1/v3/api-docs`

## 🔐 Authentication

The API uses JWT (JSON Web Tokens) for authentication. After successful login, include the token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

## 📖 API Endpoints

### Authentication

#### Register User
```http
POST /auth/sign-up
Content-Type: application/json

{
    "firstName": "Sergio",
    "lastName": "Hernandez",
    "email": "sergio.chi2@test.com",
    "password": "123456ab"
}
```

**Response:**
```json
{
    "id": 4,
    "firstName": "Sergio",
    "lastName": "Hernandez",
    "email": "sergio.chi3@test.com",
    "role": "USER"
}
```

#### Login
```http
POST /auth/sign-in
Content-Type: application/json

{
    "email": "sergio.chi@test.com",
    "password": "123456ab"
}
```

**Response:**
```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "user": {
        "id": 1,
        "firstName": "Sergio",
        "lastName": "Hernandez",
        "email": "sergio.chi@test.com",
        "role": "USER"
    }
}
```

### Districts

#### Get All Districts
```http
GET /districts
Authorization: Bearer <token>
```

**Response:**
```json
[
    {
        "id": 1,
        "name": "Cercado de Lima"
    },
    {
        "id": 2,
        "name": "Ate"
    }
]
```

### Restaurants

#### Get Restaurants (Paginated)
```http
GET /restaurants/page?page=0
Authorization: Bearer <token>
```

#### Get Restaurants by District
```http
GET /restaurants/page/district?districtName=Ate&page=0
Authorization: Bearer <token>
```

#### Get Restaurant by ID
```http
GET /restaurants/{id}
Authorization: Bearer <token>
```

**Response:**
```json
{
    "id": 6,
    "name": "Café Barranco",
    "address": "Bolognesi 203",
    "phoneNumber": "01-6789012",
    "districtName": "Barranco",
    "pricePerPerson": 40.0,
    "capacity": 50
}
```

### Reservations

#### Create Reservation
```http
POST /reservations
Authorization: Bearer <token>
Content-Type: application/json

{
    "restaurantId": 3,
    "reservationDate": "2025-08-08T20:00:00",
    "numberOfPeople": 2,
    "additionalInfo": "Celebración de aniversario"
}
```

#### Get My Reservations
```http
GET /reservations/my-reservations
Authorization: Bearer <token>
```

**Response:**
```json
[
    {
        "id": 1,
        "restaurantName": "Ate Sazón",
        "reservationDate": "2025-08-08T20:00:00",
        "numberOfPeople": 2,
        "status": "PAID",
        "additionalInfo": "Celebración de aniversario",
        "totalAmount": 90.0
    }
]
```

#### Get Reservation by ID
```http
GET /reservations/{id}
Authorization: Bearer <token>
```

### Payment (PayPal)

#### Create Payment Order
```http
POST /checkout/paypal/create?reservationId=1&returnUrl=https://google.com&cancelUrl=https://google.com
Authorization: Bearer <token>
```

**Response:**
```json
{
    "paypalUrl": "https://www.sandbox.paypal.com/checkoutnow?token=9T566258AR397830U"
}
```

#### Capture Payment
```http
POST /checkout/paypal/capture?orderId=9T566258AR397830U
Authorization: Bearer <token>
```

**Response:**
```json
{
    "completed": true,
    "reservationId": 2
}
```

## 🗂️ Project Structure

```
src/
├── main/
│   ├── java/com/helier/restaurant_reservation/
│   │   ├── RestaurantReservationApplication.java
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST Controllers
│   │   ├── domain/          # Entity classes
│   │   ├── dto/             # Data Transfer Objects
│   │   ├── exception/       # Exception handling
│   │   ├── mapper/          # DTO mappers
│   │   ├── repository/      # Data repositories
│   │   ├── security/        # Security configuration
│   │   └── service/         # Business logic
│   └── resources/
│       ├── application.properties
│       ├── static/
│       └── templates/
└── test/
```

## 🔧 Configuration

### Database Configuration
The application uses PostgreSQL as the primary database. Update the following properties in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### JWT Configuration
JWT tokens are configured with a 30-day validity period. Update the secret key:

```properties
jwt.secret=your_jwt_secret_key
jwt.validity-in-seconds=2592000
```

### PayPal Configuration
Configure PayPal sandbox credentials:

```properties
paypal.client-id=your_paypal_client_id
paypal.client-secret=your_paypal_client_secret
paypal.api-base=https://sandbox.paypal.com
```

## 🚀 Deployment

The application is configured for deployment on Render.com. Update the production URL in `application.properties`:

```properties
helier.openapi.prod-url=https://your-app-name.onrender.com/api/v1
```

## 📊 Status Codes

- `200 OK` - Successful GET, PUT, PATCH requests
- `201 Created` - Successful POST requests
- `400 Bad Request` - Invalid request data
- `401 Unauthorized` - Authentication required
- `403 Forbidden` - Access denied
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Helier** - Initial work

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- PayPal for payment integration
- All contributors and testers

---

For more information or support, please contact the development team.