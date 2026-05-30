package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 *
 * Interface used for initializing or removing entities and game functionality
 *
 * Pre-conditions:
 * - gameData must not be null
 * - world must not be null
 *
 * Post-conditions:
 * - Entities may have been updated or removed, depending on game logic.
 */
public interface IGamePluginService {
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);

}
