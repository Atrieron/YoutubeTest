package youtubeTest.repository;

import java.util.List;

import youtubeTest.model.YoutubeVideo;

public interface YoutubeVideoRepository {
	List<YoutubeVideo> getAll();
	List<YoutubeVideo> getByGameId(Integer id);
	YoutubeVideo save(YoutubeVideo user);
	YoutubeVideo create(YoutubeVideo youtubeVideo);
}