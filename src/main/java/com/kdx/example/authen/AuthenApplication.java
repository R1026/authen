package com.kdx.example.authen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class AuthenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenApplication.class, args);
	}

}
