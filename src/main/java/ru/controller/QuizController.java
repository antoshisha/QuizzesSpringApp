package ru.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.entity.Question;
import ru.entity.QuestionOption;
import ru.entity.Quiz;
import ru.entity.User;
import ru.service.QuestionService;
import ru.service.QuizService;

import java.util.List;


@Controller
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;

    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
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
    public String getQuizzesForUserId(@ModelAttribute("user") User user) {
        return "/QuizController/getQuizzesForUserIdGet";
    }

    @PostMapping("getQuizzesForUserId")
    public String getQuizzesForUserIdPost(@ModelAttribute("user") User user, Model model) {
        List<Quiz> quizzes = quizService.getQuizForUserId(user.getId());
        model.addAttribute("quizzes", quizzes);
        for (Quiz z: quizzes) {
            System.out.println(z.getName());
        }
        return "/QuizController/getQuizzesForUserIdPost";
    }

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
        quizService.deleteQuiz(quiz.getId());
        questionService.deleteQuestionByQuizId(quiz.getId());
        return "/QuizController/deleteQuizPost";
    }


    @PostMapping("updateQuiz")
    public String updateQuiz() {
        return "updateQuiz";
    }

    @GetMapping("/takeQuiz")
    public String takeQuiz(Model model) {
        List<Quiz> list = quizService.getAll();
        model.addAttribute("quizzes", list);
        return "/QuizController/takeQuizGet";
    }

    @GetMapping("/takeQuiz/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Quiz quiz = quizService.takeQuiz(id);
        model.addAttribute("quiz", quiz);
        return "/QuizController/takeQuiz{id}";
    }

}
