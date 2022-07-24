package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import org.mapstruct.Mapper;

@Mapper
public interface ArticleMapper {
    Article dtoToEntity(ArticleDTO articleDTO);

    ArticleDTO entityToDto(Article article);
}
