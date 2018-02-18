package authTest.repository.datajpa;

import authTest.model.Game;
import authTest.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudGameRepository extends JpaRepository<Game, Integer> {
    Game findBySteamId(String idString);
}