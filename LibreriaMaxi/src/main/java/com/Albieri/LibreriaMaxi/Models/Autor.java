package com.Albieri.LibreriaMaxi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Autores")
@Getter
@Setter

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAutor;
    private String name;
    private Date fechaNacimiento;
    private Date fechaMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Books> libros;

    public Autor() {
    }

    public Autor(DatosAutor datosAutors) {
        this.name = datosAutors.nombre().replace(",", "");
        this.fechaNacimiento = datosAutors.fechaNacimiento();
        this.fechaMuerte = datosAutors.fechaMuerte();
    }

    @Override
    public String toString() {
        return
                "name=" + name +
                        ", fechaNacimiento='" + fechaNacimiento + '\'' +
                        ", fechaMuerte=" + fechaMuerte
                ;
    }
}
