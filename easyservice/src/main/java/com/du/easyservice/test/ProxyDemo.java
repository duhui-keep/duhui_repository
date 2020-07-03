package com.du.easyservice.test;

import java.lang.reflect.Method;

public class ProxyDemo implements ProxyInterFace {


    private  ProxyInterFace proxyInterFace = new ProxyInterFaceImpl();

    @Override
    public void say() {
        System.out.println("kaishi");
        proxyInterFace.say();
        System.out.println("jieshu");
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }
}
