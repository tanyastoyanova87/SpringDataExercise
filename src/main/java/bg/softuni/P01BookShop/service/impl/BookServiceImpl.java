package bg.softuni.P01BookShop.service.impl;

import bg.softuni.P01BookShop.data.entities.Author;
import bg.softuni.P01BookShop.data.entities.Book;
import bg.softuni.P01BookShop.data.entities.Category;
import bg.softuni.P01BookShop.data.entities.enums.AgeRestriction;
import bg.softuni.P01BookShop.data.entities.enums.EditionType;
import bg.softuni.P01BookShop.data.repositories.BookRepository;
import bg.softuni.P01BookShop.data.repositories.CategoryRepository;
import bg.softuni.P01BookShop.service.AuthorService;
import bg.softuni.P01BookShop.service.BookService;
import bg.softuni.P01BookShop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final String BOOK_FILE_URL = "src/main/resources/files/books.txt";
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           CategoryRepository categoryRepository,
                           CategoryService categoryService,
                           AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOK_FILE_URL))
                .forEach(line -> {
                    String[] tokens = line.split("\\s+");

                    EditionType editionType = EditionType.values()[Integer.parseInt(tokens[0])];
                    LocalDate date = LocalDate.parse(tokens[1],
                            DateTimeFormatter.ofPattern("d/M/yyyy"));

                    int copies = Integer.parseInt(tokens[2]);
                    BigDecimal price = new BigDecimal(tokens[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tokens[4])];

                    String title = Arrays
                            .stream(tokens).skip(5)
                            .collect(Collectors.joining(" "));

                    Set<Category> categories = categoryService.getRandomCategories();
                    Author author = authorService.getRandomAuthor();

                    Book book = new Book
                            (title, editionType, price, date,
                                    ageRestriction, author, categories, copies);

                    bookRepository.save(book);
                });
    }

    @Override
    public boolean areBooksInserted() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public void printTitlesOfBooksAfter2000() {
        this.bookRepository.findBooksByReleaseDateAfter(LocalDate.of(2000, 12, 31))
                .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printBooksByGeorgePowell() {
        this.bookRepository
                .findBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle
                        ("George", "Powell")
                .forEach(book -> System.out.printf("%s %s %s%n",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()));
    }
}
