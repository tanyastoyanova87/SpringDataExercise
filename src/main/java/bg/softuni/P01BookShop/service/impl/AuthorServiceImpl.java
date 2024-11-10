package bg.softuni.P01BookShop.service.impl;

import bg.softuni.P01BookShop.data.entities.Author;
import bg.softuni.P01BookShop.data.repositories.AuthorRepository;
import bg.softuni.P01BookShop.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final String AUTHOR_FILE_URL = "src/main/resources/files/authors.txt";

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(AUTHOR_FILE_URL))
                .forEach(line -> {
                    String[] tokens = line.split(" ");
                    System.out.println(tokens[0]);
                    System.out.println(tokens[1]);
                    authorRepository.save(new Author(tokens[0], tokens[1]));
                });
    }

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom.current().nextLong(1, this.authorRepository.count() + 1);
        return this.authorRepository.findById(randomId).orElse(null);
    }

    @Override
    public boolean areAuthorsInserted() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public void printAuthorsNameWithBooksBefore1990() {
        this.authorRepository.getAuthorsByBooksReleaseDateBefore(LocalDate.of(1990, 1, 1))
                .forEach(author -> {
                    System.out.printf("%s %s%n", author.getFirstName(), author.getLastName());
                });
    }

    @Override
    public void printAuthorsOrderedByNumberOfBooks() {
        this.authorRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparingInt((Author author) -> author.getBooks().size()).reversed())
                .forEach(author ->
                System.out.printf("%s %s - %d%n",
                        author.getFirstName(), author.getLastName(), author.getBooks().size()));


    }
}
