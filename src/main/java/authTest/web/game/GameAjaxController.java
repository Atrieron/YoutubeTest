package authTest.web.game;

import authTest.model.Game;
import authTest.to.GameSearchTo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/game")
public class GameAjaxController extends AbstractGameController {
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GameSearchTo> getAll(@RequestParam("searchString") String searchString, HttpServletRequest request) {
        List<GameSearchTo> searchResult = super.getAll(searchString);
        for(GameSearchTo gameSearchTo: searchResult){
            if(gameSearchTo.getImg_path()==null){
                gameSearchTo.setImg_path(request.getContextPath()+"/imageController/"+gameSearchTo.getId());
            }
        }
        return searchResult;
    }

    @PostMapping(value = "/vote")
    public void vote(@RequestParam("id") String id, @RequestParam("type") String idType) {
        if("steamId".equals(idType)){
            Game game = super.registerSteamGame(id);
        }
    }
}