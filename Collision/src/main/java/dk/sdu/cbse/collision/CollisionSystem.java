package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.services.IPostProcessingService;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.commonAsteroid.Asteroid;

import java.util.HashSet;
import java.util.Set;

public class CollisionSystem implements IPostProcessingService {



    public void process(GameData gameData, World world) {

        // To avoid bugs, we remove the entities after the loop
        Set<Entity> entitiesToRemove = new HashSet<>();

        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                // if the two entities are identical, skip the iteration
                if (entity1.getId().equals(entity2.getId())) {
                    continue;
                }

                // Each entity has a type. This way we can ensure low coupling and not import modules
                if (collides(entity1, entity2)) {
                    if (entity1.getType().equals("BULLET") && entity1.getOwner().equals("PLAYER") && entity2 instanceof Asteroid asteroid) {
                        entitiesToRemove.add(entity1);
                        // Sets split to true. AsteroidSystem handles splitting to ensure high cohesion
                        asteroid.setShouldSplit(true);
                        gameData.addScore(25);
                    }

                    if (entity1.getType().equals("BULLET") && entity1.getOwner().equals("PLAYER") && entity2.getType().equals("ENEMY")) {
                        entitiesToRemove.add(entity1);
                        entitiesToRemove.add(entity2);
                        gameData.addScore(50);
                    }

                    if(entity1.getType().equals("PLAYER") && entity2.getType().equals("ASTEROID")){
                        entitiesToRemove.add(entity1);
                        entitiesToRemove.add(entity2);
                        System.out.println("GAME OVER");
                        gameData.setGameOver(true);
                    }

                    if (entity1.getType().equals("PLAYER") && entity2.getType().equals("BULLET") && entity2.getOwner().equals("ENEMY")){
                        entitiesToRemove.add(entity1);
                        entitiesToRemove.add(entity2);
                        System.out.println("GAME OVER");
                        gameData.setGameOver(true);
                    }
                }
            }
        }

        for (Entity entity : entitiesToRemove) {
            world.removeEntity(entity);
        }
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx =  entity1.getX() -  entity2.getX();
        float dy =  entity1.getY() - entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}
