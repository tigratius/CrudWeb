package com.tigratius.crudweb.controller;

import com.tigratius.crudweb.config.ThymeleafConfig;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/")
public class HomeController extends HttpServlet {

    private ThymeleafConfig thymeleafConfig;
    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) {
        servletContext = config.getServletContext();
        thymeleafConfig = new ThymeleafConfig(servletContext);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        TemplateEngine engine = thymeleafConfig.getTemplateEngine();
        engine.process("home", ctx, response.getWriter());
    }
}
