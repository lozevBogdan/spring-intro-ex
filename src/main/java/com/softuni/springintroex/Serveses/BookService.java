package com.softuni.springintroex.Serveses;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.EditionType;

import java.io.IOException;
import java.time.LocalDate;

public interface BookService {

    void seedDataInDB() throws IOException;

    void printBookByAgeRestriction(String ageRestriction);
    void printAllBookByEditionTypeAndCopies();
    void printBooksTitleAndPriceBetween5And40();
    void printBooksTitleWithRealisedDateNotIn(String localDate);
    void printBooksByRealisedDateBefore(String date);
    void printBooksTitleByContainingAGivenString(String letter);
    void printBooksTitleByAuthorLastNameStartingWith(String letter);
    void printBooksByTitleLengthMoreThan(int length);
    void printTotalBooksByCopies();
    void printAllBookInfoByTitle(String title);


}
