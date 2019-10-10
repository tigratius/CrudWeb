package com.tigratius.crudweb.controller;

import com.tigratius.crudweb.config.ThymeleafConfig;
import com.tigratius.crudweb.entity.Customer;
import com.tigratius.crudweb.entity.Project;
import com.tigratius.crudweb.repository.impl.CustomerRepositoryImpl;
import com.tigratius.crudweb.repository.impl.ProjectRepositoryImpl;
import com.tigratius.crudweb.service.CustomerService;
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

@WebServlet({"/customer", "/customer/new", "/customer/insert", "/customer/edit", "/customer/delete", "/customer/update"})
public class CustomerController extends HttpServlet {

    private ThymeleafConfig thymeleafConfig;
    private ServletContext servletContext;
    private CustomerService customerService;

    private final String redirectToCustomerList = "/customer";
    private final String stringId= "id";


    @Override
    public void init(ServletConfig config) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        servletContext = config.getServletContext();
        thymeleafConfig = new ThymeleafConfig(servletContext);
        customerService = new CustomerService(new CustomerRepositoryImpl(sessionFactory), new ProjectRepositoryImpl(sessionFactory));
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
            case "/customer/new":
                showNewForm(request, response);
                break;
            case "/customer/insert":
                insert(request, response);
                break;
            case "/customer/delete":
                delete(request, response);
                break;
            case "/customer/edit":
                showEditForm(request, response);
                break;
            case "/customer/update":
                 update(request, response);
                break;
            default:
                list(request, response);
                break;
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter(stringId));
        String name = request.getParameter("name");
        List<String> projectIds = Arrays.asList(request.getParameterValues("projectIds"));
        customerService.update(id, name, projectIds);
        response.sendRedirect(redirectToCustomerList);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter(stringId));
        List<Project> projects = customerService.getProjects();
        Customer customer = customerService.getById(id);
        List<Long> projectIds = customer.getProjects().stream().map(Project::getId).collect(Collectors.toList());

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("customer", customer);
        ctx.setVariable("projects", projects);
        ctx.setVariable("projectIds", projectIds);

        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("customer/edit", ctx, response.getWriter());
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter(stringId));
        customerService.delete(id);
        response.sendRedirect(redirectToCustomerList);
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        List<String> projectIds = Arrays.asList(request.getParameterValues("projectIds"));
        customerService.create(name, projectIds);
        response.sendRedirect(redirectToCustomerList);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Project> projects = customerService.getProjects();

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("projects", projects);

        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("customer/new", ctx, response.getWriter());
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        List<Customer> customers = customerService.getAll();
        ctx.setVariable("customers", customers);

        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("customer/list", ctx, response.getWriter());
    }
}
