package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.IGamePluginService;
import dk.sdu.cbse.common.World;

import javafx.scene.paint.Color;

public class EnemyPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        Enemy enemy = new Enemy();
        enemy.setX(700);
        enemy.setY(200);
        enemy.setRadius(10);
        enemy.setColor(Color.RED);
        enemy.setType("ENEMY");
        world.addEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
