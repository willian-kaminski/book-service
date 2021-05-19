package com.kaminski.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaminski.book.entity.Book;
import com.kaminski.book.entity.Category;
import com.kaminski.book.model.BookStatus;
import com.kaminski.book.model.form.BookForm;
import com.kaminski.book.service.implementation.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(value = MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookServiceImpl bookService;

    private MockMvc mockMvc;

    @BeforeEach
    public void onInit(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BookController(bookService)).build();
    }

    @Test
    @DisplayName("Test create sucess")
    void when_create_book_it_should_return_201() throws Exception {

        Book book = Book.builder()
                .id(1)
                .title("Book Test")
                .author("Book Author")
                .isbn("0001")
                .status(BookStatus.AVAILABLE.toString())
                .description("description test")
                .category(new Category(1, "ACAO"))
                .build();

        BookForm bookForm = BookForm.builder()
                .title("Book Test")
                .author("Book Author")
                .isbn("0001")
                .description("description test")
                .categoryId(1)
                .build();

        when(bookService.create(any(BookForm.class))).thenReturn(book);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(bookForm);

        this.mockMvc.perform(post("/api/v1/books").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Book Test")));

    }

    @Test
    @DisplayName("Test create error")
    void when_create_book_without_title_it_should_return_400() throws Exception {

        BookForm bookForm = BookForm.builder()
                .title(null)
                .author("Book Author")
                .isbn("0001")
                .description("description test")
                .categoryId(1)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(bookForm);

        this.mockMvc.perform(post("/api/v1/books").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Test update sucess")
    void when_update_book_it_should_return_200() throws Exception {

        Book book = Book.builder()
                .id(1)
                .title("Book Test")
                .author("Book Author")
                .isbn("0001")
                .status(BookStatus.AVAILABLE.toString())
                .description("description test")
                .category(new Category(1, "ACAO"))
                .build();

        Book bookUpdate = Book.builder()
                .id(1)
                .title("Book Test 1")
                .author("Book Author")
                .isbn("0001")
                .status(BookStatus.AVAILABLE.toString())
                .description("description test")
                .category(new Category(1, "ACAO"))
                .build();

        BookForm bookForm = BookForm.builder()
                .title("Book Test 1")
                .author("Book Author")
                .isbn("0001")
                .description("description test")
                .categoryId(1)
                .build();

        when(bookService.update(anyInt(), any(BookForm.class))).thenReturn(bookUpdate);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(bookForm);

        this.mockMvc.perform(put("/api/v1/books/1").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Book Test 1")));

    }

    @Test
    @DisplayName("Test update error")
    void when_update_book_without_title_it_should_return_400() throws Exception {

        BookForm bookForm = BookForm.builder()
                .title(null)
                .author("Book Author")
                .isbn("0001")
                .description("description test")
                .categoryId(1)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(bookForm);

        this.mockMvc.perform(put("/api/v1/books/1").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

}
