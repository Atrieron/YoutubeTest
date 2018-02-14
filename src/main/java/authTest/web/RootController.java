package authTest.web;

import authTest.model.Game;
import authTest.repository.GameRepository;
import authTest.repository.YoutubeVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {
	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private YoutubeVideoRepository videoRepository;
	
    @GetMapping("/")
    public String root(ModelMap model) {
    	//model.addAttribute("videos", repository.getAll());
        model.addAttribute("games", gameRepository.getAll());
        return "gamelist";
    }

    @GetMapping("/game/{id}")
    public String gamePage(ModelMap model, @PathVariable("id") Integer id) {
        Game game = gameRepository.getById(id);
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
        Game game = gameRepository.getById(imageId);
        return game.getImage();
    }
}