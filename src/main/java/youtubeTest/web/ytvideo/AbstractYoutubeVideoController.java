package youtubeTest.web.ytvideo;

import youtubeTest.model.YoutubeVideo;
import youtubeTest.repository.YoutubeVideoRepository;
import youtubeTest.to.YoutubeVideoTo;
import youtubeTest.util.ToUtil;
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
