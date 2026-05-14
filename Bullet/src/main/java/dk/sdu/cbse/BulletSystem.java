package dk.sdu.cbse;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.IEntityProcessingService;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.bullet.Bullet;

import java.util.ArrayList;
import java.util.List;

public class BulletSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        List<Entity> bulletsToRemove = new ArrayList<>();

        for (Entity entity : world.getEntities()) {
            if (entity instanceof Bullet) {
                Bullet bullet = (Bullet) entity;
                moveBullet(bullet, gameData);
                if (isOutsideGameArea(bullet, gameData)) {
                    bulletsToRemove.add(bullet);
                }
            }
        }

        for (Entity bullet : bulletsToRemove) {
            world.removeEntity(bullet);
        }
    }

    private void moveBullet(Bullet bullet, GameData gameData) {
        bullet.setX(bullet.getX() + bullet.getDirectionX() * bullet.getSpeed() * gameData.getDeltaTime());
        bullet.setY(bullet.getY() + bullet.getDirectionY() * bullet.getSpeed() * gameData.getDeltaTime());
    }

    private boolean isOutsideGameArea(Bullet bullet, GameData gameData) {
        return bullet.getX() < 0
                || bullet.getX() > gameData.getDisplayWidth()
                || bullet.getY() < 0
                || bullet.getY() > gameData.getDisplayHeight();
    }
}
