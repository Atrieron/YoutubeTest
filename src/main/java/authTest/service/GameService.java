package authTest.service;

import authTest.model.Game;
import authTest.model.Image;

import java.util.List;

public interface GameService {
    Game saveBySteamId(String id) throws Exception;
    Game getById(Integer id);
    Image getImageByGameId(int imageId);
    List<Game> getAll();
    Game getBySteamId(String idString);
}