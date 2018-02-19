package authTest.web;

import authTest.model.Game;
import authTest.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class RootController {
	@Autowired
	private GameService gameService;
	
    @GetMapping("/")
    public String root(ModelMap model) {
        model.addAttribute("games", gameService.getAll());
        return "gamelist";
    }

    @GetMapping("/search")
    public String search(ModelMap model) {
        return "searchpage";
    }

    @GetMapping("/game/{id}")
    public String gamePage(ModelMap model, @PathVariable("id") Integer id) {
        Game game = gameService.getById(id);
        if(game==null){
            return "/";
        }
        model.addAttribute("game",game);
        return "gamepage";
    }

    @GetMapping(value = "/imageController/{imageId}")
    @ResponseBody
    public byte[] getImageById(@PathVariable int imageId)  {
        return gameService.getImageByGameId(imageId).getData();
    }
}