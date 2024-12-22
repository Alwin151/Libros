package com.Albieri.LibreriaMaxi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "Libros")
@Getter @Setter
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idLibro")
    private Long id;
    private Long idGutemberg;
    @Column(name = "titulo",unique = true)
    private String title;
    @Transient
    private List<String> lenguajes;
    private Integer numeroDescargas;
    @ManyToOne
    @JoinColumn(name = "id_Autor")
    private Autor autor;

    public Books(){}

    public Books(DatosBooks datosBooks){
        this.idGutemberg = datosBooks.idGutember();
        this.title = datosBooks.title();
        this.numeroDescargas = datosBooks.numeroDescargas();
    }
    @Override
    public String toString(){
        return "Titulo :"+title;
    }
}
