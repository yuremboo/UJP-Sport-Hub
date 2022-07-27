package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
    Article dtoToEntity(ArticleDTO articleDTO);
    ArticleDTO entityToDto(Article article);
}
