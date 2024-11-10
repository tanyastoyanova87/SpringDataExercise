package bg.softuni.P01BookShop.controller;

import bg.softuni.P01BookShop.data.repositories.CategoryRepository;
import bg.softuni.P01BookShop.service.AuthorService;
import bg.softuni.P01BookShop.service.BookService;
import bg.softuni.P01BookShop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public ConsoleRunner(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!categoryService.areCategoriesInserted()) {
            this.categoryService.seedCategories();
        }

        if (!authorService.areAuthorsInserted()) {
            this.authorService.seedAuthors();
        }

        if (!bookService.areBooksInserted()) {
            this.bookService.seedBooks();
        }

        this.bookService.printTitlesOfBooksAfter2000();
        this.authorService.printAuthorsNameWithBooksBefore1990();
        this.authorService.printAuthorsOrderedByNumberOfBooks();
        this.bookService.printBooksByGeorgePowell();
    }
}
