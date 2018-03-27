package youtubeTest.service;

import youtubeTest.model.Game;
import youtubeTest.model.Image;

import java.util.List;

public interface GameService {
    Game saveBySteamId(String id) throws Exception;
    Game getById(Integer id);
    Image getImageByGameId(int imageId);
    List<Game> getAll();
    Game getBySteamId(String idString);
    List<Game> getBySubstring(String subString);

    void saveImage(Image gamePic);
}