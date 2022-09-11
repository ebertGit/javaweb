package org.example.servlet.context;

import org.example.controller.HelloWorldController;
import org.example.controller.LoginController;
import org.example.db.DbConnectionManager;
import org.example.service.UserService;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyWebApplicationContext {

    private Map<String, Object> beans = new ConcurrentHashMap<>();

    public void createBeanMap() {
        // TODO auto scan and auto create.
        DbConnectionManager dbConnectionManager = new DbConnectionManager();
        UserService userService = new UserService(dbConnectionManager);
        beans.put("org.example.service.UserService", userService);

        HelloWorldController helloWorldController = new HelloWorldController();
        beans.put("org.example.controller.HelloWorldController", helloWorldController);

        LoginController loginController = new LoginController();
        try {
            Field field = LoginController.class.getDeclaredField("userService");
            field.setAccessible(true);
            field.set(loginController, userService);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        beans.put("org.example.controller.LoginController", loginController);

    }

    public Object getBeanByName(String name) {
        return beans.get(name);
    }
}
