package youtubeTest.repository.datajpa;

import youtubeTest.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudGameRepository extends JpaRepository<Game, Integer> {
    Game findBySteamId(String idString);
}