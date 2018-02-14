package authTest.repository;

import authTest.model.Game;

import java.util.List;

public interface GameRepository {
    List<Game> getAll();
    Game save(Game game);
    Game getById(Integer id);
}
