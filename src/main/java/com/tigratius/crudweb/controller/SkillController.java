package com.tigratius.crudweb.controller;

import com.tigratius.crudweb.config.ThymeleafConfig;
import com.tigratius.crudweb.entity.Skill;
import com.tigratius.crudweb.repository.impl.SkillRepositoryImpl;
import com.tigratius.crudweb.service.SkillService;
import com.tigratius.crudweb.util.HibernateUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/skill", "/skill/new", "/skill/insert", "/skill/edit", "/skill/delete", "/skill/update"})
public class SkillController extends HttpServlet {
    private ThymeleafConfig thymeleafConfig;
    private ServletContext servletContext;
    private SkillService skillService;

    @Override
    public void init(ServletConfig config) {
        servletContext = config.getServletContext();
        thymeleafConfig = new ThymeleafConfig(servletContext);
        skillService = new SkillService(new SkillRepositoryImpl(HibernateUtil.getSessionFactory()));
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
            case "/skill/new":
                showNewForm(request, response);
                break;
            case "/skill/insert":
                insert(request, response);
                break;
            case "/skill/delete":
                delete(request, response);
                break;
            case "/skill/edit":
                showEditForm(request, response);
                break;
            case "/skill/update":
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
        skillService.update(id, name);
        response.sendRedirect("/skill");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        Skill skill = skillService.getById(id);
        ctx.setVariable("skill", skill);

        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("skill/edit", ctx, response.getWriter());
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        skillService.delete(id);
        response.sendRedirect("/skill");
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        skillService.create(name);
        response.sendRedirect("/skill");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("skill/new", ctx, response.getWriter());
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        List<Skill> skills = skillService.getAll();
        ctx.setVariable("skills", skills);

        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("skill/list", ctx, response.getWriter());
    }
}
