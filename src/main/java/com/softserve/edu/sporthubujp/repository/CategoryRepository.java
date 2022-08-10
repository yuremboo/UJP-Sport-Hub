package com.softserve.edu.sporthubujp.repository;

import com.softserve.edu.sporthubujp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
