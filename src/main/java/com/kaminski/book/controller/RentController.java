package com.kaminski.book.controller;

import com.kaminski.book.model.dto.BookDetailDTO;
import com.kaminski.book.model.form.RentalForm;
import com.kaminski.book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/rentals")
public class RentController {

    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDetailDTO> rentBook(@RequestBody @Valid RentalForm rentalForm){
        return ResponseEntity.status(HttpStatus.OK).body(
                new BookDetailDTO(bookService.rentBook(rentalForm.getBookId())));
    }

}
