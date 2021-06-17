package ru.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import ru.config.SpringConfig;
import ru.entity.UserAnswer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringConfig.class })
class UserAnswerDAOImplTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserAnswerDAOImpl userAnswerDAO;



    @Test
    public void shouldCreateUserAnswer() {
        UserAnswer userAnswer = new UserAnswer(99, 1,1,"test_answer", 0);
        userAnswerDAO.createUserAnswer(userAnswer);

        UserAnswer userAnswer1 =  jdbcTemplate.query(
                "SELECT * FROM user_answer WHERE id=?",
                new Object[]{userAnswer.getUserAnswerId()}, new UserAnswerMapper())
                .stream().findAny().orElse(null);

        jdbcTemplate.update("DELETE FROM user_answer WHERE id = ?", userAnswer.getUserAnswerId());

        Assertions.assertEquals(userAnswer.getUserAnswerId(), userAnswer1.getUserAnswerId());

    }

}