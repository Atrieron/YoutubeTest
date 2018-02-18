package authTest.repository.datajpa;

import authTest.model.YoutubeVideo;
import authTest.repository.YoutubeVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DataJpaYoutubeVideoRepository implements YoutubeVideoRepository {
    @Autowired
    CrudYoutubeVideoRepository crudYoutubeVideoRepository;

    @Override
    public List<YoutubeVideo> getAll() {
        return crudYoutubeVideoRepository.findAll();
    }

    @Override
    public List<YoutubeVideo> getByGameId(Integer id) {
        return crudYoutubeVideoRepository.getByGameId(id);
    }

    @Override
    @Transactional
    public YoutubeVideo save(YoutubeVideo youtubeVideo) {
        return crudYoutubeVideoRepository.save(youtubeVideo);
    }

    @Override
    public YoutubeVideo create(YoutubeVideo youtubeVideo) {
        return crudYoutubeVideoRepository.save(youtubeVideo);
    }
}