INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_USER');

INSERT INTO tb_user (name, email, password) VALUES ('Alex', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Fernanda', 'fernanda@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

insert into tb_babysitter (cpf, full_name, date_of_birth, address, phone_number, weekly_hours, monthly_salary, bonus, years_of_experience, max_travel_distance, user_id) values ('5015296394', 'Fernanda Monte dos Santos', '1993-04-08', '38 Golden Leaf Hill', '(782) 6797088', 19, 1884.23, 149.73, 4, 17, 2);