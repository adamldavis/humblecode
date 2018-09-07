package com.humblecode.humblecode.model;

import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class CourseLink {

    public UUID id;
    public String name;

    public CourseLink() {
    }

    public CourseLink(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseLink)) return false;
        CourseLink that = (CourseLink) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
