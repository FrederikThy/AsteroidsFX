import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.enemy.EnemySPI;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires CommonBullet;
    requires CommonEnemy;
    requires java.desktop;
    requires javafx.graphics;

    uses BulletSPI;
    uses EnemySPI;

    provides IGamePluginService with dk.sdu.cbse.enemy.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemy.EnemySystem;

    provides EnemySPI with dk.sdu.cbse.enemy.EnemyPlugin;
}