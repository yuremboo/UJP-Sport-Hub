package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    private static final String ARTICLE_NOT_FOUND_BY_ID = "Article not found by id: %s";
    private static final String ARTICLE_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";

    @Mock
    private ArticleRepository articleRepository;
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
//    @Test
//    void should_find_and_return_one_student() {
//        // Arrange
//        final var expectedStudent = ArticleDTO.builder().name("Jimmy Olsen").age(28).build();
//        when(articleRepository.findById(anyString())).thenReturn(Optional.of(expectedStudent));
//
//        // Act
//        final var actual = underTest.getArticleById(anyString());
//
//        // Assert
//        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedStudent);
//        verify(articleRepository, times(1)).findById(anyString());
//        verifyNoMoreInteractions(articleRepository);
//    }
//    @Test
//    void should_delete_one_student() {
//        // Arrange
//        doNothing().when(articleRepository).deleteById(anyString());
//
//        // Act & Assert
//        underTest.deleteArticleById("4");
//        verify(articleRepository, times(1)).deleteById(anyString());
//        verifyNoMoreInteractions(articleRepository);
//    }
    @Test
    void willThrowTokenNotFoundException() {
        List<ArticleListDTO> articleListDTOS = spy(new ArrayList<>());
        List<Article> articleList = spy(new ArrayList<>());
        //        when(articleRepository.deleteById(anyString()))
        //            .thenReturn(true);
        assertThatThrownBy(() -> underTest.deleteArticleById(anyString()))
            .isInstanceOf(EntityNotExistsException.class);

        verify(articleList, never()).stream();
    }
}