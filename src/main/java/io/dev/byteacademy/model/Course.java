package io.dev.byteacademy.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.dev.byteacademy.enums.CourseLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "course")
public class Course {
    
    private String id;
    
    @Indexed(unique = true)
    private String title;
    private Double duration;

    private CourseLevel level;

}
