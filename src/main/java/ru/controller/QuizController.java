package ru.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dto.UserQuestionAnswerDTO;
import ru.entity.*;
import ru.service.QuestionService;
import ru.service.QuizService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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


    @GetMapping("/updateQuiz")
    public String updateQuiz(Model model) {
        List<Quiz> list = quizService.getAll();
        model.addAttribute("quizzes", list);
        return "/QuizController/updateQuizGet";
    }

    @GetMapping("/updateQuiz/{id}")
    public String updateQuizById(@PathVariable("id") int id, Model model) {
        Quiz quiz = quizService.takeQuiz(id);
        System.out.println(quiz.getId());
        List<Question> questions = quiz.getQuestionList();
        for (Question x:questions) {
            List<QuestionOption> questionOptions = x.getQuestionOptions();
            for (QuestionOption c: questionOptions ) {
                System.out.println(c.getId() + " id option get");
            }
        }
        model.addAttribute("quiz", quiz);
        return "/QuizController/updateQuiz{id}";
    }

    @PatchMapping("/updateQuiz")
    public String updatedQuiz(@ModelAttribute("quiz") Quiz quiz) {
//        quizService.updateQuiz(quiz);
        System.out.println(quiz.getId());
        List<Question> questions = quiz.getQuestionList();
        for (Question x:questions) {
            List<QuestionOption> questionOptions = x.getQuestionOptions();
            for (QuestionOption c: questionOptions ) {
                System.out.println(c.getId() + "id option patch");
            }
        }
        System.out.println(quiz.getName() + " from patch");
        return "QuizController/updateQuizPost";
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
        List<Question> questions = quiz.getQuestionList();
        UserQuestionAnswerDTO userQuestionAnswerDTO = new UserQuestionAnswerDTO();
        for (Question x : questions) {
            userQuestionAnswerDTO.addQuestion(x);
            System.out.println(x.getQuestionType());
            userQuestionAnswerDTO.addUserAnswer(new UserAnswer());
        }
        model.addAttribute("userQuestionAnswerDTO", userQuestionAnswerDTO);
        return "/QuizController/takeQuiz{id}";
    }

    @PostMapping("/takeQuiz")
    public String passedTest(@ModelAttribute("userQuestionAnswerDTO") UserQuestionAnswerDTO dto) {
        List<UserAnswer> userAnswers = new ArrayList<>();
        List<String> list = dto.getList();
        for (String c : list) {
//            String [] arr = c.split("-");
//            UserAnswer userAnswer = new UserAnswer();
//            userAnswer.setQuestionId(Integer.parseInt(arr[0]));
//            try {
//                userAnswer.setQuestionOptionId(Integer.parseInt(arr[1]));
//            }catch (Exception e){
//                userAnswer.setText(arr[1]);
//            }
//
//            userAnswers.add(userAnswer);
            System.out.println(c);

        }
        System.out.println(dto.getIdListForTextAnswer().isEmpty());
        for (Integer x: dto.getIdListForTextAnswer()) {
            System.out.println(x);
        }
        for (String z: dto.getTextList()) {
            System.out.println(z);
        }

        return "/";
    }

}
