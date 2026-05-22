package io.dev.byteacademy.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.dev.byteacademy.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findByNameIn(Collection<String> names);
    
}
