package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.mapper.ArticleMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.repository.LogsRepository;
import com.softserve.edu.sporthubujp.service.CommentService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArticleServiceImplTest {

    private static final String ARTICLE_NOT_FOUND_BY_ID = "Article not found by id: %s";
    private static final String ARTICLE_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleMapper articleMapper;
    @Mock
    private LogsRepository logRepository;
    @Mock
    private CommentService commentService;
    @InjectMocks
    private ArticleServiceImpl underTest;

    @Test
    @Disabled
    void getNewestArticlesByCategoryId() {

//        List<ArticleListDTO> articleListDTOS = spy(new ArrayList<>());
//
//        underTest.getNewestArticlesByCategoryId(anyString(), any(PageRequest.class));
//
//        when(articleRepository.findNewestArticlesByCategoryId(anyString(), any(PageRequest.class)))
//                .thenReturn(Optional.of(articleMapper.dtoToEntity(articleListDTOS)));
//
//        verify(confirmationTokenRepository).findByToken(confirmationToken.getToken());
//
//        ConfirmationToken confirmationTokenTest = confirmationTokenRepository.findByToken(confirmationToken.getToken())
//                .orElseThrow(EntityNotFoundException::new);
//
//        assertThat(confirmationTokenTest).isEqualTo(confirmationToken);
    }
}