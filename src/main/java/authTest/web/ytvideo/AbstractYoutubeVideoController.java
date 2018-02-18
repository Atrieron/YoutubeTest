package authTest.web.ytvideo;

import authTest.model.YoutubeVideo;
import authTest.repository.YoutubeVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractYoutubeVideoController {
    @Autowired
    private YoutubeVideoRepository repository;

    public YoutubeVideo create(YoutubeVideo youtubeVideo) {
        return repository.create(youtubeVideo);
    }
}
