package youtubeTest.repository.datajpa;

import youtubeTest.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudImageRepository extends JpaRepository<Image, Integer> {
    Image findByGameId(Integer gameId);
}