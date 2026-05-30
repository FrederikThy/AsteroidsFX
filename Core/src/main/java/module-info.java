import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostProcessingService;

module Core {
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    requires spring.beans;
    requires spring.core;

    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostProcessingService;

    exports dk.sdu.cbse.core to javafx.graphics;

    opens dk.sdu.cbse.core to spring.beans, spring.core, spring.context;
}
