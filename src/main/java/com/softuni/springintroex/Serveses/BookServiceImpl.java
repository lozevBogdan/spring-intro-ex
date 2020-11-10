package com.softuni.springintroex.Serveses;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.*;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.repositories.CategoryRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.LocalDate.parse;

@Service
public class BookServiceImpl implements BookService {

    private final FileUtil fileUtil;
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepo;

    @Autowired
    public BookServiceImpl(FileUtil fileUtil, BookRepository bookRepo, AuthorRepository authorRepository, CategoryRepository categoryRepo) {
        this.fileUtil = fileUtil;
        this.bookRepo = bookRepo;
        this.authorRepository = authorRepository;
        this.categoryRepo = categoryRepo;
    }

    @Override
    @Transactional
    public void seedDataInDB() throws IOException {

        String [] lines =
        this.fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);

        for (String line : lines) {
            String [] data = line.split("\\s+");
            Random random = new Random();



            int authorIndex = random.nextInt((int)this.authorRepository.count())+1;
            Author author = this.authorRepository.findById((long) authorIndex).get();
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = parse(data[1], formatter);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }

            String title = titleBuilder.toString().trim();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(localDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(this.getRandomCategories());

            this.bookRepo.saveAndFlush(book);

        }
    }

    @Override
    public void printBookByAgeRestriction(String ageRestriction) {

        AgeRestriction ageRestriction1 =
                AgeRestriction.valueOf(ageRestriction.toUpperCase());
        this.bookRepo.findAllByAgeRestriction(ageRestriction1)
        .forEach(b-> System.out.println(b.getTitle()));


    }

    @Override
    public void printAllBookByEditionTypeAndCopies() {

        this.bookRepo
                .findAllByEditionTypeAndCopiesLessThan
                        (EditionType.GOLD,5000)
                .forEach(b-> System.out.println(b.getTitle()));


    }

    @Override
    public void printBooksTitleAndPriceBetween5And40() {

        this.bookRepo.findBookByPriceNotBetween5And40()
                .forEach(b->
                        System.out.printf("%s - $%s%n",b.getTitle(),b.getPrice()));

    }

    @Override
    public void printBooksTitleWithRealisedDateNotIn(String localDate) {

        this.bookRepo.findAllByReleaseDateNot(localDate)
        .forEach(b-> System.out.println(b.getTitle()));


    }

    @Override
    public void printBooksByRealisedDateBefore(String date) {

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd-MM-yyyy");

        LocalDate localDate =  parse(date,formatter);

        this.bookRepo.findBookByReleaseDateBefore(localDate)
        .forEach(b-> System.out.printf("%s %s %s%n"
        ,b.getTitle(),b.getEditionType(),b.getPrice()));


    }

    @Override
    public void printBooksTitleByContainingAGivenString(String letter) {

        this.bookRepo.findBookByTitleContaining(letter)
        .forEach(b-> System.out.println(b.getTitle()));


    }

    @Override
    public void printBooksTitleByAuthorLastNameStartingWith(String letter) {

        this.bookRepo.findBookByAuthorLastNameStartingWith(letter)
                .forEach(b-> System.out.printf("%s (%s)%n",b.getTitle()
                        ,b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName()));

    }

    @Override
    public void printBooksByTitleLengthMoreThan(int length) {

        System.out.println(this.bookRepo.findBookByTitleLengthMoreThan(length));

    }

    @Override
    public void printTotalBooksByCopies() {

        Map<String,Integer> collection = new HashMap<>();

        List<Book> bookList = this.bookRepo.findAll();

        bookList
                .stream()
                .forEach(b->{
                    String name = b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName();
                  int copies = b.getCopies();

                  collection.putIfAbsent(name,0);
                  collection.put(name,collection.get(name) + copies);

                });

        collection.entrySet()
                .stream()
                .sorted((a,b)->Integer.compare(b.getValue(),a.getValue()))
                .forEach(e-> System.out.println(e.getKey() + " - " + e.getValue()));

    }

    @Override
    public void printAllBookInfoByTitle(String title) {

        Book book = this.bookRepo.findBookByTitle(title.trim());

        BookInfo bookInfo = new BookInfo(book.getTitle(),book.getEditionType()
        ,book.getAgeRestriction(),book.getPrice());

        System.out.println(bookInfo);

    }

    Set<Category> getRandomCategories(){
        Set<Category> categories = new HashSet<>();

        Random random = new Random();

        for (int i = 0; i < 4; i++) {

            long categoryIndex = random.nextInt((int)this.categoryRepo.count())+1;
            Category category = this.categoryRepo.findById( categoryIndex).get();

            categories.add(category);
        }


        return categories;

    }
}
