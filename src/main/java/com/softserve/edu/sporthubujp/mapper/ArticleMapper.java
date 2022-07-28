package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ArticleMapper {
    Article dtoToEntity(ArticleDTO articleDTO);
    ArticleDTO entityToDto(Article article);
}
