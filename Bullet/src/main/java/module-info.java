import dk.sdu.cbse.BulletPlugin;
import dk.sdu.cbse.BulletSystem;
import dk.sdu.cbse.common.IEntityProcessingService;
import dk.sdu.cbse.common.IGamePluginService;
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
