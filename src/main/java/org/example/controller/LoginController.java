package org.example.controller;

import org.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController {

    private UserService userService;

    // case "/hello/login":
    public String initLogin(){
        return "/views/login.jsp";
    }

    // case: /hello/doLogin
    public String doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

            if (userService.userAuthentication(userName, password)){
                req.setAttribute("userName", userName);
                req.setAttribute("msg", "Welcome back~");
//                req.getRequestDispatcher("/views/success.jsp").forward(req, resp);
                return "/views/success.jsp";
            } else {
                req.setAttribute("msg", "user name or password is incorrect.");
//                req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                return "/views/error.jsp";
            }
    }

    // case: /hello/register
    public String initRegister() {
        return "/views/register.jsp";
    }

    // case: /hello/doRegistration
    public String doRegistration(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        if (userName != null && userName !="" && password != null && password != ""){
            int count = userService.userRegistration(userName, password);
            // TODO use exception to judgement.
            if (count > 0){
                req.setAttribute("userName", userName);
                req.setAttribute("msg", "Registration was successful!");
//                req.getRequestDispatcher("/views/success.jsp").forward(req, resp);
                return "/views/success.jsp";
            } else {
                req.setAttribute("msg", "The user name already exist.");
            }
        } else {
            req.setAttribute("msg", "user name or password can not be empty.");
        }
//        req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        return "/views/error.jsp";
    }
}
