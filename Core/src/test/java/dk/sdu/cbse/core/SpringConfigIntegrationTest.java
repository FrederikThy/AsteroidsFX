package dk.sdu.cbse.core;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpringConfigIntegrationTest {

    // Tests if the Spring Bean objects gets created at runtime.
    @Test
    void springContextCreatesGameRuntimeBeans() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(SpringConfig.class)) {
            assertNotNull(context.getBean(ModuleLayer.class));
            assertNotNull(context.getBean(GameData.class));
            assertNotNull(context.getBean(World.class));
            assertNotNull(context.getBean(Game.class));
            assertNotNull(context.getBean("pluginServices", List.class));
            assertNotNull(context.getBean("entityProcessingServices", List.class));
            assertNotNull(context.getBean("postProcessingServices", List.class));
        }
    }

}
