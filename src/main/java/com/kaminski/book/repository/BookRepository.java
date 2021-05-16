package com.kaminski.book.repository;

import com.kaminski.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM tbl_books where status = 'AVAILABLE' and title like %?1% or author like %?1%")
    List<Book> findByTitleOrAuthor(String filter);

    @Query(nativeQuery = true, value = "SELECT * FROM tbl_books where status = 'AVAILABLE' ")
    List<Book> findAllAvailable();

}