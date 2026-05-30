import dk.sdu.cbse.common.services.IPostProcessingService;

module Collision {
    requires Common;
    requires CommonAsteroid;

    provides IPostProcessingService with dk.sdu.cbse.collision.CollisionSystem;
}