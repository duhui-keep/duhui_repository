package com.du.easyservice.test;

public class StaticBean {

    String name;
    //    静态变量
    static int age;

    public StaticBean(String name) {
        this.name = name;
    }


    public StaticBean(int age) {
        this.age = age;
    }

    //    静态方法

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        StaticBean.age = age;
    }

    static void SayHello() {
        System.out.println("Hello i am java");
    }

    @Override
    public String toString() {
        return "StaticBean{" +
                "name='" + name + '\'' +
                '}';
    }
}