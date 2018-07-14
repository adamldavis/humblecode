package com.humblecode.humblecode.data;

import com.humblecode.humblecode.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CategoryRepository extends ReactiveMongoRepository<Category, UUID> {

    Flux<Category> findByName(String name);

}
