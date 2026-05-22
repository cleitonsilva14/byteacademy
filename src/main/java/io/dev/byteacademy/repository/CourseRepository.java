package io.dev.byteacademy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.dev.byteacademy.model.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
}
