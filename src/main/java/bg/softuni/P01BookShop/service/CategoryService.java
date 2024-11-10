package bg.softuni.P01BookShop.service;

import bg.softuni.P01BookShop.data.entities.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;
    Set<Category> getRandomCategories();
    boolean areCategoriesInserted();
}
