import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.enemy.EnemySPI;

module Enemy {
    requires Common;
    requires CommonBullet;
    requires CommonEnemy;
    requires java.desktop;
    requires javafx.graphics;

    uses BulletSPI;
    uses EnemySPI;

    provides dk.sdu.cbse.common.IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
    provides dk.sdu.cbse.common.IEntityProcessingService with dk.sdu.cbse.enemy.EnemySystem;

    provides EnemySPI with dk.sdu.cbse.enemy.EnemyPlugin;
}