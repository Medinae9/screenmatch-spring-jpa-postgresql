🎬 Screenmatch – Persistencia de Datos con Spring JPA y PostgreSQLEste proyecto es una aplicación Java desarrollada con Spring Boot que permite gestionar información de series y episodios. Evolucionó de una simple búsqueda en consola a un sistema robusto con persistencia en PostgreSQL, utilizando Spring Data JPA para realizar consultas avanzadas y complejas.Es un proyecto académico del curso de Alura Latam, enfocado en el dominio de la persistencia de datos y el consumo de APIs REST.🎯 Objetivo del ProyectoDesarrollar una aplicación capaz de consumir datos de la API de OMDb, procesarlos mediante Streams de Java, y persistirlos de forma relacional en una base de datos, permitiendo búsquedas personalizadas mediante JPQL.🚀 Funcionalidades PrincipalesLa aplicación ofrece un menú interactivo con las siguientes opciones:Buscar Series en la Web: Consulta la API de OMDb y guarda solo resultados de tipo "serie" para evitar inconsistencias.Buscar Episodios: Descarga y asocia todos los episodios de las temporadas de una serie guardada.Mostrar Series Buscadas: Lista las series almacenadas ordenadas por género.Buscar Series por Título: Localiza una serie específica en la base de datos local.Top 5 Mejores Series: Filtra las 5 series con mejor evaluación.Buscar por Categoría: Filtra series según el género (Acción, Comedia, Drama, etc.).Filtro por Temporadas y Evaluación: Consultas combinadas para encontrar contenido específico.Buscar Episodios por Título: Encuentra capítulos específicos usando fragmentos de su nombre.Top 5 Episodios por Serie: Muestra los capítulos mejor valorados de una serie elegida.🛠️ Tecnologías UtilizadasJava 17Spring Boot 3.xSpring Data JPAHibernate (Mapeo Objeto-Relacional)PostgreSQL (Base de datos relacional)Jackson (Manipulación de JSON)Maven (Gestión de dependencias)🗺️ Estructura de la Base de DatosSe implementó una relación 1:N (Uno a Muchos) entre Series y Episodios, asegurando integridad mediante el uso de claves foráneas y cascada.Fragmento de códigoerDiagram
SERIE ||--o{ EPISODIO : "tiene"
SERIE {
Long id PK
String titulo
Integer totalTemporadas
Double evaluacion
String poster
Categoria genero
String actores
String sinopsis
}
EPISODIO {
Long id PK
String titulo
Integer temporada
Integer numeroEpisodio
Double evaluacion
LocalDate fechaDeLanzamiento
Long serie_id FK
}
🧠 Conceptos Avanzados Implementados1. Consultas con JPQL y SQLSe abandonaron las consultas derivadas simples en favor de JPQL para lograr mayor legibilidad y potencia:JOINs: Unión de tablas para buscar episodios a través de los atributos de la serie.Funciones Nativas: Uso de ILIKE para búsquedas insensibles a mayúsculas y LIMIT para rankings.2. Tratamiento de Datos y ResilienciaFiltros de Streams: Se implementaron validaciones .filter(d -> d.episodios() != null) para evitar errores de tipo NullPointerException cuando la API devuelve datos incompletos.Validación de Tipo: La aplicación solo permite el guardado de contenido donde Type = "series", ignorando automáticamente películas o juegos.🔐 Configuración y SeguridadEl proyecto utiliza variables de entorno para proteger credenciales sensibles.Variables Necesarias:VariableDescripciónDB_URLURL de conexión (ej. jdbc:postgresql://localhost:5432/screenmatch)DB_USERNAMETu usuario de PostgresDB_PASSWORDTu contraseña de PostgresIMDB_API_KEYTu API Key de OMDbConfiguración en application.properties:Propertiesspring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
▶️ Cómo Ejecutar el Proyecto
Clona este repositorio.Configura las variables de entorno en tu sistema o en el IDE (IntelliJ).Asegúrate de tener una instancia de PostgreSQL corriendo y una base de datos llamada screenmatch.Ejecuta ScreenmatchApplication.java.📚 Lo que aprendí en este móduloDiferenciar entre consultas derivadas, consultas nativas y JPQL.Manejar fechas en SQL y Java de forma sincronizada.Implementar un diseño de software defensivo contra datos nulos de APIs externas.Optimizar el rendimiento de la base de datos mediante el uso de FetchType.EAGER y EASY según la necesidad.

Autor: Emmanuel Medina AndradeProyecto Académico – Alura Latam / Oracle Next Education