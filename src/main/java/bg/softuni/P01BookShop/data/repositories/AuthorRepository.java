package bg.softuni.P01BookShop.data.repositories;

import bg.softuni.P01BookShop.data.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Set<Author> getAuthorsByBooksReleaseDateBefore(LocalDate books_releaseDate);
    Set<Author> findAllAuthorsByOrderByBooksDesc();
}
