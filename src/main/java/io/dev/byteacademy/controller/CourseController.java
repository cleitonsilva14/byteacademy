package io.dev.byteacademy.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dev.byteacademy.model.Course;
import io.dev.byteacademy.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        // return courseService.findById(id)
        //         .map(c -> ResponseEntity.ok().body(c))
        //         .orElse(ResponseEntity.notFound().build());
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Course course) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseService.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Course newCourse) {
        var course = courseService.updateCourse(id, newCourse);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!courseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
