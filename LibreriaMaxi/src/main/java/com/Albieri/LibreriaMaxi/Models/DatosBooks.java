package com.Albieri.LibreriaMaxi.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosBooks(
        @JsonAlias("id") Long idGutember,
        String title,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages") List<String> lenguajes,
        @JsonAlias("download_count") Integer numeroDescargas
) {
}
