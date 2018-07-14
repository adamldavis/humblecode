package com.humblecode.humblecode.data;

import com.humblecode.humblecode.model.Course;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CourseRepository extends ReactiveMongoRepository<Course, UUID> {

    Flux<Course> findByCategoryId(UUID categoryId);

    Flux<Course> findByName(String name);

}
