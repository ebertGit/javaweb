package org.example.servlet.method;

import java.lang.reflect.Method;

public class HandlerMethod {

    private final String beanName;
    private final Class<?> beanType;
    private final Method method;

    public HandlerMethod(String beanName, Class<?> beanType, Method method) {
        this.beanName = beanName;
        this.beanType = beanType;
        this.method = method;
    }

    public String getBeanName() {
        return beanName;
    }

    public Class<?> getBeanType() {
        return beanType;
    }

    public Method getMethod() {
        return method;
    }
}
