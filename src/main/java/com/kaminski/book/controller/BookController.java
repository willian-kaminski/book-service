package com.kaminski.book.controller;

import com.kaminski.book.entity.Book;
import com.kaminski.book.model.dto.BookDTO;
import com.kaminski.book.model.dto.BookDetailDTO;
import com.kaminski.book.model.form.BookForm;
import com.kaminski.book.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {

    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll(
            @RequestParam(required = false, defaultValue = "") String filter){

        List<Book> books = bookService.getAll(filter);
        return ResponseEntity.status(HttpStatus.OK).body(BookDTO.converterEntityToDTO(books));

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailDTO> findById(@PathVariable Integer id){
        var book = bookService.getBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(new BookDetailDTO(book));
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody @Valid BookForm bookForm){
        Book book = bookService.create(bookForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BookDTO(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Integer id, @RequestBody @Valid BookForm bookForm){
        Book book = bookService.update(id, bookForm);
        return ResponseEntity.status(HttpStatus.OK).body(new BookDTO(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteById(@PathVariable Integer id){

        var book = bookService.delete(id);

        if(book == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.status(HttpStatus.OK).body(new BookDTO(book));

    }

}