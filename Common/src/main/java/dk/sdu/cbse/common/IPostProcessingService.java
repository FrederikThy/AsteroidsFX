package dk.sdu.cbse.common;

/**
 *
 * Processes the final state of entities.
 *
 * Pre-conditions:
 * - gameData must not be null
 * - world must not be null
 *
 * Post-conditions:
 * - Entities may have been updated or removed, depending on game logic.
 */
public interface IPostProcessingService {

    void process(GameData gameData, World world);
}
