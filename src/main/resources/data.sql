INSERT INTO STUDENT(name, email, password) VALUES('Aluno', 'aluno@email.com', '$2a$10$s5HzGU8Cvovb.EkwHi8xW.JWPRta2UxRKJCxaXQHOxsHIiICaZoQ2');

INSERT INTO COURSE(name, category) VALUES('Spring Boot', 'Programação');
INSERT INTO COURSE(name, category) VALUES('HTML 5', 'Front-end');


INSERT INTO TOPIC(title, message, creation_date, status, student_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'UNRESOLVED', 1, 1);
INSERT INTO TOPIC(title, message, creation_date, status, student_id, course_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'UNRESOLVED', 1, 1);
INSERT INTO TOPIC(title, message, creation_date, status, student_id, course_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'UNANSWERED', 1, 2);