package com.softuni.springintroex.Serveses;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthorServesImpl implements AuthorService {

    private final FileUtil fileUtil;
    private final AuthorRepository authorRepo;

    @Autowired
    public AuthorServesImpl(FileUtil fileUtil, AuthorRepository authorRepo) {
        this.fileUtil = fileUtil;
        this.authorRepo = authorRepo;
    }

    @Override
    public void seedAuthorInDB() throws IOException {

        String[] lines = fileUtil
                .readFileContent(GlobalConstants.AUTHORS_FILE_PATH);

        for (String line : lines) {
            String [] tokens = line.split("\\s+");
            Author author = new Author(tokens[0],tokens[1]);
            this.authorRepo.saveAndFlush(author);

        }

    }
}
