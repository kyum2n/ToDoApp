package com.example.webapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.example.webapp.entity.User;
// import com.example.webapp.repository.UserMapper;

@SpringBootApplication
@MapperScan("com.example.webapp.repository")
public class WebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

}
