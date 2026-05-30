package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 *
 * Interface for processing systems
 * The systems that are called will run the logic for the behavior of entities
 *
 * Pre-conditions:
 * - gameData must not be null
 * - world must not be null
 *
 * Post-conditions:
 * - Entities may have been updated, depending on game logic.
 */
public interface IEntityProcessingService {
    void process(GameData gameData, World world);
}
