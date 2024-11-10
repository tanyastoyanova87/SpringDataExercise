package bg.softuni.P01BookShop.service;

import bg.softuni.P01BookShop.data.entities.Author;

import java.io.IOException;

public interface AuthorService {
    void seedAuthors() throws IOException;
    Author getRandomAuthor();
    boolean areAuthorsInserted();

    void printAuthorsNameWithBooksBefore1990();

    void printAuthorsOrderedByNumberOfBooks();
}
