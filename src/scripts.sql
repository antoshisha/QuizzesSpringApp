CREATE TABLE quiz (
                      id INTEGER NOT NULL PRIMARY KEY,
                      name VARCHAR,
                      start DATE,
                      finish DATE,
                      description VARCHAR);

CREATE TABLE question_type (
                               id INTEGER NOT NULL PRIMARY KEY,
                               name VARCHAR);

CREATE TABLE question (
                          id INTEGER NOT NULL PRIMARY KEY,
                          name VARCHAR,
                          quiz_id INTEGER REFERENCES quiz(id),
                          type INTEGER REFERENCES question_type(id));

CREATE TABLE answer_option (
                               id INTEGER NOT NULL PRIMARY KEY,
                               question_id INTEGER REFERENCES question(id),
                               option VARCHAR);

CREATE TABLE users (
                       id INTEGER NOT NULL PRIMARY KEY,
                       name_is_anonymous BOOLEAN);

CREATE TABLE user_past_quizzes (
                                   user_id INTEGER REFERENCES users(id),
                                   quiz_id INTEGER REFERENCES quiz(id));

CREATE TABLE user_answer (
                             id INTEGER NOT NULL PRIMARY KEY,
                             user_id INTEGER REFERENCES users(id),
                             question_id INTEGER REFERENCES question(id),
                             text VARCHAR,
                             answer_option_id INTEGER REFERENCES answer_option(id));