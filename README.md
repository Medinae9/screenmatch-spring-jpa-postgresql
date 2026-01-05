🎬 Screenmatch – Persistencia de Datos con Spring JPA y PostgreSQL

Este proyecto es una aplicación Java desarrollada con Spring Boot cuyo objetivo es aprender a guardar y consultar
información en una base de datos PostgreSQL utilizando Spring Data JPA.

Es un proyecto académico del curso de Alura, enfocado en comprender los conceptos básicos de persistencia de datos,
consumo de APIs externas y organización de un proyecto Java con Spring.

🎯 Objetivo del Proyecto

Aprender a usar Spring Boot.

Conectar una aplicación Java a PostgreSQL.

Guardar y consultar datos usando JPA.

Crear entidades y repositorios.

Realizar consultas sencillas.

Consumir datos desde una API externa (OMDB).

Usar variables de entorno para datos sensibles.

📂 Estructura del Proyecto
src/main/java/com/aluracursos/screenmatch/
├── model/ # Clases que representan los datos
├── repository/ # Interfaces JPA para acceder a la base de datos
├── service/ # Lógica del programa y consumo de la API
├── principal/ # Menú y ejecución del programa
└── ScreenmatchApplication.java

🛠️ Tecnologías Utilizadas

Java

Spring Boot

Spring Data JPA

Hibernate

PostgreSQL

Maven

IntelliJ IDEA

API de OMBD

🗃️ Base de Datos

La aplicación utiliza PostgreSQL como sistema gestor de base de datos.

Las tablas se crean automáticamente usando JPA.

Hibernate se encarga del mapeo entre clases Java y tablas.

La configuración se realiza mediante variables de entorno.

🗺️ Diagrama de la Base de Datos
erDiagram
SERIE {
Long id PK
String titulo
String categoria
Double evaluacion
Integer total_temporadas
}

    TEMPORADA {
        Long id PK
        Integer numero
        Long serie_id FK
    }

    EPISODIO {
        Long id PK
        String titulo
        Integer numero
        Double evaluacion
        Long temporada_id FK
    }

    SERIE ||--o{ TEMPORADA : "tiene"
    TEMPORADA ||--o{ EPISODIO : "contiene"

🧠 Explicación sencilla

Una Serie puede tener varias Temporadas.

Una Temporada puede tener varios Episodios.

PK significa clave primaria.

FK significa clave foránea.

Las relaciones se crean automáticamente con Spring Data JPA.

🔐 Configuración con Variables de Entorno

Para mayor seguridad, el proyecto utiliza variables de entorno para manejar información sensible como:

Credenciales de PostgreSQL

API Key de IMDb

Esto evita subir datos privados al repositorio.

🌍 Variables de Entorno Utilizadas
Variable Descripción
DB_URL URL de conexión a PostgreSQL
DB_USERNAME Usuario de la base de datos
DB_PASSWORD Contraseña de la base de datos
IMDB_API_KEY API Key para consumir la API de IMDb
⚙️ Configuración en application.properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update

imdb.api.key=${IMDB_API_KEY}

🛠️ Configurar variables de entorno
Windows (PowerShell)
setx DB_URL "jdbc:postgresql://localhost:5432/screenmatch"
setx DB_USERNAME "postgres"
setx DB_PASSWORD "tu_password"
setx IMDB_API_KEY "tu_api_key"

Linux / macOS
export DB_URL="jdbc:postgresql://localhost:5432/screenmatch"
export DB_USERNAME="postgres"
export DB_PASSWORD="tu_password"
export IMDB_API_KEY="tu_api_key"

📄 ¿Qué hace la aplicación?

Consulta información de series desde la API de IMDb.

Convierte los datos recibidos a objetos Java.

Guarda la información en PostgreSQL.

Permite realizar consultas como:

Buscar series por nombre

Buscar series por categoría

Buscar series por evaluación

Consultar número de temporadas

Muestra los resultados en consola.

▶️ Cómo ejecutar el proyecto

1. Requisitos

Java instalado

PostgreSQL en ejecución

Base de datos creada (por ejemplo: screenmatch)

Variables de entorno configuradas

API Key válida de OMBD

2. Clonar el repositorio

3. Abrir el proyecto

Abrir en IntelliJ IDEA

Esperar a que Maven descargue las dependencias

4. Ejecutar la aplicación

Ejecutar la clase:

ScreenmatchApplication.java

📚 Conceptos Aprendidos

Conexión a PostgreSQL desde Java

Uso de @Entity, @Id, @OneToMany y @ManyToOne

Repositorios con JpaRepository

Persistencia de datos con JPA

Consultas básicas

Consumo de APIs REST

Uso de variables de entorno para seguridad

👤 Autor

Emmanuel Medina Andrade
Proyecto académico – Alura Latam