package ru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.entity.Question;
import ru.entity.QuestionOption;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {
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
        return"/QuestionController/createQuestionPost";
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
