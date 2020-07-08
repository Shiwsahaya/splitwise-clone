package split.wise.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import split.wise.web.model.Category;
import split.wise.web.repository.CategoryRepository;
import split.wise.web.service.CategoryService;

import javax.transaction.Transactional;


@Service
@Transactional
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category){
        return categoryRepository.save(category);
    }

}
