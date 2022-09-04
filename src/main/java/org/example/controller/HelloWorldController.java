package org.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorldController {

    // case "/hello":
    public void hello(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI().substring(req.getContextPath().length());

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Hello</title>");
        writer.println("</head>");
        writer.println("<body style=\"text-align:center;\">");
        writer.println("<h1>Hello World.</h1>");
        writer.println("<div>&nbsp;&nbsp;--&nbsp;Output by doGet(" + uri + ").</div>");
        writer.println("<div><a href=\"" + req.getContextPath() + "/hello/login\">Goto login~~~</a>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
