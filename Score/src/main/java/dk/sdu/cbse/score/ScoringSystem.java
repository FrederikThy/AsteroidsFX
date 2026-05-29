package dk.sdu.cbse.score;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.IPostProcessingService;
import dk.sdu.cbse.common.World;
import org.springframework.web.client.RestTemplate;


public class ScoringSystem implements IPostProcessingService {
    // Used to send the final score to the microservice
    private final RestTemplate restTemplate = new RestTemplate();
    private boolean scoreSubmitted = false;

    @Override
    public void process(GameData gameData, World world) {
        // Submit the final score once when the game has ended.
        if (gameData.isGameOver() && !scoreSubmitted) {
            submitScore(gameData.getScore());
            scoreSubmitted = true;
        }
    }

    private void submitScore(int score) {
        try {
            // Send the score to the scoring microservice and read back the current high score.
            String highscore = restTemplate.postForObject("http://localhost:8080/score?score=" + score, null, String.class);

        } catch (Exception e){
            // Keep the game from crashing if the scoring service is not running.
            System.out.println("Could not connect to server. Score wasn't submitted." + e.getMessage());
        }
    }
}
