package com.tigratius.crudweb.controller;

import com.tigratius.crudweb.config.ThymeleafConfig;
import com.tigratius.crudweb.entity.Project;
import com.tigratius.crudweb.entity.Team;
import com.tigratius.crudweb.repository.impl.ProjectRepositoryImpl;
import com.tigratius.crudweb.repository.impl.TeamRepositoryImpl;
import com.tigratius.crudweb.service.ProjectService;
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

@WebServlet({"/project", "/project/new", "/project/insert", "/project/edit", "/project/delete", "/project/update"})
public class ProjectController extends HttpServlet {
    private ThymeleafConfig thymeleafConfig;
    private ServletContext servletContext;
    private ProjectService projectService;

    @Override
    public void init(ServletConfig config) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        servletContext = config.getServletContext();
        thymeleafConfig = new ThymeleafConfig(servletContext);
        projectService = new ProjectService(new ProjectRepositoryImpl(sessionFactory), new TeamRepositoryImpl(sessionFactory));
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
            case "/project/new":
                showNewForm(request, response);
                break;
            case "/project/insert":
                insert(request, response);
                break;
            case "/project/delete":
                delete(request, response);
                break;
            case "/project/edit":
                showEditForm(request, response);
                break;
            case "/project/update":
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
        String budget = request.getParameter("budget");
        List<String> teamIds = Arrays.asList(request.getParameterValues("teamIds"));
        projectService.update(id, name, budget, teamIds);
        response.sendRedirect("/project");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        List<Team> teams = projectService.getTeams();
        Project project = projectService.getById(id);
        List<Long> teamIds = project.getTeams().stream().map(Team::getId).collect(Collectors.toList());

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("project", project);
        ctx.setVariable("teams", teams);
        ctx.setVariable("teamIds", teamIds);

        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("project/edit", ctx, response.getWriter());
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        projectService.delete(id);
        response.sendRedirect("/project");
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String budget = request.getParameter("budget");
        List<String> teamIds = Arrays.asList(request.getParameterValues("teamIds"));

        projectService.create(name, budget, teamIds);
        response.sendRedirect("/project");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Team> teams = projectService.getTeams();

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("teams", teams);

        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("project/new", ctx, response.getWriter());
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Project> projects = projectService.getAll();

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("projects", projects);

        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("project/list", ctx, response.getWriter());
    }
}
