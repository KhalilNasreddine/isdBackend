package com.book.verse.books;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    //Optional<Book> findAllByType(String type);
    //Optional<Book> findByType(String type);
    List<Book> findAllByAuthorId(Integer authorId);
    List<Book> findAllByType(String type);
    Page<Book> findAllByOrderByCreatedDateDesc(Pageable pageable);
}