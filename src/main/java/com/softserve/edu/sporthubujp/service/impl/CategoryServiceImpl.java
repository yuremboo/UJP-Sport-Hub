package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.CategoryDTO;
import com.softserve.edu.sporthubujp.entity.Category;
import com.softserve.edu.sporthubujp.mapper.CategoryMapper;
import com.softserve.edu.sporthubujp.repository.CategoryRepository;
import com.softserve.edu.sporthubujp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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

        List<CategoryDTO> categoryDTOS = new LinkedList<>();
        for (var category : categories) {
            categoryDTOS.add(categoryMapper.entityToDto(category));
        }
        return categoryDTOS;
    }
}
