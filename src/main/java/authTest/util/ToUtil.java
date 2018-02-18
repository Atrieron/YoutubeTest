package authTest.util;

import authTest.model.Game;
import authTest.to.GameSearchTo;

/**
 * Created by A3 on 18.02.2018.
 */
public class ToUtil {
    public static GameSearchTo toGameSearchTo(Game game){
        return new GameSearchTo(game.getId(), game.getName(), game.getSteamId(), "/imageController/"+game.getId());
    }
}