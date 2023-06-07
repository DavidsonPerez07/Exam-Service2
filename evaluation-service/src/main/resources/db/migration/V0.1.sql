CREATE TABLE Exam (
  id_exam BIGINT NOT NULL AUTO_INCREMENT,
  introduction VARCHAR(1000),
  max_score DOUBLE NOT NULL,
  exam_link VARCHAR(500) NOT NULL,
  PRIMARY KEY (id_exam)
);

CREATE TABLE Question (
  id_question BIGINT NOT NULL AUTO_INCREMENT,
  question_description VARCHAR(1000) NOT NULL,
  assessment DOUBLE NOT NULL,
	question_type INTEGER NOT NULL,
  id_exam BIGINT NOT NULL,
  PRIMARY KEY (id_question),
  CONSTRAINT fk_Question_Exam
    FOREIGN KEY (id_exam)
    REFERENCES Exam (id_exam)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE ExamOption (
  id_option BIGINT NOT NULL AUTO_INCREMENT,
  option_description VARCHAR(1000) NOT NULL,
  is_correct BOOLEAN NOT NULL,
  id_question BIGINT NOT NULL,
  PRIMARY KEY (id_option),
  CONSTRAINT fk_Option_Question
    FOREIGN KEY (id_question)
    REFERENCES Question (id_question)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Student (
  id_student BIGINT NOT NULL AUTO_INCREMENT,
  id_card VARCHAR(45) NOT NULL,
  student_name VARCHAR(100),
  email VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_student)
);

CREATE TABLE ExamPresentation (
  id_exam_presentation BIGINT NOT NULL AUTO_INCREMENT,
  score DOUBLE NOT NULL,
  id_student BIGINT,
  id_exam BIGINT,
  PRIMARY KEY (id_exam_presentation),
  CONSTRAINT fk_ExamPresentation_Student
    FOREIGN KEY (id_student)
    REFERENCES Student (id_student)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT fk_ExamPresentation_Exam
    FOREIGN KEY (id_exam)
    REFERENCES Exam (id_exam)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Answer (
  id_answer BIGINT NOT NULL AUTO_INCREMENT,
  open_answer VARCHAR(1000),
  assessment DOUBLE NOT NULL,
  id_question BIGINT,
  id_exam_presentation BIGINT NOT NULL,
  PRIMARY KEY (id_answer),
  CONSTRAINT fk_Answer_Question
    FOREIGN KEY (id_question)
    REFERENCES Question (id_question)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Answer_Presentation
    FOREIGN KEY (id_exam_presentation)
    REFERENCES ExamPresentation (id_exam_presentation)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE AnswerOption (
  id_answer BIGINT NOT NULL,
  id_option BIGINT NOT NULL,
  PRIMARY KEY (id_answer, id_option),
  CONSTRAINT fk_AnswerOption_Answer
    FOREIGN KEY (id_answer)
    REFERENCES Answer (id_answer)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_AnswerOption_Option
    FOREIGN KEY (id_option)
    REFERENCES ExamOption (id_option)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);