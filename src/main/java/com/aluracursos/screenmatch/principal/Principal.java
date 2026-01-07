package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.*;
import com.aluracursos.screenmatch.repository.SerieRepository;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String apiKey;
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosSerie> datosSeries = new ArrayList<>();
    private SerieRepository repositorio;
    private List<Serie> series = new ArrayList<>();

    public Principal(SerieRepository repository, String apiKey) {

        this.repositorio = repository;
        this.apiKey = apiKey;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar series 
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                    4 - Buscar series por título
                    5 - Top 5 series
                    6 - Buscar series por categoría
                    7 - Filtrar series
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarTop5Series();
                    break;
                case 6:
                    buscarSeriesPorCategoria();
                    break;
                case 7:
                    filtrarSeriesPorTemporadaYEvaluacion();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }
    private void buscarSeriePorTitulo() {
        System.out.println("Escribe el nombre de la serie que desea buscar:");
        var nombreSerie = teclado.nextLine();
        Optional<Serie> serieBuscada = repositorio.findByTituloContainingIgnoreCase(nombreSerie);

        if (serieBuscada.isPresent()) {
            System.out.println("Datos de la serie: " + serieBuscada.get());
        } else {
            System.out.println("Datos no encontrados");
        }
    }
    private DatosSerie getDatosSerie() {
        System.out.println("Escribe el nombre de la serie que deseas buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&apikey=" + apiKey);
        System.out.println(json);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos.sinopsis());
        return datos;
    }

    private void buscarEpisodioPorSerie() {
        mostrarSeriesBuscadas();
        System.out.println("Escribe el nombre de la serie de la cual quieres ver episodios");
        var nombreSerie = teclado.nextLine();
        Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nombreSerie);

        if (serie.isPresent()){
            var serieEncontrada = serie.get();
            List<DatosTemporadas> temporadas = new ArrayList<>();
            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoApi.obtenerDatos(URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + "&apikey=" + apiKey);
                DatosTemporadas datosTemporada = conversor.obtenerDatos(json, DatosTemporadas.class);
                temporadas.add(datosTemporada);
            }
            temporadas.forEach(System.out::println);
        }

    }

    private void buscarSerieWeb() {
        DatosSerie datos = getDatosSerie();
        Serie serie = new Serie(datos);
        repositorio.save(serie);
        //datosSeries.add(datos);
        System.out.println(datos);
    }

    private void mostrarSeriesBuscadas() {
        series = repositorio.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    public void buscarTop5Series() {
        List<Serie> topSeries = repositorio.findTop5ByOrderByEvaluacionDesc();
        topSeries.forEach(s-> System.out.println("Serie: " + s.getTitulo() + " Evaluación: " + s.getEvaluacion()));
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("De qué genero deseas buscar series:");
        var nombreGenero = teclado.nextLine();
        Categoria categoria = Categoria.fromEspanol(nombreGenero);
        List<Serie> seriesPorCategoria= repositorio.findByGenero(categoria);
        System.out.println("Series del genero " + nombreGenero + " ");
        seriesPorCategoria.forEach(System.out::println);
    }

    public void filtrarSeriesPorTemporadaYEvaluacion(){
        System.out.println("Buscar series con _ mínimo de temporada/s");
        var totalTemporadas = teclado.nextInt();
        teclado.nextLine();
        System.out.println("A partir de que valor mínimo como evaluación?");
        var evaluacion = teclado.nextDouble();
        teclado.nextLine();
        List<Serie> filtroSeries = repositorio.findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(totalTemporadas, evaluacion);
        System.out.println("**Series encontradas**");
        filtroSeries.forEach(s ->
                System.out.println(s.getTitulo() + " - evaluación: " + s.getEvaluacion()));
    }
}