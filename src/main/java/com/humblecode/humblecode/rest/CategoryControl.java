package com.humblecode.humblecode.rest;

import com.humblecode.humblecode.data.CategoryRepository;
import com.humblecode.humblecode.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@RestController
public class CategoryControl {

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping(value = "/api/category", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<String> addCategory(@RequestBody Map body) {
        return Mono.<String> create(callback ->{
            String name = (String) body.get("name");
            String d = (String) body.get("description");
            Category category = new Category(name, d);

            categoryRepository.save(category).doOnError(ex -> callback.error((Throwable) ex))
                    .doOnSuccess(s -> callback.success("Saved"))
                    .block();
        });
    }

    @DeleteMapping("/api/category/{id}")
    public Mono<Void> deleteCategory(@RequestParam("id") String id) {
        return categoryRepository.deleteById(UUID.fromString(id));
    }

    @GetMapping("/api/categories")
    public Flux<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping(value = "/api/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Category> getCategory(@RequestParam("id") String id) {
        return categoryRepository.findById(UUID.fromString(id));
    }
    
    @PutMapping(value = "/api/category/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Category> updateCategory(@RequestParam("id") String id, @RequestBody Category updatedCategory) {

        Mono<Category> categoryMono = categoryRepository.findById(UUID.fromString(id));

        return categoryMono.flatMap(category -> {
            category.name = updatedCategory.name;
            category.description = updatedCategory.description;
            category.setCourseLinks(updatedCategory.courseLinks);
            return categoryRepository.save(category);
        });
    }

}
