# Kairova - Simple Mental Health Portal

A simplified version of the Kairova Student Mental Health Portal, providing a clean and intuitive interface for mental health self-assessment.

## Features

- Simple and intuitive mental health assessment
- Personalized advice based on user responses
- Mobile-responsive design
- No database required (in-memory storage)

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Getting Started

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd kairova-simple
   ```

2. **Build the application**
   ```bash
   mvn clean package
   ```

3. **Run the application**
   ```bash
   java -jar target/kairova-simple-0.0.1-SNAPSHOT.jar
   ```

4. **Access the application**
   Open your web browser and navigate to: [http://localhost:8080](http://localhost:8080)

## Project Structure

```
kairova-simple/
├── src/
│   └── main/
│       ├── java/com/kairova/simple/
│       │   ├── KairovaSimpleApplication.java  # Main application class
│       │   ├── controller/                    # Web controllers
│       │   ├── model/                         # Data models
│       │   └── service/                       # Business logic
│       └── resources/
│           ├── static/                        # Static resources (CSS, JS, images)
│           ├── templates/                     # Thymeleaf templates
│           └── application.properties         # Application configuration
├── pom.xml                                   # Maven configuration
└── README.md                                 # This file
```

## Customization

- **Colors and Styling**: Edit the CSS in `resources/static/css/`
- **Content**: Modify the Thymeleaf templates in `resources/templates/`
- **Logic**: Update the controller in `src/main/java/com/kairova/simple/controller/`

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built with Spring Boot and Thymeleaf
- Styled with Bootstrap 5
- Icons by Bootstrap Icons
