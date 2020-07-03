package com.easy.ui.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class loginController {

    @RequestMapping("/")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wwewew00000", "weree");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println(e
            );
        }
        return "index";
    }

    @Scheduled(cron = "* * * * * ? ")
    public void corn(){
        System.out.println(new Date());
    }

    @RequestMapping("index")
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/logout")
    public void logout() {
        System.out.println("logout");
    }
}
