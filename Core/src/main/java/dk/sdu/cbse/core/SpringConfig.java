package dk.sdu.cbse.core;

import dk.sdu.cbse.common.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

@Configuration
public class SpringConfig {

    @Bean
    public ModuleLayer pluginLayer() {
        return new ModuleConfig().createPluginLayer();
    }
    @Bean
    public GameData gameData() {
        return new GameData();
    }

    @Bean
    public World world(){
        return new World();
    }

    @Bean
    public List<IGamePluginService> pluginServices(ModuleLayer pluginLayer){
        return getPluginServices(pluginLayer, IGamePluginService.class);
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServices(ModuleLayer pluginLayer){
        return getPluginServices(pluginLayer, IEntityProcessingService.class);
    }
    @Bean
    public List<IPostProcessingService> postProcessingServices(ModuleLayer pluginLayer){
        return getPluginServices(pluginLayer, IPostProcessingService.class);
    }

    @Bean
    public Game game(GameData gameData, World world, List<IGamePluginService> pluginServices, List<IEntityProcessingService> entityProcessingServices, List<IPostProcessingService> postProcessingServices){

        return new Game(gameData, world, pluginServices, entityProcessingServices, postProcessingServices);
    }

    private <T> List<T> getPluginServices(ModuleLayer pluginLayer, Class<T> serviceType){
        List<T> services = new ArrayList<>();

        for (T service : ServiceLoader.load(pluginLayer, serviceType)) {
            services.add(service);
        }
        return services;
    }
}
