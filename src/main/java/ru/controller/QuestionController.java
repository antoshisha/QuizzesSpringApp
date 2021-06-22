package ru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @PostMapping("createQuestion")
    public String createQuestion (){
        return"createQuestion";
    }

    @PostMapping("updateQuestion")
    public String updateQuestion() {
        return "updateQuestion";
    }

    @DeleteMapping("deleteQuestion")
    public String deleteQuestion() {
        return "deleteQuestion";
    }
}
