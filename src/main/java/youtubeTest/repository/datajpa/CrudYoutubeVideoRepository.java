package youtubeTest.repository.datajpa;

import youtubeTest.model.YoutubeVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrudYoutubeVideoRepository extends JpaRepository<YoutubeVideo, Integer> {
    List<YoutubeVideo> getByGameId(Integer gameId);
}