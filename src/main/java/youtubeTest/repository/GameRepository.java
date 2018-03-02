package youtubeTest.repository;

import youtubeTest.model.Game;
import youtubeTest.model.Image;

import java.util.List;

public interface GameRepository {
    List<Game> getAll();
    Game save(Game game);
    Game getById(Integer id);
    Game getBySteamId(String idString);

    Image saveImage(Image image);
    Image getImageByGameId(int imageId);

    List<Game> getBySubstring(String subString);
}
