package org.example.listener;

import org.example.db.DbConnectionManager;
import org.example.servlet.context.MyWebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    private final DbConnectionManager dbConnectionManager = new DbConnectionManager();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized()");
        DbConnectionManager.main(null);

        MyWebApplicationContext wac = new MyWebApplicationContext();
        wac.createBeanMap();
        sce.getServletContext().setAttribute("myWebApplicationContext", wac);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed()");
        dbConnectionManager.releaseConnection();
    }
}
