package dk.sdu.cbse.common;

/**
 *
 *  DOCUMENTATION
 *  POST CONDITIONS AND PRE CONDITIONS
 *
 */
public interface IGamePluginService {
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);

}
