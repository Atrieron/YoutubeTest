package youtubeTest.service;

import youtubeTest.model.Game;
import youtubeTest.model.Image;
import youtubeTest.repository.GameRepository;
import youtubeTest.util.NetHelper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;

    @Override
    public Game saveBySteamId(String id) throws Exception {
        String st = NetHelper.readUrl("http://store.steampowered.com/api/appdetails?appids="+id);
        String substr = "{\"" + id + "\":";
        if(st.startsWith(substr)) {
            st = st.substring(substr.length());
            st = st.substring(0, st.length()-1);
            if(st.startsWith("{\"success\":true,\"data\":")) {
                st = st.substring("{\"success\":true,\"data\":".length());
                st = st.substring(0, st.length()-1);
                JSONObject jo = new JSONObject(st);
                Game game = new Game();
                game.setSteamId(id);
                if(jo.has("name")){
                    game.setName(jo.get("name").toString());
                }
                if(jo.has("short_description")){
                    game.setDescription(jo.get("short_description").toString());
                }
                game = gameRepository.save(game);
                if(game!=null) {
                    if (jo.has("header_image")) {
                        Image image = new Image();
                        image.setGame(game);
                        image.setData(NetHelper.getImage(jo.get("header_image").toString()));
                        gameRepository.saveImage(image);
                    }

                    return game;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    @Transactional
    public Game getById(Integer id) {
        return gameRepository.getById(id);
    }

    @Override
    public Image getImageByGameId(int imageId) {
        return gameRepository.getImageByGameId(imageId);
    }

    @Override
    public List<Game> getAll() {
        return gameRepository.getAll();
    }

    @Override
    public Game getBySteamId(String idString) {
        return gameRepository.getBySteamId(idString);
    }

    @Override
    public List<Game> getBySubstring(String subString) {
        return gameRepository.getBySubstring(subString);
    }
}
