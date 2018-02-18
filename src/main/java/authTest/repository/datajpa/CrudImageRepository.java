package authTest.repository.datajpa;

import authTest.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudImageRepository extends JpaRepository<Image, Integer> {
    Image findByGameId(Integer gameId);
}