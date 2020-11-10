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

        System.out.println("Welcome to my homework 'Spring Data Advanced Quering'");
        System.out.println("Enter number (1-11) of exercise which you want " +
                "to check or 'STOP' for exit:");
        String input = reader.readLine();


        while (!input.equalsIgnoreCase("STOP")){

            switch (input){

                case "1":
                    System.out.println("Please enter age restriction:");
                    String ageRest = reader.readLine();
                    this.bookService.printBookByAgeRestriction(ageRest);
                    break;
                case "2":
                    this.bookService.printAllBookByEditionTypeAndCopies();
                    break;
                case "3":
                    this.bookService.printBooksTitleAndPriceBetween5And40();
                    break;
                case "4":
                    System.out.println("Please enter the released date(year)");
                    String date = reader.readLine();
                    this.bookService.printBooksTitleWithRealisedDateNotIn(date);
                    break;
                case "5":
                    System.out.println("Please enter data:");
                    String data = reader.readLine();
                    this.bookService.printBooksByRealisedDateBefore(data);
                    break;
                case "6":
                    System.out.println("Please enter a string:");
                    this.authorService.findAuthorsByFirstNameEndingWith(
                            reader.readLine());
                    break;
                case "7":
                    System.out.println("Please enter a string:");
                    this.bookService.printBooksTitleByContainingAGivenString
                            (reader.readLine());
                    break;
                case "8":
                    System.out.println("Please enter a string:");
                    this.bookService.printBooksTitleByAuthorLastNameStartingWith
                            (reader.readLine());
                    break;
                case "9":
                    System.out.println("Enter a minimum length of book title:");
                    this.bookService
                            .printBooksByTitleLengthMoreThan
                                    (Integer.parseInt(reader.readLine()));
                    break;
                case "10":
                    this.bookService.printTotalBooksByCopies();
                    break;
                case "11":
                    System.out.println("Please enter book title:");
                    this.bookService.printAllBookInfoByTitle(reader.readLine());
                    break;
            }

            System.out.println("Enter number (1-11) of exercise which you " +
                    "want to check or 'STOP' for exit:");
            input =reader.readLine();
        }

        //ex1
//        System.out.println("Please enter age restriction:");
//        String ageRest = reader.readLine();
//
//        this.bookService.printBookByAgeRestriction(ageRest);

        //ex2
      //  this.bookService.printAllBookByEditionTypeAndCopies();

        //ex3
       // this.bookService.printBooksTitleAndPriceBetween5And40();

        //ex4
//        System.out.println("Please enter the released date(year)");
//        String date = reader.readLine();
//        this.bookService.printBooksTitleWithRealisedDateNotIn(date);

        //ex5
//        System.out.println("Please enter data:");
//        String data = reader.readLine();
//        this.bookService.printBooksByRealisedDateBefore(data);
        //ex6
//        System.out.println("Please enter a string:");
//        this.authorService.findAuthorsByFirstNameEndingWith(reader.readLine());
        //ex7

//        System.out.println("Please enter a string:");
//        this.bookService.printBooksTitleByContainingAGivenString(reader.readLine());

        //Ex8
//        System.out.println("Please enter a string:");
//        this.bookService.printBooksTitleByAuthorLastNameStartingWith(reader.readLine());

        //ex9

//        System.out.println("Enter a minimum length of book title:");
//        this.bookService.printBooksByTitleLengthMoreThan(Integer.parseInt(reader.readLine()));

        //10
      //  this.bookService.printTotalBooksByCopies();

        //11
//        System.out.println("Please enter book title:");
//        this.bookService.printAllBookInfoByTitle(reader.readLine());

    }

    public  void seedData() throws IOException {
        this.categoryServis.seedCategoriesInDB();
        this.authorService.seedAuthorInDB();
        this.bookService.seedDataInDB();

    }

}
