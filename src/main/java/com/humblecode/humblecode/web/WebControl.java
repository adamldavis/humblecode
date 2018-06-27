package com.humblecode.humblecode.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebControl {

    @Value("${app.name}")
    String appName;

    @GetMapping("/")
    public String home(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        model.addAttribute("applicationName", appName);
        return "home";
    }

    @GetMapping("/category")
    public String category(Model model, @RequestParam("name") String name,
                           @RequestParam(value="username", required = false) String username) {
        model.addAttribute("name", name);
        model.addAttribute("username", username);

        return "category";
    }

    @GetMapping("/categoryList")
    public String categoryList(Model model, @RequestParam(value="username", required = false) String username) {
        model.addAttribute("username", username);

        return "categoryList";
    }
}
