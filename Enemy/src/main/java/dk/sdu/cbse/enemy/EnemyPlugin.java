package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.IGamePluginService;
import dk.sdu.cbse.common.World;

import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.enemy.EnemySPI;
import javafx.scene.paint.Color;

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

    }
}
