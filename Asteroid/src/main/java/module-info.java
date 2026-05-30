import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires java.desktop;
    requires javafx.graphics;
    requires CommonAsteroid;

    uses dk.sdu.cbse.commonAsteroid.AsteroidSPI;

    provides IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroid.AsteroidSystem;
    provides dk.sdu.cbse.commonAsteroid.AsteroidSPI with dk.sdu.cbse.asteroid.AsteroidPlugin;
}