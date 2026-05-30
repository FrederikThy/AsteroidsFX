import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;


module Player {
    requires Common;
    requires CommonBullet;
    requires java.desktop;
    requires javafx.graphics;

    uses BulletSPI;

    provides IGamePluginService with dk.sdu.cbse.player.PlayerPlugin;

    provides IEntityProcessingService with dk.sdu.cbse.player.PlayerSystem;
}


