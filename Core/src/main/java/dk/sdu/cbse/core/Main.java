package dk.sdu.cbse.core;

import dk.sdu.cbse.common.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main extends Application {

    private Game game;
    private AnnotationConfigApplicationContext context;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

       context = new AnnotationConfigApplicationContext();
       context.register(SpringConfig.class);
       context.refresh();

       game = context.getBean(Game.class);

        Scene scene = game.createScene();



        stage.setTitle("AsteroidsFX");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {game.stop(); context.close();});
        stage.show();
        game.start();
    }

}
