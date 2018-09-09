package com.humblecode.humblecode.web;

import com.humblecode.humblecode.data.CourseRepository;
import com.humblecode.humblecode.model.Course;
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
    CourseRepository courseRepository;

    @PostConstruct
    public void setup() {
        courseRepository.count().blockOptional().filter(count -> count == 0).ifPresent(it ->
            Flux.just(
                    new Course("Beginning Java"),
                    new Course("Advanced Java"),
                    new Course("Reactive Streams in Java"))
                .doOnNext(c -> System.out.println(c.toString()))
                .flatMap(courseRepository::save)
                .subscribe() // need to actually execute save*/
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
