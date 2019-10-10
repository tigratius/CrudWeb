package com.tigratius.crudweb.controller;

import com.tigratius.crudweb.config.ThymeleafConfig;
import com.tigratius.crudweb.entity.Team;
import com.tigratius.crudweb.entity.User;
import com.tigratius.crudweb.repository.impl.TeamRepositoryImpl;
import com.tigratius.crudweb.repository.impl.UserRepositoryImpl;
import com.tigratius.crudweb.service.TeamService;
import com.tigratius.crudweb.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet({"/team", "/team/new", "/team/insert", "/team/edit", "/team/delete", "/team/update"})
public class TeamController extends HttpServlet {
    private ThymeleafConfig thymeleafConfig;
    private ServletContext servletContext;
    private TeamService teamService;

    @Override
    public void init(ServletConfig config) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        servletContext = config.getServletContext();
        thymeleafConfig = new ThymeleafConfig(servletContext);
        teamService = new TeamService(new TeamRepositoryImpl(sessionFactory), new UserRepositoryImpl(sessionFactory));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/team/new":
                showNewForm(request, response);
                break;
            case "/team/insert":
                insert(request, response);
                break;
            case "/team/delete":
                delete(request, response);
                break;
            case "/team/edit":
                showEditForm(request, response);
                break;
            case "/team/update":
                update(request, response);
                break;
            default:
                list(request, response);
                break;
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        List<String> userIds = Arrays.asList(request.getParameterValues("userIds"));
        teamService.update(id, name, userIds);
        response.sendRedirect("/team");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        List<User> users = teamService.getUsers();
        Team team = teamService.getById(id);
        List<Long> userIds = team.getUsers().stream().map(User::getId).collect(Collectors.toList());

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("team", team);
        ctx.setVariable("users", users);
        ctx.setVariable("userIds", userIds);

        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("team/edit", ctx, response.getWriter());
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        teamService.delete(id);
        response.sendRedirect("/team");
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        List<String> userIds = Arrays.asList(request.getParameterValues("userIds"));
        teamService.create(name, userIds);
        response.sendRedirect("/team");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = teamService.getUsers();

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("users", users);

        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("team/new", ctx, response.getWriter());
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Team> teams = teamService.getAll();

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("teams", teams);

        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("team/list", ctx, response.getWriter());
    }
}
