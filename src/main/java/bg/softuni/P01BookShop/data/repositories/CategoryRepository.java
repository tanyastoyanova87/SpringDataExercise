package bg.softuni.P01BookShop.data.repositories;

import bg.softuni.P01BookShop.data.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
