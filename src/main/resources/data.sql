INSERT INTO `user` (email, name, password) VALUES ('student@alura.com.br', 'Student', '1234');
INSERT INTO `user` (email, name, password) VALUES ('gabriel@alura.com.br', 'Gabriel', '5678');
INSERT INTO `user` (email, name, password) VALUES ('guilherme@gmail.com', 'Guilherme Ramos', 'gm2930');

INSERT INTO course (category, name) VALUES('Spring Boot', 'Development');
INSERT INTO course (category, name) VALUES('HTML 5', 'Frontend');

INSERT INTO topic (created_at, message, status, title, author_id, course_id) VALUES(NOW(), 'Project does not compile', 'UNANSWERED', 'Question 1', 1, 1);
INSERT INTO topic (created_at, message, status, title, author_id, course_id) VALUES(NOW(), 'My element is invisible', 'UNANSWERED', 'Component is gone', 3, 2);
INSERT INTO topic (created_at, message, status, title, author_id, course_id) VALUES(NOW(), 'Error while parsing JSON', 'UNANSWERED', 'Exception is thrown', 2, 1);
