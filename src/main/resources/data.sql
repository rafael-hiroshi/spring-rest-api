INSERT INTO `user` (id, email, name, password) VALUES (1, 'student@alura.com.br', 'Student', '$2a$10$6CBw6OYWBboGQRPN7Uzj/OTJFBtCa.nxGH8tHbG7BO8OjgZW.wWsq');
INSERT INTO `user` (id, email, name, password) VALUES (2, 'gabriel@alura.com.br', 'Gabriel', '$2a$10$kbo3jq3.gEBBwbJNK8Ijs.WBvHq13hU/O4A3yOuT3igQB2vWuw8gG');
INSERT INTO `user` (id, email, name, password) VALUES (3, 'guilherme@gmail.com', 'Guilherme Ramos', '$2a$10$U5W9B7lUO0YJP6wM/dMTgOowsvoAPnAG8VGvHMt14PwGij6LVxsm2');
INSERT INTO `user` (id, email, name, password) VALUES (4, 'admin@alura.com.br', 'Administrator', '$2a$10$6CBw6OYWBboGQRPN7Uzj/OTJFBtCa.nxGH8tHbG7BO8OjgZW.wWsq');

INSERT INTO profile (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_STUDENT');
INSERT INTO user_profiles (user_id, profiles_id) VALUES (1, 2), (2, 2), (3, 2), (4, 1);

INSERT INTO course (category, name) VALUES('Development', 'Spring Boot');
INSERT INTO course (category, name) VALUES('Frontend', 'HTML 5');

INSERT INTO topic (created_at, message, status, title, author_id, course_id) VALUES(NOW(), 'Project does not compile', 'UNANSWERED', 'Question 1', 1, 1);
INSERT INTO topic (created_at, message, status, title, author_id, course_id) VALUES(NOW(), 'My element is invisible', 'UNANSWERED', 'Component is gone', 3, 2);
INSERT INTO topic (created_at, message, status, title, author_id, course_id) VALUES(NOW(), 'Error while parsing JSON', 'UNANSWERED', 'Exception is thrown', 2, 1);
