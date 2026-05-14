package dk.sdu.cbse.common;

/**
 *
 * Interface for processing systems
 * The systems are updated each frame using AnimationTimer in Core
 *
 * Pre-conditions:
 * - gameData must not be null
 * - world must not be null
 *
 * Post-conditions:
 * - Each entity has been stored in world, and entities may have updated state.
 */
public interface IEntityProcessingService {
    void process(GameData gameData, World world);
}
