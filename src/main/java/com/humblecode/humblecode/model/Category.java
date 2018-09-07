package com.humblecode.humblecode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Document
public class Category {

    @Id
    UUID id = UUID.randomUUID();
    public String name;
    public String description;

    public final List<CourseLink> courseLinks = new ArrayList<>();

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addCourse(String courseName, UUID id) {
        courseLinks.add(new CourseLink(id, courseName));
    }

    public void deleteCourse(UUID id) {
        int i = courseLinks.indexOf(new CourseLink(id, ""));
        if (i >= 0) courseLinks.remove(i);
    }

    public void deleteCourse(CourseLink link) {
        courseLinks.remove(link);
    }

    public void updateCourse(CourseLink course) {
        int i = courseLinks.indexOf(new CourseLink(id, ""));
        courseLinks.remove(i);
        courseLinks.add(i, course);
    }

    public void setCourseLinks(Collection<CourseLink> links) {
        this.courseLinks.clear();
        this.courseLinks.addAll(links);
    }
}
