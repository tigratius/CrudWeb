--liquibase formatted sql
--changeset tigratius:2

/*Populate skills*/
INSERT INTO skills (name) values ('Java');
INSERT INTO skills (name) values ('C#');
INSERT INTO skills (name) values ('Management');
INSERT INTO skills (name) values ('Design');

/*Populate teams*/
INSERT INTO teams (name) values ('JavaTeam');
INSERT INTO teams (name) values ('C#Team');
INSERT INTO teams (name) values ('ManagerTeam');
INSERT INTO teams (name) values ('DesignerTeam');

/*Populate customers*/
INSERT INTO customers (name) values ('JavaProjectCustomer');
INSERT INTO customers (name) values ('C#ProjectCustomer');

/*Populate projects*/
INSERT INTO projects (name, budget, customer_id) values ('JavaProject', 2000000, 1);
INSERT INTO projects (name, budget, customer_id) values ('C#Project', 1000000, 11);

/*Populate users*/
INSERT INTO users (first_name, last_name, specialty, team_id) values ('Alex', 'Andreev', 'Java programmer', 1);
INSERT INTO users (first_name, last_name, specialty, team_id) values ('Feodor', 'Sumkin', 'Designer', 4);
INSERT INTO users (first_name, last_name, specialty, team_id) values ('Vlad', 'Andreev', 'Manager', 3);
INSERT INTO users (first_name, last_name, specialty, team_id) values ('Ivan', 'Ivanov', 'C# programmer', 2);
INSERT INTO users (first_name, last_name, specialty, team_id) values ('Petr', 'Pechkin', 'Manager', 3);

/*Populate usersskills*/
INSERT INTO usersskills (user_id, skill_id) values (1, 1);
INSERT INTO usersskills (user_id, skill_id) values (1, 2);
INSERT INTO usersskills (user_id, skill_id) values (2, 4);
INSERT INTO usersskills (user_id, skill_id) values (3, 3);
INSERT INTO usersskills (user_id, skill_id) values (4, 2);
INSERT INTO usersskills (user_id, skill_id) values (5, 3);

/*Populate teamsprojects*/
INSERT INTO teamsprojects (team_id, project_id) values (1, 1);
INSERT INTO teamsprojects (team_id, project_id) values (3, 1);
INSERT INTO teamsprojects (team_id, project_id) values (4, 1);
INSERT INTO teamsprojects (team_id, project_id) values (2, 2);
INSERT INTO teamsprojects (team_id, project_id) values (3, 2);








