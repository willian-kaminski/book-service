package com.kaminski.book.service.implementation;

import com.kaminski.book.entity.Book;
import com.kaminski.book.model.form.BookForm;
import com.kaminski.book.model.BookStatus;
import com.kaminski.book.repository.BookRepository;
import com.kaminski.book.service.BookService;
import com.kaminski.book.service.CategoryService;
import com.kaminski.book.utils.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private CategoryService categoryService;
    private Validation validation;

    @Override
    public List<Book> getAll(String filter){

        if(!filter.isEmpty())
            return findByTitleOrAuthor(filter);

        return listAll();
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAllAvailable();
    }

    @Override
    public Book getBook(Integer id) {

        Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent())
            return book.get();

        return null;

    }

    @Override
    public Book create(BookForm bookForm) {

        validation.verifyIfCategoryExists(bookForm.getCategoryId());

        Book book = Book.builder()
                .title(bookForm.getTitle())
                .isbn(bookForm.getIsbn())
                .author(bookForm.getAuthor())
                .category(categoryService.getCategory(bookForm.getCategoryId()))
                .description(bookForm.getDescription())
                .status(String.valueOf(BookStatus.AVAILABLE))
                .createAt(new Date())
                .build();

        return bookRepository.save(book);

    }

    @Override
    public Book update(Integer id, BookForm bookForm) {

        validation.verifyIfBookExists(id);
        validation.verifyIfBookIsRented(id);
        validation.verifyIfCategoryExists(bookForm.getCategoryId());

        var bookDB = getBook(id);
        bookDB.setTitle(bookForm.getTitle());
        bookDB.setIsbn(bookForm.getIsbn());
        bookDB.setDescription(bookForm.getDescription());
        bookDB.setAuthor(bookForm.getAuthor());
        bookDB.setCategory(categoryService.getCategory(bookForm.getCategoryId()));

        return bookRepository.save(bookDB);

    }

    @Override
    public Book delete(Integer id) {

        validation.verifyIfBookExists(id);
        validation.verifyIfBookIsRented(id);

        var book = getBook(id);
        book.setStatus(String.valueOf(BookStatus.REMOVED));

        return bookRepository.save(book);

    }

    @Override
    public List<Book> findByTitleOrAuthor(String filter) {
        return bookRepository.findByTitleOrAuthor(filter);
    }

    @Override
    public Book rentBook(Integer id) {

        validation.verifyIfBookExists(id);
        validation.verifyIfBookIsRented(id);

        var book = getBook(id);
        book.setStatus(String.valueOf(BookStatus.RENTED));

        return bookRepository.save(book);

    }

}