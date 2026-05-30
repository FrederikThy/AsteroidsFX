package dk.sdu.cbse.common.data;

import java.util.*;

public class World {

    private final Map<String, Entity>  entities = new HashMap<>();

    // Add entity to HashMap
    public void addEntity(Entity entity) {
        entities.put(entity.getId().toString(), entity);
    }

    //Remove entity from hashmap
    public void removeEntity(Entity entity) {
        entities.remove(entity.getId().toString());
    }

    // Get all values from HashMap
    public Collection<Entity> getEntities() {
        return entities.values();
    }
}
