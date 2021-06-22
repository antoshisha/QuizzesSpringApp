package ru.dao;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.config.SpringConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class QuestionDAOImplTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    QuestionDAOImpl questionDAO;

//    void ShouldDeleteQuestion(int id) {
//        jdbcTemplate.query("INSERT INTO");
//    }

}