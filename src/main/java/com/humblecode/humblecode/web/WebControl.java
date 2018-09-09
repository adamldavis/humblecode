package com.humblecode.humblecode.web;

import com.humblecode.humblecode.data.CategoryRepository;
import com.humblecode.humblecode.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.security.Principal;

@Controller
public class WebControl {

    @Value("${app.name}")
    String appName;

    @Autowired
    CategoryRepository categoryRepository;

    @PostConstruct
    public void setup() {
        categoryRepository.count().blockOptional().filter(count -> count == 0).ifPresent(it ->
            Flux.just(
                new Category("Beginning Java", "The most commonly used programming language in the world."),
                new Category("Advanced Java", "More advanced Java topics."),
                new Category("Groovy", "Super awesome optionally dynamic language on the JVM."))
                .doOnNext(c -> System.out.println(c.toString()))
                .flatMap(categoryRepository::save)
                .blockLast() // need to actually execute save*/
        );
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam(value="name", required=false, defaultValue="") String name) {
        model.addAttribute("name", name);
        model.addAttribute("applicationName", appName);
        return "home";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("applicationName", appName);
        model.addAttribute("error", "Login failed.");
        return "login";
    }

    @GetMapping("/user/account")
    public String userAccount(Model model, Principal principal) {
        model.addAttribute("applicationName", appName);
        model.addAttribute("username", principal.getName());
        return "account";
    }
}
