package dk.sdu.cbse.player;

import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.IGamePluginService;
import dk.sdu.cbse.common.World;

import javafx.scene.paint.Color;

public class PlayerPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        Player player = new Player();
        player.setX(100);
        player.setY(100);
        player.setRadius(8);
        player.setColor(Color.GREEN);
        player.setType("PLAYER");

        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
