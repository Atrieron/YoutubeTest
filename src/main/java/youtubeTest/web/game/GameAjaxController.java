package youtubeTest.web.game;

import youtubeTest.model.Game;
import youtubeTest.to.GameSearchTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/game")
public class GameAjaxController extends AbstractGameController {
    @PostMapping(value = "/search")
    public ModelAndView getAll(@RequestParam("searchString") String searchString, HttpServletRequest request) {
        List<GameSearchTo> searchResult = super.getAll(searchString);
        for(GameSearchTo gameSearchTo: searchResult){
            if(gameSearchTo.getImg_path()==null){
                gameSearchTo.setImg_path(request.getContextPath()+"/imageController/"+gameSearchTo.getId());
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModelMap().addAttribute("games",searchResult);
        modelAndView.setViewName("searchResult");
        return modelAndView;
    }

    @PostMapping(value = "/vote")
    public void vote(@RequestParam("id") String id, @RequestParam("type") String idType) {
        if("steamId".equals(idType)){
            Game game = super.registerSteamGame(id);
        }
    }
}