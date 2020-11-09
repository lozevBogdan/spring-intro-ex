package com.softuni.springintroex.Serveses;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Category;
import com.softuni.springintroex.repositories.CategoryRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServesImpl implements CategoryServis {

    private final FileUtil fileUtil;
    private  final CategoryRepository categoryRepo;

    @Autowired
    public CategoryServesImpl(FileUtil fileUtil, CategoryRepository categoryRepo) {
        this.fileUtil = fileUtil;
        this.categoryRepo = categoryRepo;
    }


    @Override
    public void seedCategoriesInDB() throws IOException {

        String  [] lines = fileUtil
                .readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);

        for (String line : lines) {
            Category category = new Category(line);
            this.categoryRepo.saveAndFlush(category);
        }



    }
}
