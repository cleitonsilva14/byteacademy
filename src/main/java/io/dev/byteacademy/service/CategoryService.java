package io.dev.byteacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.dev.byteacademy.exception.CategoryNotFoundException;
import io.dev.byteacademy.model.Category;
import io.dev.byteacademy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> saveAll(List<Category> categories){
        return categoryRepository.saveAll(categories);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category id:{%s} not found!".formatted(id)));
    }

}
