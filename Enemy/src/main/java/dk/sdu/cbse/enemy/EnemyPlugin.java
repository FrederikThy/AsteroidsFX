package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.data.World;

import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.enemy.EnemySPI;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class EnemyPlugin implements IGamePluginService, EnemySPI {
    @Override
    public void start(GameData gameData, World world) {

        world.addEntity(createEnemy(0, gameData.getDisplayHeight() / 2f));
    }

    @Override
    public Enemy createEnemy(float x, float y) {
        Enemy enemy = new Enemy();
        enemy.setX(x);
        enemy.setY(y);
        enemy.setRadius(10);
        enemy.setColor(Color.RED);
        enemy.setType("ENEMY");
        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Makes a list to not remove entities during world.getEntities
        List<Entity> enemy = new ArrayList<Entity>();

        for  (Entity entity : world.getEntities()) {
            if(entity instanceof Enemy) {
                enemy.add(entity);
            }
        }

        for (Entity entity : enemy){
            world.removeEntity(entity);
        }
    }
}
