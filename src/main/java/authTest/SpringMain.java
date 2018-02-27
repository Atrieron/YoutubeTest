package authTest;

import authTest.repository.GameRepository;
import authTest.service.GameService;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] param) throws InterruptedException {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/mock.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            GameRepository gameRepository = (GameRepository) appCtx.getBean("dataJpaGameRepository");
            gameRepository.getBySubstring("showdown").forEach(el->System.out.println(el));
        }
    }
}