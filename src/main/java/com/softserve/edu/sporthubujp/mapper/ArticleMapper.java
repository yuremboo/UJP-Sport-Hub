package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ArticleMapper {

    Article dtoToEntity(ArticleDTO articleDTO);

    ArticleDTO entityToDto(Article article);

    ArticleListDTO entityToListDTO(Article article);

    @Mapping(target = "createDateTime", ignore = true)
    @Mapping(target = "updateDateTime", ignore = true)
    @Mapping(target = "category.id", ignore = true)
    @Mapping(target = "team.id", ignore = true)
    Article updateArticle(@MappingTarget Article articleFromDb,  ArticleSaveDTO newArticle);

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "team.id", source = "teamId")
    Article saveDtoToEntity(ArticleSaveDTO articleDTO);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "teamId", source = "team.id")
    ArticleSaveDTO entityToSaveDto(Article article);
}
