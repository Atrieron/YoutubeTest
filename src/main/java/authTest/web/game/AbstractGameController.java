package authTest.web.game;

import authTest.model.Game;
import authTest.service.GameService;
import authTest.to.GameSearchTo;
import authTest.util.ToUtil;
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
            if(game!=null)
                res.add(ToUtil.toGameSearchTo(game));
        }
        return res;
    }
}