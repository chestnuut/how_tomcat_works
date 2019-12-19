package me.chestnuut.how_tomcat_works.ch02.servlet;

import me.chestnuut.how_tomcat_works.ch02.Processor;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Initializing Servlet...");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        System.out.println("From Service...");
        PrintWriter out = res.getWriter();
        out.println(Processor.SUCCESS);
        out.println("Hello, Roses are red");
        out.println("Violets are blue");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
