package dk.sdu.cbse.player;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.data.World;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

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
        // Makes a list to not remove entities during world.getEntities
        List<Entity> player = new ArrayList<Entity>();

        for  (Entity entity : world.getEntities()) {
            if(entity instanceof Player) {
                player.add(entity);
            }
        }

        for (Entity entity : player){
            world.removeEntity(entity);
        }
    }
}
