package youtubeTest;

import youtubeTest.repository.GameRepository;
import org.springframework.context.support.GenericXmlApplicationContext;

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