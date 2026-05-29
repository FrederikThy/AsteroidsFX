package dk.sdu.cbse.player;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.GameKeys;
import dk.sdu.cbse.common.IEntityProcessingService;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.bullet.BulletSPI;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PlayerSystem implements IEntityProcessingService {
    private static final float SPEED = 220f;


    @Override
    public void process(GameData gameData, World world) {
        List<Entity> bullets = new ArrayList<>();

        for (Entity entity : world.getEntities()) {
            if (entity instanceof Player) {
                movePlayer(gameData, entity);
                Entity bullet = shoot(gameData, entity);
                if (bullet != null) {
                    bullets.add(bullet);
                }
            }
        }

        for (Entity bullet : bullets) {
            world.addEntity(bullet);
        }
    }

    private void movePlayer(GameData gameData, Entity player) {
        float directionX = 0;
        float directionY = 0;

        if (gameData.getKeys().isDown(GameKeys.LEFT)) {
            directionX--;
        }
        if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
            directionX++;
        }
        if (gameData.getKeys().isDown(GameKeys.UP)) {
            directionY--;
        }
        if (gameData.getKeys().isDown(GameKeys.DOWN)) {
            directionY++;
        }

        player.setX(player.getX() + directionX * SPEED * gameData.getDeltaTime());
        player.setY(player.getY() + directionY * SPEED * gameData.getDeltaTime());

        if (player.getX() > gameData.getDisplayWidth()) {
            player.setX(0);
        }

        if (player.getX() < 0) {
            player.setX(gameData.getDisplayWidth());
        }

        if (player.getY() > gameData.getDisplayHeight()) {
            player.setY(0);
        }

        if (player.getY() < 0) {
            player.setY(gameData.getDisplayHeight());
        }

        if (directionX != 0 || directionY != 0) {
            float rotation = (float)  Math.toDegrees(Math.atan2(directionY, directionX));
            player.setRotation(rotation);
        }
    }

    private Entity shoot(GameData gameData, Entity player) {
        if (!gameData.getKeys().isPressed(GameKeys.SPACE)) {
            return null;
        }

        for (BulletSPI bulletSPI : ServiceLoader.load(PlayerSystem.class.getModule().getLayer(),BulletSPI.class)) {
            return bulletSPI.createBullet(player, gameData);
        }

        return null;
    }
}
