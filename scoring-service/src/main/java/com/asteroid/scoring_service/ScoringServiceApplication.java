package com.asteroid.scoring_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/score")
public class ScoringServiceApplication {

	private int highScore = 0;
	public static void main(String[] args) {
		SpringApplication.run(ScoringServiceApplication.class, args);
	}
	// Returns the current highscore
	@GetMapping("/highscore")
	public int getHighScore(){
		return highScore;
	}

	// If the new score is higher than the highscore, replace it.
	@PostMapping
	public int submitScore(@RequestParam int score){
		if (score > highScore){
			highScore = score;
		}
		return highScore;
	}


}
