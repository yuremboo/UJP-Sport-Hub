package com.softserve.edu.sporthubujp.mapperimpl;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import org.hibernate.cfg.NotYetImplementedException;

public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public Article dtoToEntity(ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        }
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setTitle(articleDTO.getTitle());
        article.setText(articleDTO.getText());
        article.setIsActive(articleDTO.getIsActive());
        article.setCommentsActive(articleDTO.getCommentsActive());
        article.setCreateDateTime(articleDTO.getCreateDateTime());
        article.setUpdateDateTime(articleDTO.getUpdateDateTime());
        article.setCategory(articleDTO.getCategory());
        article.setComments(articleDTO.getComments());
        return article;
    }

    @Override
    public ArticleDTO entityToDto(Article article) {
        if (article == null) {
            return null;
        }
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setText(article.getText());
        articleDTO.setIsActive(article.getIsActive());
        articleDTO.setCommentsActive(article.getCommentsActive());
        articleDTO.setCreateDateTime(article.getCreateDateTime());
        articleDTO.setUpdateDateTime(article.getUpdateDateTime());
        articleDTO.setCategory(article.getCategory());
        articleDTO.setComments(article.getComments());
        return articleDTO;
    }
}
