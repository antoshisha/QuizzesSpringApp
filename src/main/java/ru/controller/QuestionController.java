package ru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.entity.Question;
import ru.entity.QuestionOption;
import ru.service.QuestionService;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/")
    public String homePage() {
        return "QuestionController/QuestionHomePage";
    }
    @GetMapping("createQuestion")
    public String createQuestion(Model model) {
        Question question = new Question();
        for (int i = 0; i < 4; i++) {
            question.addQuestionOption(new QuestionOption());
        }
        model.addAttribute("question", question);
        return "/QuestionController/createQuestionGet";
    }
    @PostMapping("createQuestion")
    public String createQuestionPost (@ModelAttribute("question") Question question){
        System.out.println(question.getName());
        System.out.println(question.getQuizId());
        System.out.println(question.getQuestionType());
        List<QuestionOption> questionOptions = question.getQuestionOptions();
        for (QuestionOption x:         questionOptions) {
            System.out.println(x.getOption());
        }
        questionService.createQuestion(question, question.getQuizId());
        return"/QuestionController/createQuestionPost";
    }

    @GetMapping("/updateQuestion")
    public String updateQuestion(Model model) {
        model.addAttribute("questions", questionService.getAllQuestions());
        return "/QuestionController/updateQuestionGet";
    }

    @GetMapping("updateQuestion/{id}")
    public String updateQuestionId(@PathVariable("id") int id, Model model) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "/QuestionController/updateQuestion{id}Get";
    }


    @GetMapping("/deleteQuestion")
    public String deleteQuestion(@ModelAttribute("question") Question question) {
        return "/QuestionController/deleteQuestionGet";
    }

    @PostMapping("/deleteQuestion")
    public String deleteQuestionPost(@ModelAttribute("question") Question question) {
        questionService.deleteQuestion(question.getId());
        return "/QuestionController/deleteQuestionPost";
    }
}
