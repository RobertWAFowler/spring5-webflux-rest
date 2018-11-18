package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProjectBootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public ProjectBootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
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
        categoryRepository.save(Category.builder().description("Fruits").build())
                .block();

        categoryRepository.save(Category.builder().description("Nuts").build())
                .block();
        categoryRepository.save(Category.builder().description("Breads").build())
                .block();

        categoryRepository.save(Category.builder().description("Meats").build())
                .block();
        categoryRepository.save(Category.builder().description("Eggs").build())
                .block();
    }

    private void initVendors() {
        vendorRepository.save(Vendor.builder().firstName("Joe").lastName("Buck").build()).block();

        vendorRepository.save(Vendor.builder().firstName("Micheal").lastName("Weston").build()).block();

        vendorRepository.save(Vendor.builder().firstName("Jessie").lastName("Waters").build()).block();

        vendorRepository.save(Vendor.builder().firstName("Bill").lastName("Nershi").build()).block();

        vendorRepository.save(Vendor.builder().firstName("Jimmy").lastName("Buffett").build()).block();
    }


}
