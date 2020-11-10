package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

 Set<Book>findAllByAgeRestriction(AgeRestriction ageRestriction);
 Set<Book> findAllByEditionTypeAndCopiesLessThan
         (EditionType editionType,int copies);

 @Query("select b from Book b where  b.price not between 5 and 40")
 Set<Book> findBookByPriceNotBetween5And40();

 @Query("select b from Book b where substring(b.releaseDate,1,4)  not in :realisedDate")
 Set<Book> findAllByReleaseDateNot(String realisedDate);

 Set<Book> findBookByReleaseDateBefore(LocalDate date);

 Set<Book> findBookByTitleContaining(String word);

 Set<Book> findBookByAuthorLastNameStartingWith(String word);

 @Query("select count (b) from Book b where length(b.title) > :length ")
 int findBookByTitleLengthMoreThan(int length);

 Book findBookByTitle(String title);



}
