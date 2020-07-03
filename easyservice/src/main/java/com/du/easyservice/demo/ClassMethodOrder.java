package com.du.easyservice.demo;

import org.springframework.boot.autoconfigure.web.ResourceProperties;

public class ClassMethodOrder  {


    static int SM=0;
    static{
        System.out.println("++++++++++++父类静态代码块+++++++++++++++");
    };

    {
        System.out.println("++++++++++父类普通代码块++++++++++");
    };

     ClassMethodOrder() {
        System.out.println("++++++父类构造方法+++++");
    }
    public  void getName(String name) {
        System.out.println("+++父类普通方法+++");

    }


    public static void main(String[] args) {




    }


}

