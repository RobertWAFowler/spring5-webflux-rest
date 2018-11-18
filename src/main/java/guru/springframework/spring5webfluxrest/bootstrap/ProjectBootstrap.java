package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vender;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import guru.springframework.spring5webfluxrest.repository.VenderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProjectBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final VenderRepository venderRepository;

    public ProjectBootstrap(CategoryRepository categoryRepository, VenderRepository venderRepository) {
        this.categoryRepository = categoryRepository;
        this.venderRepository = venderRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!dataExists()) {
            initData();
        }
    }

    private boolean dataExists() {

        Long categoryCount = categoryRepository.count().block();
        log.info("Category Records: " + categoryCount);
        Long venderCount = venderRepository.count().block();
        log.info("Vender Records: " + venderCount);
        if (categoryCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void initData() {
        initCategories();
        initVenders();
    }

    private void initCategories() {
        Category cat1 = new Category();
        cat1.setDescription("American");
        categoryRepository.save(cat1).block();

        Category cat2 = new Category();
        cat2.setDescription("Italian");
        categoryRepository.save(cat2).block();

        Category cat3 = new Category();
        cat3.setDescription("Mexican");
        categoryRepository.save(cat3).block();

        Category cat4 = new Category();
        cat4.setDescription("Fast Food");
        categoryRepository.save(cat4).block();
    }

    private void initVenders() {
        Vender cat1 = new Vender();
        cat1.setFirstname("Evan");
        cat1.setLastname("Wood");
        venderRepository.save(cat1).block();

        Vender cat2 = new Vender();
        cat2.setFirstname("Thandie");
        cat2.setLastname("Newton");
        venderRepository.save(cat2).block();

        Vender cat3 = new Vender();
        cat3.setFirstname("Jeffrey");
        cat3.setLastname("Wright");
        venderRepository.save(cat3).block();

        Vender cat4 = new Vender();
        cat4.setFirstname("Ingrid");
        cat4.setLastname("Berdal");
        venderRepository.save(cat4).block();
    }
}
