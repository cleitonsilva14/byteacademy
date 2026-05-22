package io.dev.byteacademy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.dev.byteacademy.exception.CategoryNotFoundException;
import io.dev.byteacademy.exception.CourseNotFoundException;
import io.dev.byteacademy.model.Category;
import io.dev.byteacademy.model.Course;
import io.dev.byteacademy.repository.CategoryRepository;
import io.dev.byteacademy.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    public Course save(Course course) {

        if (course.getCategory() == null || course.getCategory().getId() == null
                || course.getCategory().getId().isBlank()) {
            throw new IllegalArgumentException("A categoria do curso e o seu ID são obrigatórios!");
        }

        String categoryId = course.getCategory().getId();
        Category categoryCompleta = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));

        course.setCategory(categoryCompleta);

        return courseRepository.save(course);
    }

    public Course updateCourse(String id, Course newCourse) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + id));

        if (newCourse.getCategory() != null && newCourse.getCategory().getId() != null) {
            var category = categoryRepository.findById(newCourse.getCategory().getId()).orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + id));
            course.setCategory(category);
        } else {
            throw new IllegalArgumentException("Category and Category ID are required for update.");
        }

        course.setTitle(newCourse.getTitle());
        course.setDuration(newCourse.getDuration());
        course.setLevel(newCourse.getLevel());

        return courseRepository.save(course);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(String id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course id:{%s} not found!".formatted(id)));
    }

    public boolean existsById(String id) {
        return courseRepository.existsById(id);
    }

    public void deleteById(String id) {
        courseRepository.deleteById(id);
    }

}
