package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.entity.Category;
import com.softserve.edu.sporthubujp.entity.Team;
import com.softserve.edu.sporthubujp.entity.comment.Comment;
import com.softserve.edu.sporthubujp.exception.CategoryNotFoundException;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.exception.TeamNotFoundException;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.repository.CategoryRepository;
import com.softserve.edu.sporthubujp.repository.LogsRepository;
import com.softserve.edu.sporthubujp.repository.TeamRepository;
import com.softserve.edu.sporthubujp.service.CommentService;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    private static final String ARTICLE_NOT_FOUND_BY_ID = "Article not found by id: %s";
    private static final String ARTICLE_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private ArticleMapper articleMapper;
    @InjectMocks
    private ArticleServiceImpl underTest;

    @Test
    void getNewestArticlesByCategoryId() {
        List<ArticleListDTO> articleListDTOS = spy(new ArrayList<>());
        List<Article> articleList = spy(new ArrayList<>());

        when(articleRepository.findNewestArticlesByCategoryId(anyString(), any(PageRequest.class)))
            .thenReturn(Optional.of(articleList));

        List<ArticleListDTO> underTestArticles =
            underTest.getNewestArticlesByCategoryId(anyString(), any(PageRequest.class));

        verify(articleList).stream();

        assertThat(underTestArticles).isEqualTo(articleListDTOS);
    }

    @Test
    void willThrowWhenGetNewestArticles() {

        List<Article> articleList = spy(new ArrayList<>());

        when(articleRepository.findNewestArticlesByCategoryId(anyString(), any(PageRequest.class)))
            .thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getNewestArticlesByCategoryId(anyString(), any(PageRequest.class)))
            .isInstanceOf(EntityNotFoundException.class);

        verify(articleList, never()).stream();
    }

    @Test
    void canUpdateArticle() {
        ArticleDTO articleDTO = spy(new ArticleDTO());
        ArticleSaveDTO articleSaveDTO = spy(new ArticleSaveDTO());
        Article article = spy(new Article());
        Category category = spy(new Category());
        Team team = spy(new Team());

        given(articleRepository.findById(article.getId()))
            .willReturn(Optional.of(article));

        given(categoryRepository.findById(article.getId()))
            .willReturn(Optional.of(category));

        given(teamRepository.findById(article.getId()))
            .willReturn(Optional.of(team));

        when(articleMapper.entityToDto(any()))
            .thenReturn(articleDTO);

        ArticleDTO articleSaveDTOUnderTest = underTest.updateArticle(articleSaveDTO, article.getId());
        verify(articleRepository).save(article);
        verify(article).setCategory(category);
        verify(article).setTeam(team);

        Assertions.assertThat(articleSaveDTOUnderTest).isEqualTo(articleDTO);
    }

    @Test
    void willThrowWhenUpdateArticle() {
        ArticleSaveDTO articleSaveDTO = spy(new ArticleSaveDTO());
        Article article = spy(new Article());

        given(articleRepository.findById(article.getId()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.updateArticle(articleSaveDTO, article.getId()))
            .isInstanceOf(EntityNotExistsException.class)
            .hasMessage("Unable to find entity.");
        verify(articleMapper, never()).entityToDto(any(Article.class));
    }

    @Test
    void willThrowCategoryWhenUpdateArticle() {
        Article article = spy(new Article());
        ArticleSaveDTO articleSaveDTO = spy(new ArticleSaveDTO());

        given(articleRepository.findById(article.getId()))
            .willReturn(Optional.of(article));

        given(categoryRepository.findById(article.getId()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.updateArticle(articleSaveDTO, article.getId()))
            .isInstanceOf(CategoryNotFoundException.class)
            .hasMessage("Unable to find category.");
        verify(articleMapper, never()).entityToDto(any(Article.class));
    }

    @Test
    void willThrowTeamWhenUpdateArticle() {
        Article article = spy(new Article());
        ArticleSaveDTO articleSaveDTO = spy(new ArticleSaveDTO());
        Category category = spy(new Category());

        given(articleRepository.findById(article.getId()))
            .willReturn(Optional.of(article));

        given(categoryRepository.findById(article.getId()))
            .willReturn(Optional.of(category));

        given(teamRepository.findById(article.getId()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.updateArticle(articleSaveDTO, article.getId()))
            .isInstanceOf(TeamNotFoundException.class)
            .hasMessage("Unable to find team.");
        verify(articleMapper, never()).entityToDto(any(Article.class));
    }

    @Test
    void canPublishUnpublishedArticle() {
        ArticleDTO articleDTO = spy(new ArticleDTO());
        Article article = spy(new Article());

        given(articleRepository.findById(article.getId()))
            .willReturn(Optional.of(article));

        when(articleMapper.entityToDto(any()))
            .thenReturn(articleDTO);

        when(article.getIsActive())
            .thenReturn(false);

        underTest.publishUnpublishedArticle(article.getId());
        verify(article).setIsActive(anyBoolean());
        verify(articleRepository).save(article);
    }

    @Test
    void willThrowWhenPublishUnpublishedArticle() {
        given(articleRepository.findById(anyString()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.publishUnpublishedArticle(anyString()))
            .isInstanceOf(EntityNotExistsException.class)
            .hasMessage("Unable to find entity.");
        verify(articleMapper, never()).entityToDto(any(Article.class));
    }

    @Test
    void canPostArticle() {
        ArticleSaveDTO articleSaveDTO = spy(new ArticleSaveDTO());
        Article article = spy(new Article());
        Category category = spy(new Category());
        Team team = spy(new Team());

        when(articleMapper.saveDtoToEntity(any()))
            .thenReturn(article);

        given(categoryRepository.findById(article.getId()))
            .willReturn(Optional.of(category));

        given(teamRepository.findById(article.getId()))
            .willReturn(Optional.of(team));

        when(articleMapper.entityToSaveDto(any()))
            .thenReturn(articleSaveDTO);

        ArticleSaveDTO articleSaveDTOUnderTest = underTest.postArticle(articleSaveDTO);
        verify(articleRepository).save(article);
        verify(article).setCategory(category);
        verify(article).setTeam(team);

        Assertions.assertThat(articleSaveDTOUnderTest).isEqualTo(articleSaveDTO);
    }

    @Test
    void willThrowCategoryWhenPostArticle() {
        ArticleSaveDTO articleSaveDTO = spy(new ArticleSaveDTO());
        Article article = spy(new Article());
        when(articleMapper.saveDtoToEntity(any()))
            .thenReturn(article);
        given(categoryRepository.findById(article.getId()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.postArticle(articleSaveDTO))
            .isInstanceOf(CategoryNotFoundException.class)
            .hasMessage("Unable to find category.");
        verify(articleMapper, never()).entityToDto(any(Article.class));
    }

    @Test
    void willThrowTeamWhenPostArticle() {
        ArticleSaveDTO articleSaveDTO = spy(new ArticleSaveDTO());
        Article article = spy(new Article());
        Category category = spy(new Category());

        when(articleMapper.saveDtoToEntity(any()))
            .thenReturn(article);
        given(categoryRepository.findById(article.getId()))
            .willReturn(Optional.of(category));
        given(teamRepository.findById(article.getId()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.postArticle(articleSaveDTO))
            .isInstanceOf(TeamNotFoundException.class)
            .hasMessage("Unable to find team.");
        verify(articleMapper, never()).entityToDto(any(Article.class));
    }

    @Test
    void cannotDeleteNotExistingArticle() {
        String id = anyString();
        when(articleRepository.existsById(id))
            .thenReturn(false);
        AssertionsForClassTypes.assertThatThrownBy(() -> underTest.deleteArticleById(id))
            .isInstanceOf(EntityNotExistsException.class)
            .hasMessageContaining(String.format(ARTICLE_NOT_DELETE_BY_ID,
                id));
        verify(articleRepository, never()).delete(any(Article.class));
    }
    @Test
    void getAllArticlesBySubscription() {
        List<ArticleDTO> articleDTOS = spy(new ArrayList<>());
        List<Article> articleList = spy(new ArrayList<>());

        when(articleRepository.getAllArticlesBySubscription(anyString()))
            .thenReturn(articleList);

        List<ArticleDTO> underTestArticles =
            underTest.getAllArticlesBySubscription(anyString());


        assertThat(underTestArticles).isEqualTo(articleDTOS);
    }
}