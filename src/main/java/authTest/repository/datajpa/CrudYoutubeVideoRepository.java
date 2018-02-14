package authTest.repository.datajpa;

import authTest.model.YoutubeVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrudYoutubeVideoRepository extends JpaRepository<YoutubeVideo, Integer> {
    List<YoutubeVideo> getByGameId(Integer gameId);
}