package org.example.servlet;

import org.example.controller.HelloWorldController;
import org.example.controller.LoginController;
import org.example.service.UserService;
import org.example.servlet.context.MyWebApplicationContext;
import org.example.servlet.method.HandlerMethod;
import org.example.servlet.method.RequestMappingInfo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class MyServlet extends HttpServlet {

    private MyWebApplicationContext wac;

    // TODO add index for RequestMappingInfo: path -> RequestMappingInfo.
    private final Map<RequestMappingInfo, HandlerMethod> mappingLookup = new LinkedHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req,resp);
    }

    /**
     * Assign Controller according to the Request.
     * @param req request
     * @param resp response
     * @throws IOException
     * @throws ServletException
     */
    protected void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HandlerMethod hm = getHandlerMethod(req);

        if (hm != null) {
            String beanName = hm.getBeanName();
            Method method = hm.getMethod();
            Object[] paramObjs = new Object[method.getParameterCount()];
            String errorMsg = null;
            Exception exception = null;

            try {
                int i = 0;
                // set parameters.
                for (Parameter param : method.getParameters()) {
                    if (param.getType() == HttpServletRequest.class) {
                        paramObjs[i] = req;
                    } else if (param.getType() == HttpServletResponse.class) {
                        paramObjs[i] = resp;
                    } else {
                        // TODO add parameter binding.
                        paramObjs[i] = null;
                    }
                    i++;
                }
                // invoke method.
                Object instanceObj = wac.getBeanByName(beanName);
                String result = (String) method.invoke(instanceObj, paramObjs);

                if (result != null && result != "") {
                    if (result.startsWith("redirect:")) {
                        resp.sendRedirect(result);
                        return;
                    }
                    req.getRequestDispatcher(result).forward(req, resp);
                    return;
                }
            } catch (IllegalAccessException e) {
                errorMsg = e.toString();
                exception = e;
            } catch (InvocationTargetException e) {
                errorMsg = e.toString();
                exception = e;
            } catch (Exception e) {
                errorMsg = e.toString();
                exception = e;
            }

            // if error happened, show error page.
            if (errorMsg != null) {
                req.setAttribute("msg", errorMsg);
                req.setAttribute("exception", exception);
                req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
            }
        } else {
            // if no handler matched, show not found page.
            req.setAttribute("msg", req.getRequestURI().substring(req.getContextPath().length()));
            req.getRequestDispatcher("/views/notFound.jsp").forward(req, resp);
        }
    }

    private HandlerMethod getHandlerMethod(HttpServletRequest req) {
        String uri = req.getRequestURI().substring(req.getContextPath().length());
        String method = req.getMethod().toUpperCase();

        // TODO use index for RequestMappingInfo: path -> RequestMappingInfo.
        // TODO match slash end.
        RequestMappingInfo rmi = mappingLookup.keySet().stream().filter(e -> {
            if (e.getPath().equals(uri)) {
                if (e.getMethod().size() == 0 || e.getMethod().contains(method)) {
                    return true;
                }
            }
            return false;
        }).findFirst().orElse(null);

        if (rmi == null) {
            return null;
        }
        return mappingLookup.get(rmi);
    }

    @Override
    @SuppressWarnings("all")
    public void init() throws ServletException {
        System.out.println("MyServlet init() called.");
        createHandlerMapping();

        ServletContext servletContext = getServletContext();
        wac = (MyWebApplicationContext) servletContext.getAttribute("myWebApplicationContext");
    }

    /**
     * Create Mapping of request info to handler.
     * TODO create automatically.
     */
    private void createHandlerMapping() {
        /* Request mapping: /hello -> HelloWorldController#hello */
        try {
            Class<?> clazz = HelloWorldController.class;
            Method method = clazz.getMethod("hello", HttpServletRequest.class, HttpServletResponse.class);
            doCreateMapping(clazz, method, "/hello");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        /* Request mapping: /hello/login GET,POST -> LoginController#initLogin */
        try {
            Class<?> clazz = LoginController.class;
            Method method = clazz.getMethod("initLogin");
            doCreateMapping(clazz, method, "/hello/login", "GET", "POST");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        /* Request mapping: /hello/doLogin POST -> LoginController#doLogin */
        try {
            Class<?> clazz = LoginController.class;
            Method method = clazz.getMethod("doLogin", HttpServletRequest.class, HttpServletResponse.class);
            doCreateMapping(clazz, method, "/hello/doLogin", "POST");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        /* Request mapping: /hello/register GET,POST -> LoginController#initRegister */
        try {
            Class<?> clazz = LoginController.class;
            Method method = clazz.getMethod("initRegister");
            doCreateMapping(clazz, method, "/hello/register", "GET", "POST");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        /* Request mapping: /hello/doRegistration POST -> LoginController#doRegistration */
        try {
            Class<?> clazz = LoginController.class;
            Method method = clazz.getMethod("doRegistration", HttpServletRequest.class, HttpServletResponse.class);
            doCreateMapping(clazz, method, "/hello/doRegistration", "POST");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save Mapping into map {@code mappingLookup}.
     * @param clazz handler's class type
     * @param method handler's method
     * @param path request path
     * @param param the list of httpMethod
     */
    private void doCreateMapping(Class<?> clazz, Method method, String path, String...param) {
        // set bean name by class name and toggle first char to lower case.
        char[] nameChar = clazz.getName().toCharArray();
        // class name is contain package name, so do not toggle to lower case.
//        nameChar[0] = String.valueOf(nameChar[0]).toLowerCase().toCharArray()[0];
        String beanName = new String(nameChar);

        HandlerMethod hm = new HandlerMethod(beanName, clazz, method);
        RequestMappingInfo rmi = new RequestMappingInfo(path, param);
        mappingLookup.put(rmi, hm);
    }

    @Override
    public void destroy() {
        System.out.println("MyServlet destroy() called.");
    }
}
