package com.Albieri.LibreriaMaxi;

import com.Albieri.LibreriaMaxi.Models.Autor;
import com.Albieri.LibreriaMaxi.Models.Books;
import com.Albieri.LibreriaMaxi.Models.ResultadoLibros;
import com.Albieri.LibreriaMaxi.Repository.AutorRepository;
import com.Albieri.LibreriaMaxi.Repository.BooksRepository;
import com.Albieri.LibreriaMaxi.Servicios.ConsumoApi;
import com.Albieri.LibreriaMaxi.Servicios.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private BooksRepository repositorio;
    private AutorRepository autorRepository;

    public Principal(BooksRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro 
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarLibrosWeb();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }

    }

    private ResultadoLibros getDatosSerie() {
        /*System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreSerie = teclado.nextLine();*/
        var json = consumoApi.obtenerDatos(URL_BASE);
        return convierteDatos.obtenerDatos(json, ResultadoLibros.class);
    }

    private void buscarLibrosWeb() {
        HashSet<Autor> autors = new HashSet<>();
        List<Books> datos = getDatosSerie().libros().stream()
                .peek(e -> autors.addAll(e.autores().stream()
                        .map(Autor::new)
                        .collect(Collectors.toSet())
                )).map(Books::new).toList();
        datos.forEach(System.out::println);
        System.out.println("Ya se mostraron los libros");
        datos.forEach(e -> repositorio.save(e));
        autors.forEach(e -> autorRepository.save(e));
    }
}

