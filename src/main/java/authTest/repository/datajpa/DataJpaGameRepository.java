package authTest.repository.datajpa;

import authTest.model.Game;
import authTest.model.Image;
import authTest.repository.GameRepository;
import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

@Repository
public class DataJpaGameRepository implements GameRepository {
    @Autowired
    CrudGameRepository repository;

    @Autowired
    CrudImageRepository imageRepository;

    @Autowired
    EntityManager entityManager;

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
        cloneGame.setSteamId(game.getSteamId());
        cloneGame.setDescription(game.getDescription());
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

    @Override
    @Transactional
    public List<Game> getBySubstring(String subString) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Game.class).get();
        Query query = queryBuilder
                .keyword()
                .onField("name")
                .matching(subString)
                .createQuery();

        List<Game> resultList = fullTextEntityManager.createFullTextQuery(query, Game.class).getResultList();
        return resultList;
    }

    private void createLuceneIndex() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
    }
}