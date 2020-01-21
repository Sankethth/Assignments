package com.example.Cricket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CricketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricketApplication.class, args);
	}

	@RequestMapping(value = "/startMatch")
	public String startMatch() {
		OdiMatch odiMatch = new OdiMatch();
		return odiMatch.startMatch();
		//return "hello";
	}
}
