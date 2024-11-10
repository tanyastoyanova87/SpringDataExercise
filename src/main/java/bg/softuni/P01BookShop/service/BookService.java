package bg.softuni.P01BookShop.service;

import java.io.IOException;

public interface BookService {
    void seedBooks() throws IOException;
    boolean areBooksInserted();
    void printTitlesOfBooksAfter2000();

    void printBooksByGeorgePowell();
}
