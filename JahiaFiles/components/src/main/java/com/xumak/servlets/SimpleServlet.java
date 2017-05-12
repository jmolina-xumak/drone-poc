package com.xumak.servlets;

import org.osgi.service.http.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * DESCRIPTION
 * This a test servlet
 * For real uses - make sure to read corresponding documentation about it
 * -----------------------------------------------------------------------------
 * CHANGE	HISTORY
 * -----------------------------------------------------------------------------
 * Version	|	Date		|	Developer				|	Changes
 * 1.0		|	4/27/17 	|	isosa 				    |	Initial	Creation
 * -----------------------------------------------------------------------------
 */

public class SimpleServlet extends HttpServlet {

    public static final Logger logger = LoggerFactory.getLogger(SimpleServlet.class);

    public HttpService httpService;

    public SimpleServlet() {
    }

    public void postConstruct() {
    }

    public void preDestroy() {
    }

    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter writer = resp.getWriter();
        writer.println("<html><body><pre>");
        writer.println("Context path=" + req.getContextPath());
        writer.println("Servlet path=" + req.getServletPath());
        writer.println("Path info=" + req.getPathInfo());
        writer.println("Query string=" + req.getQueryString());
        writer.println("</pre></body></html>");
    }
}
