package com.kaminski.book.service;

import com.kaminski.book.entity.Book;
import com.kaminski.book.entity.Category;
import com.kaminski.book.exception.BookAlreadyRentedException;
import com.kaminski.book.exception.ResourceNotFoundException;
import com.kaminski.book.model.BookStatus;
import com.kaminski.book.model.form.BookForm;
import com.kaminski.book.repository.BookRepository;
import com.kaminski.book.service.implementation.BookServiceImpl;
import com.kaminski.book.service.implementation.CategoryServiceImpl;
import com.kaminski.book.utils.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(value = MockitoExtension.class)
class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryServiceImpl categoryService;

    @Mock
    private Validation validation;

    private final Book book = Book.builder()
            .id(1)
            .title("Book Test")
            .author("Author Test")
            .isbn("0001")
            .status(BookStatus.AVAILABLE.toString())
            .description("Description test")
            .category(new Category(1, "ACTION"))
            .build();

    private final Book bookUpdate = Book.builder()
            .id(1)
            .title("Book Test 1")
            .author("Author Test")
            .isbn("0001")
            .status(BookStatus.AVAILABLE.toString())
            .description("Description test")
            .category(new Category(1, "ACTION"))
            .build();

    private final Book bookDelete = Book.builder()
            .id(1)
            .title("Book Test")
            .author("Author Test")
            .isbn("0001")
            .status(BookStatus.AVAILABLE.toString())
            .description("Description test")
            .category(new Category(1, "ACTION"))
            .build();

    private final Category category = Category.builder()
            .id(1)
            .name("ACTION")
            .build();

    @Test
    @DisplayName("Test listAll Success")
    void when_getAll_book_it_should_return_book_list(){
        List<Book> booksExpected = Arrays.asList(book);
        when(bookService.listAll()).thenReturn(booksExpected);
        assertThat(bookService.listAll(), is(booksExpected));
    }

    @Test
    @DisplayName("Test getBook Success")
    void when_getAll_book_by_id_it_should_return_book(){
        when(bookRepository.findById(anyInt())).thenReturn(Optional.ofNullable(book));
        var bookMK = bookService.getBook(1);
        assertThat(bookMK, is(book));
    }

    @Test
    @DisplayName("Test getBook Error")
    void when_getAll_book_by_invalid_id_it_should_return_throw_exception(){
        doThrow(ResourceNotFoundException.class).when(validation).verifyIfBookExists(anyInt());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->{
            bookService.getBook(1);
        });
    }

    @Test
    @DisplayName("Test create Success")
    void when_create_book_it_should_return_book(){

        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(categoryService.getCategory(anyInt())).thenReturn(category);
        doNothing().when(validation).verifyIfCategoryExists(anyInt());

        BookForm bookForm = BookForm.builder()
                .title("Book Test")
                .author("Author Test")
                .isbn("0001")
                .description("Description test")
                .categoryId(1)
                .build();

        var bookMK = bookService.create(bookForm);
        assertThat(book, is(bookMK));

    }

    @Test
    @DisplayName("Test create Error")
    void when_create_book_with_invalid_category_it_should_return_throw_exception(){

        doThrow(ResourceNotFoundException.class).when(validation).verifyIfCategoryExists(anyInt());

        BookForm bookForm = BookForm.builder()
                .title("Book Test")
                .author("Author Test")
                .isbn("0001")
                .description("Description test")
                .categoryId(1)
                .build();

        Assertions.assertThrows(ResourceNotFoundException.class, () ->{
            bookService.create(bookForm);
        });

    }

    @Test
    @DisplayName("Test update Success")
    void when_update_book_it_should_return_book(){

        when(bookRepository.save(any(Book.class))).thenReturn(bookUpdate);
        when(bookRepository.findById(anyInt())).thenReturn(Optional.ofNullable(book));

        when(categoryService.getCategory(anyInt())).thenReturn(category);
        doNothing().when(validation).verifyIfCategoryExists(anyInt());

        BookForm bookForm = BookForm.builder()
                .title("Book Test")
                .author("Author Test")
                .isbn("0001")
                .description("Description test")
                .categoryId(1)
                .build();

        var bookMK = bookService.update(1, bookForm);
        assertThat(bookUpdate, is(bookMK));

    }

    @Test
    @DisplayName("Test update Error")
    void when_update_book_with_invalid_category_it_should_return_throw_exception(){

        doThrow(ResourceNotFoundException.class).when(validation).verifyIfCategoryExists(anyInt());

        BookForm bookForm = BookForm.builder()
                .title("Book Test 2")
                .author("Author Test")
                .isbn("0001")
                .description("Description test")
                .categoryId(1)
                .build();

        Assertions.assertThrows(ResourceNotFoundException.class, () ->{
            bookService.update(1, bookForm);
        });

    }

    @Test
    @DisplayName("Test update Error")
    void when_update_book_with_invalid_book_it_should_return_throw_exception(){

        doThrow(ResourceNotFoundException.class).when(validation).verifyIfBookExists(anyInt());

        BookForm bookForm = BookForm.builder()
                .title("Book Test 2")
                .author("Author Test")
                .isbn("0001")
                .description("Description test")
                .categoryId(1)
                .build();

        Assertions.assertThrows(ResourceNotFoundException.class, () ->{
            bookService.update(1, bookForm);
        });

    }

    @Test
    @DisplayName("Test update Error")
    void when_update_book_rented_it_should_return_throw_exception(){

        doThrow(BookAlreadyRentedException.class).when(validation).verifyIfBookIsRented(anyInt());

        BookForm bookForm = BookForm.builder()
                .title("Book Test 2")
                .author("Author Test")
                .isbn("0001")
                .description("Description test")
                .categoryId(1)
                .build();

        Assertions.assertThrows(BookAlreadyRentedException.class, () ->{
            bookService.update(1, bookForm);
        });

    }

    @Test
    @DisplayName("Test delete Success")
    void when_delete_book_it_should_return_book(){
        when(bookRepository.save(any(Book.class))).thenReturn(bookDelete);
        when(bookRepository.findById(anyInt())).thenReturn(Optional.ofNullable(book));
        var bookMK = bookService.delete(1);
        assertThat(bookDelete, is(bookMK));
    }

    @Test
    @DisplayName("Test delete Error")
    void when_delete_book_with_invalid_book_it_should_return_throw_exception(){
        doThrow(ResourceNotFoundException.class).when(validation).verifyIfBookExists(anyInt());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->{
            bookService.delete(1);
        });
    }

    @Test
    @DisplayName("Test delete Error")
    void when_delete_book_rented_it_should_return_throw_exception(){
        doThrow(BookAlreadyRentedException.class).when(validation).verifyIfBookIsRented(anyInt());
        Assertions.assertThrows(BookAlreadyRentedException.class, () ->{
            bookService.delete(1);
        });
    }

}
