package youtubeTest.util;

import youtubeTest.model.Game;
import youtubeTest.model.YoutubeVideo;
import youtubeTest.to.GameSearchTo;
import youtubeTest.to.YoutubeVideoTo;

/**
 * Created by A3 on 18.02.2018.
 */
public class ToUtil {
    public static GameSearchTo toGameSearchTo(Game game){
        return new GameSearchTo(game.getId(), game.getName(), game.getSteamId(), null);
    }

    public static YoutubeVideoTo toYoutubeVideoTo(YoutubeVideo youtubeVideo){
        return new YoutubeVideoTo(youtubeVideo.getId(), youtubeVideo.getName(), youtubeVideo.getYoutubeId(), youtubeVideo.getStartTime());
    }
}