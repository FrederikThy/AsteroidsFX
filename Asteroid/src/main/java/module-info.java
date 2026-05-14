import dk.sdu.cbse.commonAsteroid.AsteroidSPI;

module Asteroid {
    requires Common;
    requires java.desktop;
    requires javafx.graphics;
    requires CommonAsteroid;

    uses dk.sdu.cbse.commonAsteroid.AsteroidSPI;

    provides dk.sdu.cbse.common.IGamePluginService with dk.sdu.cbse.asteroid.AsteroidPlugin;
    provides dk.sdu.cbse.common.IEntityProcessingService with dk.sdu.cbse.asteroid.AsteroidSystem;
    provides dk.sdu.cbse.commonAsteroid.AsteroidSPI with dk.sdu.cbse.asteroid.AsteroidPlugin;
}