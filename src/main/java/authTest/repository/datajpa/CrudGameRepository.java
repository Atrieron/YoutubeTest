package authTest.repository.datajpa;

import authTest.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudGameRepository extends JpaRepository<Game, Integer> {
}