package dk.sdu.cbse.core;

import dk.sdu.cbse.common.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class Main extends Application {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final List<IGamePluginService> plugins = new ArrayList<>();
    private final List<IEntityProcessingService> systems = new ArrayList<>();
    private final List<IPostProcessingService>  postProcessingServices = new ArrayList<>();

    private Canvas canvas;
    private long lastFrameTime;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        loadPlugins();
        loadSystems();
        loadPostSystems();

        canvas = new Canvas(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        registerInput(scene);

        stage.setTitle("AsteroidsFX");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> stopPlugins());
        stage.show();

        startGameLoop();
    }

    private void loadPlugins() {
        for (IGamePluginService plugin : ServiceLoader.load(IGamePluginService.class)) {
            plugins.add(plugin);
            plugin.start(gameData, world);
        }
    }

    private void loadSystems() {
        for (IEntityProcessingService system : ServiceLoader.load(IEntityProcessingService.class)) {
            systems.add(system);
        }
    }

    private void loadPostSystems(){
        for (IPostProcessingService system : ServiceLoader.load(IPostProcessingService.class)) {
            postProcessingServices.add(system);
        }
    }

    private void registerInput(Scene scene) {
        scene.setOnKeyPressed(event -> setKey(event.getCode(), true));
        scene.setOnKeyReleased(event -> setKey(event.getCode(), false));
    }

    private void setKey(KeyCode keyCode, boolean pressed) {
        if (keyCode == KeyCode.UP) {
            gameData.getKeys().setKey(GameKeys.UP, pressed);
        } else if (keyCode == KeyCode.DOWN) {
            gameData.getKeys().setKey(GameKeys.DOWN, pressed);
        } else if (keyCode == KeyCode.LEFT) {
            gameData.getKeys().setKey(GameKeys.LEFT, pressed);
        } else if (keyCode == KeyCode.RIGHT) {
            gameData.getKeys().setKey(GameKeys.RIGHT, pressed);
        } else if (keyCode == KeyCode.SPACE) {
            gameData.getKeys().setKey(GameKeys.SPACE, pressed);
        }
    }

    private void startGameLoop() {
        lastFrameTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameData.setDeltaTime((now - lastFrameTime) / 1_000_000_000f);
                lastFrameTime = now;

                for (IEntityProcessingService system : systems) {
                    system.process(gameData, world);
                }

                for (IPostProcessingService postProcessing : postProcessingServices) {
                    postProcessing.process(gameData, world);
                }

                render();
                gameData.getKeys().update();
            }
        }.start();
    }

    private void render() {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(Color.BLACK);

        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Entity entity : world.getEntities()) {
            graphics.setFill(entity.getColor());

            graphics.fillOval(entity.getX() - entity.getRadius(), entity.getY() - entity.getRadius(), entity.getRadius()*2, entity.getRadius()*2);
        }
    }

    private void stopPlugins() {
        for (IGamePluginService plugin : plugins) {
            plugin.stop(gameData, world);
        }
    }
}
