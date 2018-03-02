package youtubeTest.web.game;

import youtubeTest.model.Game;
import youtubeTest.service.GameService;
import youtubeTest.to.GameSearchTo;
import youtubeTest.util.NetHelper;
import youtubeTest.util.ToUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AbstractGameController {
    @Autowired
    GameService gameService;

    private static final String STEAMSHOP_LINK = "http://store.steampowered.com/app/";

    public List<GameSearchTo> getAll(String searchString) {
        List<GameSearchTo> res = new ArrayList<>();
        if(searchString.startsWith(STEAMSHOP_LINK)){
            String idString = searchString.substring(STEAMSHOP_LINK.length());
            if(idString.contains("/")){
                idString = idString.substring(0, idString.indexOf("/"));
            }
            Game game = gameService.getBySteamId(idString);
            if(game==null){
                try {
                    GameSearchTo newGame = NetHelper.getBySteamId(idString);
                    if(newGame!=null){
                        newGame.setDescriprion(null);
                        res.add(newGame);
                    }
                } catch (Exception e) {
                }
            } else {
                res.add(ToUtil.toGameSearchTo(game));
            }
        } else {
            gameService.getBySubstring(searchString).stream().forEach(el->res.add(ToUtil.toGameSearchTo(el)));
        }
        return res;
    }

    public Game registerSteamGame(String steamId) {
        Game game = gameService.getBySteamId(steamId);
        if(game==null){
            try {
                game = gameService.saveBySteamId(steamId);
            } catch (Exception e) {
                return null;
            }
        }
        return game;
    }
}