package ru.controller;


import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.config.MyUserImpl;
import ru.dto.UserQuestionAnswerDTO;
import ru.entity.*;
import ru.service.QuestionService;
import ru.service.QuizService;
import ru.service.UserAnswerService;

import java.util.*;


@Controller
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;
    private final UserAnswerService userAnswerService;

    public QuizController(QuizService quizService, QuestionService questionService, UserAnswerService userAnswerService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.userAnswerService = userAnswerService;
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
    @PreAuthorize("hasRole('ADMIN')")
    public String createQuizGet(@ModelAttribute("quiz") Quiz quiz) {
        return "/QuizController/createQuizGet";
    }

    @PostMapping("createQuiz")
    @PreAuthorize("hasRole('ADMIN')")
    public String createQuiz(@ModelAttribute("quiz") Quiz quiz) {
        quizService.createQuiz(quiz);
        return "/QuizController/createQuizPost";
    }

    @GetMapping("deleteQuiz")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteQuizGet(Model model) {
        List<Quiz> list = quizService.getAll();
        model.addAttribute("quizzes", list);
        model.addAttribute("quizz", new Quiz());
        return "/QuizController/deleteQuizGet";
    }

    @PostMapping("deleteQuiz")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteQuiz(@ModelAttribute("quiz") Quiz quiz) {
        quizService.deleteQuiz(quiz.getId());
        questionService.deleteQuestionByQuizId(quiz.getId());
        return "/QuizController/deleteQuizPost";
    }


    @GetMapping("/updateQuiz")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateQuiz(Authentication authentication, Model model) {
        List<Quiz> list = quizService.getAll();
        model.addAttribute("quizzes", list);
        return "/QuizController/updateQuizGet";
    }

    @GetMapping("/updateQuiz/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public String updatedQuiz(@ModelAttribute("quiz") Quiz quiz) {
        System.out.println(quiz.getId());
        List<Question> questions = quiz.getQuestionList();
        for (Question x:questions) {
            List<QuestionOption> questionOptions = x.getQuestionOptions();
            for (QuestionOption c: questionOptions ) {
                System.out.println(c.getQuestionId());
            }
        }
        quizService.updateQuiz(quiz);
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
        return "/QuizController/takeQuiz{id}old";
    }

    @ResponseBody
    @PostMapping(value = "/takeQuiz", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String passedTest(@RequestBody UserAnswer map) {
//       /----old----/
//        List<UserAnswer> userAnswers = new ArrayList<>();
//        List<String> list = dto.getList();
//        for (String c : list) {
////            String [] arr = c.split("-");
////            UserAnswer userAnswer = new UserAnswer();
////            userAnswer.setQuestionId(Integer.parseInt(arr[0]));
////            try {
////                userAnswer.setQuestionOptionId(Integer.parseInt(arr[1]));
////            }catch (Exception e){
////                userAnswer.setText(arr[1]);
////            }
////
////            userAnswers.add(userAnswer);
//        }
        //-----------old--------/
//        MyUserImpl userDetails = (MyUserImpl) authentication.getPrincipal();
//        int userId = userDetails.getId();
//        List<UserAnswer> userAnswersForBatch = new ArrayList<>();
//        for (Map.Entry<Integer, List<UserAnswer>> entry : map.entrySet()) {
//            List<UserAnswer> userAnswers = entry.getValue();
//            System.out.println(entry.getKey());
//            for (UserAnswer x : userAnswers) {
//                System.out.println(x.getQuestionOptionId());
//                x.setQuestionId(entry.getKey());
////                x.setUserId(userId);
//                userAnswersForBatch.add(x);
//            }

//        }

//        userAnswerService.batchCreateUserAnswers(userAnswersForBatch);
        return "/";
    }

}
