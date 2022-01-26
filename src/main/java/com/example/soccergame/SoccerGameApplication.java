package com.example.soccergame;

import com.example.soccergame.generator.PlayerNameGenerator;
import com.example.soccergame.request.UserInfoRequest;
import com.example.soccergame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoccerGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoccerGameApplication.class, args);
	}

	@Bean
	CommandLineRunner initialize(@Autowired UserService userService) {
		return args -> {
			UserInfoRequest firstUser = new UserInfoRequest();
			firstUser.setEmailAddress("user1@soccer-game.com");
			firstUser.setPassword("qwert1234*");
			userService.create(firstUser);

			UserInfoRequest secondUser = new UserInfoRequest();
			secondUser.setEmailAddress("user2@soccer-game.com");
			secondUser.setPassword("asdasd1234!");
			userService.create(secondUser);
		};
	}
}
