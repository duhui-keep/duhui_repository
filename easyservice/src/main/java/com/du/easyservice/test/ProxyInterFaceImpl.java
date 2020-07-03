package com.du.easyservice.test;

import java.lang.reflect.Method;

public class ProxyInterFaceImpl implements ProxyInterFace {

    @Override
    public void say() {
        System.out.println("假按揭啊");
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }
}
