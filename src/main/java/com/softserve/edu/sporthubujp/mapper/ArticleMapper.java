package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ArticleMapper {
    @Mapping(target = "team.id", source = "teamId")
    @Mapping(target = "category.id", source = "categoryId")
    Article dtoToEntity(ArticleDTO articleDTO);

    @Mapping(target = "teamId", source = "team.id")
    @Mapping(target = "categoryId", source = "category.id")
    ArticleDTO entityToDto(Article article);

    @Mapping(target = "createDateTime", ignore = true)
    @Mapping(target = "updateDateTime", ignore = true)
    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "team.id", source = "teamId")
    Article updateArticle(@MappingTarget Article articleFromDb,  ArticleSaveDTO newArticle);
}
