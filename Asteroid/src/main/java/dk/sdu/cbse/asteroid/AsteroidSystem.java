package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.commonAsteroid.*;
import java.util.ServiceLoader;


public class AsteroidSystem implements IEntityProcessingService {


    private static final float asteroidSpawnTime = 5f;
    private float asteroidTimer = 0f;
    // Gets asteroid implementation through ServiceLoader, instead of dependency on AsteroidPlugin
    private final AsteroidSPI asteroidSPI = ServiceLoader.load(AsteroidSystem.class.getModule().getLayer(), AsteroidSPI.class).findFirst().orElseThrow();
    public void process(GameData gameData, World world) {

        // Spawns an asteroid if there has passed 5 seconds, and spawns the asteroid at either the left or right side of the screen
        asteroidTimer += gameData.getDeltaTime();

        if (asteroidTimer >= asteroidSpawnTime) {
            boolean spawnFromLeft = Math.random() < 0.5;

            float x;
            float y = (float) (Math.random() * gameData.getDisplayHeight());

            if (spawnFromLeft) {
                x = 0;
            } else {
                x = gameData.getDisplayWidth();
            }

            Asteroid asteroid = asteroidSPI.createAsteroid(x, y, 3);

            if (spawnFromLeft) {
                asteroid.setVelocityX(Math.abs(asteroid.getVelocityX()));
            } else {
                asteroid.setVelocityX(-Math.abs(asteroid.getVelocityX()));
            }

            world.addEntity(asteroid);
            asteroidTimer = 0f;
        }


        for (Entity entity : world.getEntities()) {
            if (entity instanceof Asteroid asteroid) {
                entity.setX(entity.getX() + entity.getVelocityX() * gameData.getDeltaTime());
                entity.setY(entity.getY() + entity.getVelocityY() *  gameData.getDeltaTime());

                if (entity.getX() > gameData.getDisplayWidth()){
                    entity.setX(0);
                }

                if (entity.getX() < 0){
                    entity.setX(gameData.getDisplayWidth());
                }

                if (entity.getY() > gameData.getDisplayHeight()){
                    entity.setY(0);
                }
                if (entity.getY() < 0){
                    entity.setY(gameData.getDisplayHeight());
                }

                if (asteroid.isShouldSplit()) {

                    // First calls splitAsteroid, which creates two small asteroids
                    splitAsteroid(world, asteroid);

                    // Then removes the original asteroid
                    world.removeEntity(entity);
                }
            }
        }
    }


    private void  splitAsteroid(World world, Asteroid asteroid) {
        if (asteroid.getSize() <= 1) {
            return;
        }

        // Creates two smaller asteroids
        for (int i = 0; i < 2; i++) {
            // Slightly changes the asteroid x and y, so they dont spawn directly onto each other
            world.addEntity(asteroidSPI.createAsteroid(asteroid.getX() + (i * 20-10), asteroid.getY() + (i * 20-10), asteroid.getSize() -1));
        }
    }


}
