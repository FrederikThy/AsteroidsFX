import dk.sdu.cbse.bullet.BulletPlugin;
import dk.sdu.cbse.bullet.BulletSystem;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.bullet.BulletSPI;

module Bullet {
    requires Common;
    requires CommonBullet;
    requires java.desktop;
    requires javafx.graphics;

    provides IGamePluginService with BulletPlugin;
    provides IEntityProcessingService with BulletSystem;
    provides BulletSPI with BulletPlugin;
}
