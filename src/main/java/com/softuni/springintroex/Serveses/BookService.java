package com.softuni.springintroex.Serveses;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.EditionType;

import java.io.IOException;

public interface BookService {

    void seedDataInDB() throws IOException;

    void printBookByAgeRestriction(String ageRestriction);
    void printAllBookByEditionTypeAndCopies();

}
