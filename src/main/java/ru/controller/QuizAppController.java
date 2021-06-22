package ru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/QuizApp")
public class QuizAppController {

    @GetMapping("/")
    public String home() {
        return "/QuizAppController/QuizAppHomePage";
    }
}
