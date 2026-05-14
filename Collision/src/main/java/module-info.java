module Collision {
    requires Common;
    requires CommonAsteroid;

    provides dk.sdu.cbse.common.IPostProcessingService with dk.sdu.cbse.collision.CollisionSystem;
}