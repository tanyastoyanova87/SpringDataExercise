package bg.softuni.P01BookShop.data.repositories;

import bg.softuni.P01BookShop.data.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Set<Book> findBooksByReleaseDateAfter(LocalDate releaseDate);
    Set<Book> findBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle(String firstName, String lastName);
}
