module Core {
    requires Common;
    requires javafx.graphics;

    uses dk.sdu.cbse.common.IGamePluginService;
    uses dk.sdu.cbse.common.IEntityProcessingService;
    uses dk.sdu.cbse.common.IPostProcessingService;

    exports dk.sdu.cbse.core to javafx.graphics;
}
