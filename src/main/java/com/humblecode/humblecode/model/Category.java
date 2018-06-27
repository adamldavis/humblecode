package com.humblecode.humblecode.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

    String name;
    String description;

    final List<Course> courses = new ArrayList<>();

    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Course> getCourses() {
        return courses;
    }

}
