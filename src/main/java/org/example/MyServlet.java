package org.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MyServlet extends HttpServlet {

    private final Map<String, String> userAuthMap = new ConcurrentHashMap<String, String>(){
        {
            // userName -> password.
            put("admin", "admin123");
        }
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().substring(req.getContextPath().length());
        switch (uri){
            case "/hello/login":
                // resp.sendRedirect("/views/login.jsp");
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
                break;
            case "/hello":
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
                break;
            default:
                // If was unknown url, forward to /hello.
                req.getRequestDispatcher("/hello").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        String uri = req.getRequestURI().substring(req.getContextPath().length());
        switch (uri){
            case "/hello/doLogin":
                if (userAuthMap.get(userName) != null && Objects.equals(userAuthMap.get(userName), password)){
                    req.setAttribute("userName", userName);
                    req.setAttribute("msg", "Welcome back~");
                    req.getRequestDispatcher("/views/success.jsp").forward(req, resp);
                } else {
                    req.setAttribute("msg", "user name or password is incorrect.");
                    req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                }
                break;
            case "/hello/doRegistration":
                if (userName != null && password != null){
                    if (userAuthMap.get(userName) == null){
                        userAuthMap.put(userName, password);
                        req.setAttribute("userName", userName);
                        req.setAttribute("msg", "Registration was successful!");
                        req.getRequestDispatcher("/views/success.jsp").forward(req, resp);
                        break;
                    } else {
                        req.setAttribute("msg", "The user name already exist.");
                    }
                } else {
                    req.setAttribute("msg", "user name or password can not be empty.");
                }
                req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    @SuppressWarnings("all")
    public void init() throws ServletException {
        System.out.println("MyServlet init() called.");
    }

    @Override
    public void destroy() {
        System.out.println("MyServlet destroy() called.");
    }
}
