package dk.sdu.cbse.core;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostProcessingService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;

public class Game {
    private final GameData gameData;
    private final World world;
    private final List<IGamePluginService> plugins;
    private final List<IEntityProcessingService>  entityProcessingServices;
    private final List<IPostProcessingService> postProcessingServices;
    private Canvas canvas;
    private long lastFrameTime;
    public Game(GameData gameData, World world,
                List<IGamePluginService> plugins,
                List<IEntityProcessingService> entityProcessingServices,
                List<IPostProcessingService> postProcessingServices) {
        this.gameData = gameData;
        this.world = world;
        this.plugins = plugins;
        this.entityProcessingServices = entityProcessingServices;
        this.postProcessingServices = postProcessingServices;
    }

    public void start() {
        for (IGamePluginService plugin : plugins) {
            plugin.start(gameData, world);
        }
        startGameLoop();
    }
    public void stop() {
        for (IGamePluginService plugin : plugins) {
            plugin.stop(gameData, world);
        }
    }

    public Scene createScene() {
        canvas = new Canvas(gameData.getDisplayWidth(), gameData.getDisplayHeight());

        Pane root = new Pane(canvas);

        Scene scene = new Scene(root);

        registerInput(scene);

        return scene;
    }
    public void update(float deltaTime) {
        gameData.setDeltaTime(deltaTime);
        for (IEntityProcessingService entityProcessing : entityProcessingServices) {
            entityProcessing.process(gameData, world);
        }
        for (IPostProcessingService postProcessing : postProcessingServices) {
            postProcessing.process(gameData, world);
        }
        gameData.getKeys().update();
    }



    public GameData getGameData() {
        return gameData;
    }

    public World getWorld() {
        return world;
    }

    private void registerInput(Scene scene) {
        scene.setOnKeyPressed(event -> setKey(event.getCode(), true));
        scene.setOnKeyReleased(event -> setKey(event.getCode(), false));
    }

    private void setKey(KeyCode keyCode, boolean pressed) {
        if (keyCode == KeyCode.UP) {
            getGameData().getKeys().setKey(GameKeys.UP, pressed);
        } else if (keyCode == KeyCode.DOWN) {
            getGameData().getKeys().setKey(GameKeys.DOWN, pressed);
        } else if (keyCode == KeyCode.LEFT) {
            getGameData().getKeys().setKey(GameKeys.LEFT, pressed);
        } else if (keyCode == KeyCode.RIGHT) {
            getGameData().getKeys().setKey(GameKeys.RIGHT, pressed);
        } else if (keyCode == KeyCode.SPACE) {
            getGameData().getKeys().setKey(GameKeys.SPACE, pressed);
        }
    }

    private void startGameLoop() {
        lastFrameTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                float deltaTime = (now - lastFrameTime) / 1_000_000_000f;
                lastFrameTime = now;

                update(deltaTime);

                render();
            }
        }.start();
    }
    public void render() {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(Color.BLACK);

        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        graphics.setFill(Color.WHITE);
        graphics.fillText("Score: " + getGameData().getScore(), 20, 30);
        for (Entity entity : getWorld().getEntities()) {
            if (entity.getType().equals("PLAYER")){
                drawPlayer(graphics, entity);
            }
            if (entity.getType().equals("BULLET")){
                drawBullet(graphics, entity);
            }
            if (entity.getType().equals("ASTEROID")){
                drawAsteroid(graphics, entity);
            }
            if (entity.getType().equals("ENEMY")){
                drawEnemy(graphics, entity);
            }
        }
    }

    // rendering methods for each entity. Is used in "render"
    private void drawPlayer(GraphicsContext graphics, Entity entity) {
        graphics.save();

        graphics.translate(entity.getX(), entity.getY());
        graphics.rotate(entity.getRotation());

        graphics.setFill(entity.getColor());
        double r = entity.getRadius();

        graphics.fillPolygon(new double[]{r, -r, -r}, new double[]{0, -r, r}, 3);

        graphics.restore();
    }

    private void drawEnemy(GraphicsContext graphics, Entity entity) {
        graphics.save();
        graphics.translate(entity.getX(), entity.getY());
        graphics.rotate(entity.getRotation());
        graphics.setFill(entity.getColor());
        double r = entity.getRadius();
        graphics.fillPolygon(new double[]{r, 0, -r, 0}, new double[]{0, r, 0, -r}, 4);

        graphics.restore();
    }

    private void drawAsteroid(GraphicsContext graphics, Entity entity) {
        graphics.setFill(entity.getColor());

        graphics.fillOval(entity.getX() - entity.getRadius(), entity.getY() - entity.getRadius(), entity.getRadius() * 2, entity.getRadius() * 2);
    }

    private void drawBullet(GraphicsContext graphics, Entity entity) {
        graphics.setFill(entity.getColor());

        graphics.fillOval(entity.getX() - entity.getRadius(),  entity.getY() - entity.getRadius(), entity.getRadius() * 2, entity.getRadius() * 2);
    }
}
