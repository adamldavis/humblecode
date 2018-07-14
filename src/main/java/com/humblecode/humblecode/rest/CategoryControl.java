package com.humblecode.humblecode.rest;

import com.humblecode.humblecode.data.CategoryRepository;
import com.humblecode.humblecode.model.Category;
import com.humblecode.humblecode.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
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

    @GetMapping("/api/category/{id}")
    public Mono<Category> getCategory(@RequestParam("id") String id) {
        return categoryRepository.findById(UUID.fromString(id));
    }
    
    @PutMapping(value = "/api/category/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Category> updateCategory(@RequestParam("id") String id, @RequestBody Map body) {

        Mono<Category> categoryMono = categoryRepository.findById(UUID.fromString(id));

        return categoryMono.flatMap(category -> {
            if (body.containsKey("name")) category.name = (String) body.get("name");
            if (body.containsKey("description")) category.description = (String) body.get("description");
            //
            return categoryRepository.save(category);
        });
    }

}
