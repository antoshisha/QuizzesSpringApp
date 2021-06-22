package ru.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.entity.Quiz;
import ru.service.QuizService;

import java.util.List;


@Controller
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String home() {
        return "QuizController/homePage";
    }
    @GetMapping("/getAll")
    public String getAll(Model model) {
        List<Quiz> list = quizService.getAll();
        model.addAttribute("quizzes", list);
        return "QuizController/getAll";
    }

    @GetMapping("/getQuizzesForUserId")
    public String getQuizzesForUserId(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "QuizController/getQuizzesForUserIdGet";
    }

//    @PostMapping("getQuizzesForUserId")
//    public String getQuizzesForUserIdPost(@ModelAttribute Quiz quiz) {
//        quizService.
//    }

    @PostMapping("saveQuiz")
    public String saveQuiz() {
        return "saveQuiz";
    }

    @GetMapping("/createQuiz")
    public String createQuizGet(@ModelAttribute("quiz") Quiz quiz) {
        return "/QuizController/createQuizGet";
    }
    @PostMapping("createQuiz")
    public String createQuiz(@ModelAttribute("quiz") Quiz quiz) {
        quizService.createQuiz(quiz);
        return "/QuizController/createQuizPost";
    }

    @GetMapping("deleteQuiz")
    public String deleteQuizGet(@ModelAttribute("quiz") Quiz quiz) {
        return "/QuizController/deleteQuizGet";
    }
    @PostMapping("deleteQuiz")
    public String deleteQuiz(@ModelAttribute("quiz") Quiz quiz) {
        return "/QuizController/deleteQuizPost";
    }


    @PostMapping("updateQuiz")
    public String updateQuiz() {
        return "updateQuiz";
    }
}
