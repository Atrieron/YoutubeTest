package authTest.web.ytvideo;

import authTest.model.YoutubeVideo;
import authTest.service.GameService;
import authTest.util.YoutubeLinkHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/ajax/video")
public class VideoAjaxController extends AbstractYoutubeVideoController {
    @Autowired
    GameService gameService;

    @PostMapping
    public ResponseEntity<String> create(@RequestParam("gameId") Integer gameId,
                                         @RequestParam("description") String description,
                                         @RequestParam("link") String link) {
        YoutubeVideo newVideo = YoutubeLinkHelper.getByLink(link,description);
        newVideo.setGame(gameService.getById(gameId));
        super.create(newVideo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}