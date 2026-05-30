package dk.sdu.cbse.common.data;

public class GameData {
    private int displayHeight = 600;
    private int displayWidth = 800;

    private int score = 0;

    private boolean gameOver = false;
    private final GameKeys keys = new GameKeys();

    private float deltaTime;

    public int getDisplayWidth() {
        return displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public GameKeys getKeys() {
        return keys;
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }
    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }
    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }

    public int  getScore() {
        return score;
    }
    public void addScore(int score) {
        this.score += score;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
