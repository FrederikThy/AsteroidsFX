module Core {
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    requires spring.beans;
    requires spring.core;

    uses dk.sdu.cbse.common.IGamePluginService;
    uses dk.sdu.cbse.common.IEntityProcessingService;
    uses dk.sdu.cbse.common.IPostProcessingService;

    exports dk.sdu.cbse.core to javafx.graphics;

    opens dk.sdu.cbse.core to spring.beans, spring.core, spring.context;
}
