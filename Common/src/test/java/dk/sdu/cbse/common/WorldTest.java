package dk.sdu.cbse.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorldTest {

    @Test
    void addEntityStoresEntityInWorld() {
        World world = new World();
        Entity entity = new Entity();

        world.addEntity(entity);

        assertTrue(world.getEntities().contains(entity));
    }

    @Test
    void removeEntityRemovesEntityFromWorld() {
        World world = new World();
        Entity entity = new Entity();

        world.addEntity(entity);
        world.removeEntity(entity);

        assertFalse(world.getEntities().contains(entity));
    }
}
