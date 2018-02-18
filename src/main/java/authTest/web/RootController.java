package authTest.web;

import authTest.model.Game;
import authTest.repository.GameRepository;
import authTest.repository.YoutubeVideoRepository;
import authTest.service.GameService;
import authTest.to.GameSearchTo;
import authTest.util.ToUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {
	@Autowired
	private YoutubeVideoRepository videoRepository;

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
        model.addAttribute("videos", videoRepository.getByGameId(id));
        return "gamepage";
    }

    @GetMapping(value = "/imageController/{imageId}")
    @ResponseBody
    public byte[] helloWorld(@PathVariable int imageId)  {
        return gameService.getImageByGameId(imageId).getData();
    }
}