package io.dev.byteacademy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.dev.byteacademy.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Long> {
    
}
