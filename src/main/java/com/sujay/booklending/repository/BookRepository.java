package com.sujay.booklending.repository;

import com.sujay.booklending.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
