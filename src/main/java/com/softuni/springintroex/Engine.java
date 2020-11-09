package com.softuni.springintroex;

import com.softuni.springintroex.Serveses.AuthorService;
import com.softuni.springintroex.Serveses.BookService;
import com.softuni.springintroex.Serveses.CategoryServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Engine implements CommandLineRunner {

    private final CategoryServis categoryServis;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public Engine(CategoryServis categoryServis, AuthorService authorService, BookService bookService) {
        this.categoryServis = categoryServis;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

      // this.seedData();

        //ex1
//        System.out.println("Please enter age restriction:");
//        String ageRest = reader.readLine();
//
//        this.bookService.printBookByAgeRestriction(ageRest);

        //ex2
        this.bookService.printAllBookByEditionTypeAndCopies();



    }

    public  void seedData() throws IOException {
        this.categoryServis.seedCategoriesInDB();
        this.authorService.seedAuthorInDB();
        this.bookService.seedDataInDB();

    }

}
