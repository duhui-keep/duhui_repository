package com.du.easyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling //定时任务
@SpringBootApplication
public class EasyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyserviceApplication.class, args);
	}

}
