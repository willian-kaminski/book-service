package com.kaminski.book.utils;

import com.kaminski.book.entity.Book;
import com.kaminski.book.entity.Category;
import com.kaminski.book.entity.Profile;
import com.kaminski.book.exception.BookAlreadyRentedException;
import com.kaminski.book.exception.ResourceNotFoundException;
import com.kaminski.book.model.BookStatus;
import com.kaminski.book.repository.BookRepository;
import com.kaminski.book.repository.CategoryRepository;
import com.kaminski.book.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class Validation {

    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;
    private ProfileRepository profileRepository;

    public void verifyIfBookExists(Integer id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty())
            throw new ResourceNotFoundException(String.format("Book not found for id %s.", id));
    }

    public void verifyIfProfileExists(Integer id){
        Optional<Profile> profile = profileRepository.findById(id);
        if(profile.isEmpty())
            throw new ResourceNotFoundException(String.format("Profile not found for id %s.", id));
    }

    public void verifyIfProfileExists(String name){
        Optional<Profile> profile = profileRepository.findByName(name);
        if(profile.isEmpty())
            throw new ResourceNotFoundException(String.format("Profile not found for name %s.", name));
    }

    public void verifyIfBookIsRented(Integer id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent() && isBookRented(book.get())) {
            throw new BookAlreadyRentedException(String.format("Book for id %s is rented.", id));
        }
    }

    public void verifyIfCategoryExists(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty())
            throw new ResourceNotFoundException(String.format("Category not found for id %s.", id));
    }

    private static boolean isBookRented(Book book){
        return book.getStatus().equals(BookStatus.RENTED.toString());
    }

}
