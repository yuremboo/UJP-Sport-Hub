package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.CategoryDTO;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
public interface CategoryService {
    List<CategoryDTO> getAllCategories();
}
