CREATE TABLE answers (
                      id BIGINT NOT NULL auto_increment,
                      answerA VARCHAR(255),
                      answerB VARCHAR(255),
                      answerC VARCHAR(255),
                      answerD VARCHAR(255),
                      aCorrect BOOLEAN,
                      isBCorrect BOOLEAN,
                      isCCorrect BOOLEAN,
                      isDCorrect BOOLEAN,
                      answerFromUser VARCHAR(255),
                      CONSTRAINT PK_Answers PRIMARY KEY (id));


CREATE TABLE authors (
                      id BIGINT NOT NULL auto_increment,
                      name VARCHAR(255),
                      surname VARCHAR(255),
                      email VARCHAR(255),
                      quizListSize BIGINT,
                      CONSTRAINT PK_Authors PRIMARY KEY (id));


CREATE TABLE question (
                      id BIGINT NOT NULL auto_increment,
                      content VARCHAR(255),
                      CONSTRAINT PK_Question PRIMARY KEY (id));

CREATE TABLE quiz(
  id BIGINT NOT NULL auto_increment,
  name VARCHAR(255),
  sizeOfQuestionList BIGINT,
  numberOfSolved BIGINT,
  CONSTRAINT PK_Quiz PRIMARY KEY (id));







