import dk.sdu.cbse.common.bullet.BulletSPI;

module Enemy {
    requires Common;
    requires CommonBullet;
    requires java.desktop;
    requires javafx.graphics;

    uses BulletSPI;

    provides dk.sdu.cbse.common.IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
    provides dk.sdu.cbse.common.IEntityProcessingService with dk.sdu.cbse.enemy.EnemySystem;
}