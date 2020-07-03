package com.du.easyservice.test;


import org.springframework.cglib.proxy.InvocationHandler;

public interface ProxyInterFace extends InvocationHandler {

    public void say();

}
