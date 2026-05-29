package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.IEntityProcessingService;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.enemy.EnemySPI;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ServiceLoader;

public class EnemySystem implements IEntityProcessingService {
    private static final float SPEED = 220f;
    Random random = new Random();
    private static final float enemySpawnTime = 10f;
    private float enemyTimer = 0f;
    private final EnemySPI enemySPI = ServiceLoader.load(EnemySystem.class.getModule().getLayer(), EnemySPI.class).findFirst().orElseThrow();

    public void process(GameData gameData, World world) {
        List<Entity> bullets = new ArrayList<>();
        boolean enemyExists = false;
        enemyTimer += gameData.getDeltaTime();

        for (Entity entity : world.getEntities()) {
            if (entity instanceof Enemy){
                enemyExists = true;

                moveEnemyRandom(gameData, entity);
                Entity bullet = shoot(gameData, entity);

                if (bullet != null) {
                    bullets.add(bullet);
                }
            }
        }
        for (Entity bullet : bullets) {
            world.addEntity(bullet);
        }

        // If enemy does not exist AND there has passed 10 seconds, spawn a new enemy at the boarder
        if (!enemyExists){
            if (enemyTimer >= enemySpawnTime){
                boolean spawnFromLeft = random.nextBoolean();

                float x;
                float y = random.nextFloat() * gameData.getDisplayHeight();
                if (spawnFromLeft) {
                    x = 0;
                }
                else  {
                    x = gameData.getDisplayWidth();
                }

                Enemy enemy = enemySPI.createEnemy(x, y);

                if (spawnFromLeft) {
                    enemy.setRotation(0);
                }
                else{
                    enemy.setRotation(180);
                }
                world.addEntity(enemy);
            }
        }
    }

    private void moveEnemyRandom(GameData gameData, Entity enemy) {
        // Gives a number between -5 and 5
        float randomTurn = random.nextFloat() * 10 - 5 ;

        // Gets current rotation, and adds randomTurn
        // Results in enemy turning either left or right
        enemy.setRotation(enemy.getRotation() + randomTurn);


        // Converts current rotation into radians
        // because .sin and .cos uses radians
        double radians = Math.toRadians(enemy.getRotation());


        float dx = (float) Math.cos(radians);
        float dy = (float) Math.sin(radians);

        enemy.setX(enemy.getX() + dx * SPEED * gameData.getDeltaTime());
        enemy.setY(enemy.getY() + dy * SPEED * gameData.getDeltaTime());

        // If the enemy walks out of screen
        // return it to the opposite site
        if (enemy.getX() > gameData.getDisplayWidth()) {
            enemy.setX(0);
        }

        if (enemy.getX() < 0) {
            enemy.setX(gameData.getDisplayWidth());
        }

        if (enemy.getY() > gameData.getDisplayHeight()) {
            enemy.setY(0);
        }

        if (enemy.getY() < 0) {
            enemy.setY(gameData.getDisplayHeight());
        }
    }

    private Entity shoot(GameData gameData, Entity enemy) {
        if (random.nextFloat() > 0.05f) {
            return null;
        }

        // BulletSPI is provided by BulletPlugin. BulletPlugin is in the plugin layer
        // so we need to explicitly say where to look for it with "EnemySystem.class.getModule().getLayer()"
        for (BulletSPI bulletSPI : ServiceLoader.load(EnemySystem.class.getModule().getLayer(),BulletSPI.class)) {
            return bulletSPI.createBullet(enemy, gameData);
        }

        return null;
    }
}
