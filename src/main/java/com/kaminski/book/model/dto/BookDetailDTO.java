package com.kaminski.book.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaminski.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class BookDetailDTO {

    private int id;
    private String title;
    private String isbn;
    private String description;
    private String author;
    private String status;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date createAt;

    private CategoryDTO category;

    public BookDetailDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.description = book.getDescription();
        this.author = book.getAuthor();
        this.status = book.getStatus();
        this.createAt = book.getCreateAt();
        this.category = new CategoryDTO(book.getCategory());
    }

    public static List<BookDetailDTO> converterEntityToDTO(List<Book> books){
        return books.stream().map(BookDetailDTO::new).collect(Collectors.toList());
    }

}