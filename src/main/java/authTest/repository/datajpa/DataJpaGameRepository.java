package authTest.repository.datajpa;

import authTest.model.Game;
import authTest.model.Image;
import authTest.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DataJpaGameRepository implements GameRepository {
    @Autowired
    CrudGameRepository repository;

    @Autowired
    CrudImageRepository imageRepository;

    @Override
    public List<Game> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Game save(Game game) {
        return repository.save(game);
    }

    @Override
    @Transactional
    public Game getById(Integer id) {
        Game game = repository.getOne(id);
        Game cloneGame = new Game();
        cloneGame.setId(game.getId());
        cloneGame.setName(game.getName());
        return cloneGame;
    }

    @Override
    public Game getBySteamId(String idString) {
        return repository.findBySteamId(idString);
    }

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image getImageByGameId(int imageId) {
        return imageRepository.findByGameId(imageId);
    }
}