package com.kaminski.book.service;

import com.kaminski.book.entity.Book;
import com.kaminski.book.model.form.BookForm;

import java.util.List;

public interface BookService {

    List<Book> getAll(String filter);

    List<Book> listAll();

    Book getBook(Integer id);

    Book create(BookForm book);

    Book update(Integer id, BookForm book);

    Book delete(Integer id);

    List<Book> findByTitleOrAuthor(String filter);

    Book rentBook(Integer id);

}