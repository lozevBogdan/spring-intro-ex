package com.softuni.springintroex.Serveses;


import java.io.IOException;

public interface AuthorService {

    void seedAuthorInDB() throws IOException;
    void findAuthorsByFirstNameEndingWith(String end);

}
