package com.softserve.edu.sporthubujp;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.entity.Category;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MapperTest {


    @Test
    void shouldMapArticleToDto() {
        //given
        Article article = new Article("123", "Some title", "Some text", true,
                false, LocalDateTime.of(2022, 7, 24, 14, 22),
                null, new Category("1", "NBA", "Basketball",
                true, LocalDateTime.of(2022, 7, 24, 12, 34),
                null, null, null,
                null, null, null), null);

        //when
        ArticleDTO articleDTO =
                Mappers.getMapper(ArticleMapper.class).entityToDto(article);

        //then
        Assertions.assertEquals(article.getCategory(),
                articleDTO.getCategory());
        Assertions.assertEquals(article.getCommentsActive(),
                articleDTO.getCommentsActive());
        Assertions.assertEquals(article.getComments(),
                articleDTO.getComments());
        Assertions.assertEquals(article.getId(),
                articleDTO.getId());
        Assertions.assertEquals(article.getText(),
                articleDTO.getText());
        Assertions.assertEquals(article.getTitle(),
                articleDTO.getTitle());
        Assertions.assertEquals(article.getIsActive(),
                articleDTO.getIsActive());
        Assertions.assertEquals(article.getCreateDateTime(),
                articleDTO.getCreateDateTime());
        Assertions.assertEquals(article.getUpdateDateTime(),
                articleDTO.getUpdateDateTime());
    }
}


