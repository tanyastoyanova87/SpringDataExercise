package bg.softuni.P01BookShop.service.impl;

import bg.softuni.P01BookShop.data.entities.Category;
import bg.softuni.P01BookShop.data.repositories.CategoryRepository;
import bg.softuni.P01BookShop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final String CATEGORY_FILE_URL = "src/main/resources/files/categories.txt";

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        Set<Category> categories = new HashSet<>();

        Files.readAllLines(Path.of(CATEGORY_FILE_URL))
                .stream()
                .filter(line -> !line.trim().isEmpty())
                .forEach(line -> categories.add(new Category(line)));

        this.categoryRepository.saveAll(categories);
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        int randomCount = ThreadLocalRandom.current().nextInt(1, 4);
        for (int i = 0; i < randomCount; i++) {
            long randomId = ThreadLocalRandom.current()
                    .nextLong(1, this.categoryRepository.count() + 1);
            this.categoryRepository.findById((int) randomId).ifPresent(categories::add);
        }

        return categories;
    }

    @Override
    public boolean areCategoriesInserted() {
        return this.categoryRepository.count() > 0;
    }
}
