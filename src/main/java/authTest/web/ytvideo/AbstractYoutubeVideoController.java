package authTest.web.ytvideo;

import authTest.model.YoutubeVideo;
import authTest.repository.YoutubeVideoRepository;
import authTest.to.YoutubeVideoTo;
import authTest.util.ToUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class AbstractYoutubeVideoController {
    @Autowired
    private YoutubeVideoRepository repository;

    public YoutubeVideo create(YoutubeVideo youtubeVideo) {
        return repository.create(youtubeVideo);
    }

    public List<YoutubeVideoTo> getVideoByGame(Integer gameId) {
        return repository.getByGameId(gameId).stream().map(el-> ToUtil.toYoutubeVideoTo(el)).collect(Collectors.toList());
    }
}
