package com.kaminski.book.model.dto;

import com.kaminski.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class BookDTO {

    private int id;
    private String title;
    private String status;

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.status = book.getStatus();
    }

    public static List<BookDTO> converterEntityToDTO(List<Book> books){
        return books.stream().map(BookDTO::new).collect(Collectors.toList());
    }

}