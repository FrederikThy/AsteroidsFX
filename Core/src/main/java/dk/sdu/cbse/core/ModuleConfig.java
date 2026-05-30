package dk.sdu.cbse.core;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
public class ModuleConfig {
    // Tries to find a plugins folder
    private static final Path pluginsDirectory = Paths.get("plugins");

    public ModuleLayer createPluginLayer() {
        if (!Files.isDirectory(pluginsDirectory)) {
            return ModuleLayer.empty();
        }

        // Finds all modules in the directory
        ModuleFinder finder = ModuleFinder.of(pluginsDirectory);

        Set<String> moduleNames = finder.findAll().stream().map(moduleReference -> moduleReference.descriptor().name()).collect(Collectors.toSet());

        if (moduleNames.isEmpty()) {
            return ModuleLayer.empty();
        }

        Configuration configuration = ModuleLayer.boot().configuration().resolve(finder, ModuleFinder.of(), moduleNames);

        return ModuleLayer.boot()
                .defineModulesWithOneLoader(configuration, ClassLoader.getSystemClassLoader());
    }
}
