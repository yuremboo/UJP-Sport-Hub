package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ArticleListMapper {
    Article dtoToEntity(ArticleListDTO articleDTO);
    ArticleListDTO entityToDto(Article article);
}
