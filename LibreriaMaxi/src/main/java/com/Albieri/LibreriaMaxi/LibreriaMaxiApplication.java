package com.Albieri.LibreriaMaxi;

import com.Albieri.LibreriaMaxi.Repository.AutorRepository;
import com.Albieri.LibreriaMaxi.Repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibreriaMaxiApplication implements CommandLineRunner {

	@Autowired
	private BooksRepository repository;
	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LibreriaMaxiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, autorRepository);
		principal.muestraElMenu();
	}
}
