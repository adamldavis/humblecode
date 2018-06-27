package com.humblecode.humblecode.rest;

import com.humblecode.humblecode.model.Category;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class CategoryControl {


    @PostMapping(value = "/api/category", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<String> addCategory(@RequestBody Map body) {
        return Mono.<String> create(callback ->{
            String name = (String) body.get("name");
            String d = (String) body.get("description");
            Category category = new Category(name, d);
            //TODO save
            callback.success("Saved");
        });
    }

    @DeleteMapping("/api/category/{id}")
    public Mono<Integer> deleteCategory(@RequestParam("id") Long id) {
        //TODO
        return Mono.just(1);
    }

    @GetMapping("/api/categories")
    public Flux<Category> getCategories() {
        return Flux.just(
                new Category("Beginning Java", "The most commonly used programming language in the world."),
                new Category("Advanced Java", "More advanced Java topics."),
                new Category("Groovy", "Super awesome optionally dynamic language on the JVM."));
    }

}
