package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.IGamePluginService;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.commonAsteroid.Asteroid;

import dk.sdu.cbse.commonAsteroid.AsteroidSPI;
import javafx.scene.paint.Color;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService, AsteroidSPI {

    Random random = new Random();
    @Override
    public void start(GameData gameData, World world) {
        world.addEntity(createAsteroid(0, gameData.getDisplayHeight() / 2, 3));
    }

    @Override
    public Asteroid createAsteroid(float x, float y, int size) {
        Asteroid asteroid = new Asteroid();
        asteroid.setX(x);
        asteroid.setY(y);
        asteroid.setSize(size);

        asteroid.setVelocityX(random.nextFloat() * 200 - 100);
        asteroid.setVelocityY(random.nextFloat() * 200 - 100);

        asteroid.setRadius(20*size);

        asteroid.setColor(Color.GRAY);
        asteroid.setType("ASTEROID");

        return asteroid;
    }
    @Override
    public void stop(GameData gameData, World world) {

    }


}
