package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProjectBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public ProjectBootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
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
        Long vendorCount = vendorRepository.count().block();
        log.info("Vendor Records: " + vendorCount);
        if (categoryCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void initData() {
        initCategories();
        initVendors();
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

    private void initVendors() {
        Vendor cat1 = new Vendor();
        cat1.setFirstName("Evan");
        cat1.setLastName("Wood");
        vendorRepository.save(cat1).block();

        Vendor cat2 = new Vendor();
        cat2.setFirstName("Thandie");
        cat2.setLastName("Newton");
        vendorRepository.save(cat2).block();

        Vendor cat3 = new Vendor();
        cat3.setFirstName("Jeffrey");
        cat3.setLastName("Wright");
        vendorRepository.save(cat3).block();

        Vendor cat4 = new Vendor();
        cat4.setFirstName("Ingrid");
        cat4.setLastName("Berdal");
        vendorRepository.save(cat4).block();
    }
}
