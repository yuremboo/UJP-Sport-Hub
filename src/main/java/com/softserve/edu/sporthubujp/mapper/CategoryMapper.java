package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.CategoryDTO;
import com.softserve.edu.sporthubujp.entity.Category;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CategoryMapper {
    Category dtoToEntity(CategoryDTO categoryDTO);
    CategoryDTO entityToDto(Category category);
}
