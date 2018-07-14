package com.humblecode.humblecode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Document
public class Category {

    @Id
    UUID id = UUID.randomUUID();
    public String name;
    public String description;

    // names of courses
    final List<String> courses = new ArrayList<>();

    final List<UUID> courseIds = new ArrayList<>();

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addCourse(String courseName, UUID id) {
        this.courses.add(courseName);
        this.courseIds.add(id);
    }

    public void deleteCourse(UUID id) {
        int i = courseIds.indexOf(id);
        courses.remove(i);
        courseIds.remove(id);
    }

    public void updateCourse(Course course) {
        int i = courseIds.indexOf(course.id);
        courses.remove(i);
        courses.add(i, course.name);
    }
}
