package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.bullet.BulletSPI;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class BulletPlugin implements IGamePluginService, BulletSPI {
    private static final float BULLET_SPEED = 500f;

    @Override
    public void start(GameData gameData, World world) {
        // Unlike the other implementations of start(...), we dont need to add a bullet when the game starts.
        // That is why this method is empty, and the bullet spawning is handled by createBullet instead.
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Bullet bullet = new Bullet();
        // Uses the entities rotation to determine in which direction the bullet should shoot
        double radians = Math.toRadians(shooter.getRotation());
        float directionX = (float) Math.cos(radians);
        float directionY = (float) Math.sin(radians);
        bullet.setX(shooter.getX() + directionX * shooter.getRadius());
        bullet.setY(shooter.getY() + directionY * shooter.getRadius());
        bullet.setDirectionX(directionX);
        bullet.setDirectionY(directionY);

        bullet.setSpeed(BULLET_SPEED);
        bullet.setRadius(2);
        bullet.setColor(Color.WHITE);
        bullet.setType("BULLET");
        bullet.setOwner(shooter.getType());
        return bullet;
    }

    public void stop(GameData gameData, World world) {
        // Makes a list to not remove entities during world.getEntities
        List<Entity> bullet = new ArrayList<Entity>();

        for  (Entity entity : world.getEntities()) {
            if(entity instanceof Bullet) {
                bullet.add(entity);
            }
        }

        for (Entity entity : bullet){
            world.removeEntity(entity);
        }
    }
}
