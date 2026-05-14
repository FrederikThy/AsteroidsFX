import dk.sdu.cbse.common.bullet.BulletSPI;


module Player {
    requires Common;
    requires CommonBullet;
    requires java.desktop;
    requires javafx.graphics;

    uses BulletSPI;

    provides dk.sdu.cbse.common.IGamePluginService with dk.sdu.cbse.player.PlayerPlugin;

    provides dk.sdu.cbse.common.IEntityProcessingService with dk.sdu.cbse.player.PlayerSystem;
}
