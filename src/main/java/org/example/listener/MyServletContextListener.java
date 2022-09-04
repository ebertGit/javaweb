package org.example.listener;

import org.example.db.DbConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    private final DbConnectionManager dbConnectionManager = new DbConnectionManager();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized()");
        DbConnectionManager.main(null);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed()");
        dbConnectionManager.releaseConnection();
    }
}
