package dk.sdu.cbse;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.IGamePluginService;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.bullet.BulletSPI;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class BulletPlugin implements IGamePluginService, BulletSPI {
    private static final float BULLET_SPEED = 500f;

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Bullet bullet = new Bullet();
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        bullet.setDirectionX(0);
        bullet.setDirectionY(-1);
        bullet.setSpeed(BULLET_SPEED);
        bullet.setRadius(2);
        bullet.setColor(Color.WHITE);
        bullet.setType("BULLET");
        bullet.setOwner(shooter.getType());
        return bullet;
    }

    public void stop(GameData gameData, World world) {
        List<Entity> bullets = new ArrayList<>();

        for (Entity entity : world.getEntities()) {
            if(entity.getClass() == Bullet.class) {
                bullets.add(entity);
            }
        }

        for (Entity bullet : bullets) {
            world.removeEntity(bullet);
        }
    }
}
