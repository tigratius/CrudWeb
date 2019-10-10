--liquibase formatted sql
--changeset tigratius:1

CREATE TABLE skills
(
    id   INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE teams
(
    id   INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE customers
(
    id   INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE users
(
    id   INTEGER AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    specialty  VARCHAR(100) NOT NULL,
    team_id INTEGER DEFAULT NULL,
    CONSTRAINT `FK_users_teams` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`) ON DELETE SET NULL
);

CREATE TABLE projects
(
    id   INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    budget DOUBLE NOT NULL,
    customer_id INTEGER DEFAULT NULL,
    CONSTRAINT `FK_projects_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE
);

CREATE TABLE usersskills
(
    id   INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id INTEGER DEFAULT NULL,
    skill_id INTEGER DEFAULT NULL,
    CONSTRAINT `FK_userskills_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
    CONSTRAINT `FK_userskills_skills` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`) ON DELETE CASCADE
);

CREATE TABLE teamsprojects
(
    id   INTEGER AUTO_INCREMENT PRIMARY KEY,
    team_id INTEGER DEFAULT NULL,
    project_id INTEGER DEFAULT NULL,
    CONSTRAINT `FK_teamsprojects_teams` FOREIGN KEY (`team_id`) REFERENCES `skills` (`id`) ON DELETE CASCADE,
    CONSTRAINT `FK_teamsprojects_projects` FOREIGN KEY (`project_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);


