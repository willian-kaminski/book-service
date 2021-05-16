package com.kaminski.book.service.implementation;

import com.kaminski.book.entity.Category;
import com.kaminski.book.repository.CategoryRepository;
import com.kaminski.book.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public Category getCategory(Integer id) {

        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent())
            return category.get();

        return null;

    }

}