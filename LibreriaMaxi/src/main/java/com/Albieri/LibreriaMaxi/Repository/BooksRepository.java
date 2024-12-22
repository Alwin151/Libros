package com.Albieri.LibreriaMaxi.Repository;

import com.Albieri.LibreriaMaxi.Models.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
