package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollisionSystemTest {

    @Test
    void collidesReturnsTrueWhenEntitiesOverlap() {
        CollisionSystem collisionSystem = new CollisionSystem();

        Entity first = entityAt(100, 100, 10);
        Entity second = entityAt(105, 100, 10);

        assertTrue(collisionSystem.collides(first, second));
    }

    @Test
    void collidesReturnsFalseWhenEntitiesAreApart() {
        CollisionSystem collisionSystem = new CollisionSystem();

        Entity first = entityAt(100, 100, 10);
        Entity second = entityAt(200, 200, 10);

        assertFalse(collisionSystem.collides(first, second));
    }

    @Test
    void collidesReturnsFalseWhenEntitiesOnlyTouchAtRadiusBoundary() {
        CollisionSystem collisionSystem = new CollisionSystem();

        Entity first = entityAt(100, 100, 10);
        Entity second = entityAt(120, 100, 10);

        assertFalse(collisionSystem.collides(first, second));
    }

    private Entity entityAt(float x, float y, float radius) {
        Entity entity = new Entity();
        entity.setX(x);
        entity.setY(y);
        entity.setRadius(radius);
        return entity;
    }
}
