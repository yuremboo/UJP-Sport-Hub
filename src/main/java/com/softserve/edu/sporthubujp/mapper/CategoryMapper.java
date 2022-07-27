package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.CategoryDTO;
import com.softserve.edu.sporthubujp.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category dtoToEntity(CategoryDTO categoryDTO);
    CategoryDTO entityToDto(Category category);
}
