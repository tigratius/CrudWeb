# CrudWeb
Необходимо реализовать веб приложение, которое взаимодействует с БД.

Сущности:   
Skill (id, name)    
User(id, firstName, lastName, specialty, Set\<Skill> skills)  
Team(id, name, Set\<User> users)  
Project(id, name, budget, Set\<Team> teams)    
Customer(id, name, Set\<Project> projects)   

Требования:
 
1. Все CRUD операции для каждой из сущностей    
2. Придерживаться подхода MVC   
3. Для сборки проекта использовать Maven    
4. Для взаимодействия с БД - Hibernate  
5. Для конфигурирования Hibernate - аннотации   
6. Инициализация БД должна быть реализована с помощью liquibase
7. Взаимодействие с пользователем необходимо реализовать с помощью Servlets + Thymeleaf 
8. Приложение должно быть развёрнуто на https://www.heroku.com/