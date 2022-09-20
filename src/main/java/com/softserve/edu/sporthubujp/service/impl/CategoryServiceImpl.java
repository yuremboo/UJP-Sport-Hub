package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.CategoryDTO;
import com.softserve.edu.sporthubujp.entity.Category;
import com.softserve.edu.sporthubujp.mapper.CategoryMapper;
import com.softserve.edu.sporthubujp.repository.CategoryRepository;
import com.softserve.edu.sporthubujp.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = new LinkedList<>();
        categories = categoryRepository.findAll();
        log.info("Get all categories in service");
        List<CategoryDTO> categoryDTOS = new LinkedList<>();
        for (var category : categories) {
            categoryDTOS.add(categoryMapper.entityToDto(category));
        }
        return categoryDTOS;
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        Category category = categoryRepository.getReferenceById(id);
        return categoryMapper.entityToDto(category);
    }
}
