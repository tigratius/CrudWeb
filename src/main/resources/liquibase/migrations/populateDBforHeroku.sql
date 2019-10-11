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
INSERT INTO projects (name, budget) values ('JavaProject', 2000000);
INSERT INTO projects (name, budget) values ('C#Project', 1000000);

/*Populate users*/
INSERT INTO users (first_name, last_name, specialty) values ('Alex', 'Andreev', 'Java programmer');
INSERT INTO users (first_name, last_name, specialty) values ('Ivan', 'Ivanov', 'C# programmer');
INSERT INTO users (first_name, last_name, specialty) values ('Vlad', 'Andreev', 'Manager');
INSERT INTO users (first_name, last_name, specialty) values ('Petr', 'Pechkin', 'Manager');
INSERT INTO users (first_name, last_name, specialty) values ('Feodor', 'Sumkin', 'Designer');
