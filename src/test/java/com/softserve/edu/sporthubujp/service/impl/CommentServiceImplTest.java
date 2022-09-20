package com.softserve.edu.sporthubujp.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softserve.edu.sporthubujp.dto.comment.CommentSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.entity.comment.Comment;
import com.softserve.edu.sporthubujp.mapper.CommentMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.repository.CommentRepository;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.service.CommentService;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentMapper commentMapper;
    @InjectMocks
    private CommentServiceImpl underTest;

    @Test
    @Disabled
    void deleteComment() {
    }

    @Test
    @Disabled
    void updateComment() {
    }

/*    @Test
    void canAddNewComment() {
        CommentSaveDTO commentDTO = spy(new CommentSaveDTO());
        Comment comment = spy(new Comment());

        given(commentRepository.save(comment));
        when(commentMapper.dtoSaveToEntity(commentDTO))
            .thenReturn(comment);


        when(articleRepository.existsById(commentDTO.getArticleId())).thenReturn(true);
        when(userRepository.existsById(commentDTO.getUserId())).thenReturn(true);

        underTest.addNewComment(commentDTO);

        ArgumentCaptor<Comment> commentArgumentCaptor =
            ArgumentCaptor.forClass(Comment.class);

        verify(commentRepository)
            .save(commentArgumentCaptor.capture());

        Comment capturedComment = commentArgumentCaptor.getValue();

        assertThat(capturedComment).isEqualTo(comment);
    }*/

    @Test
    void canAddNewComment() {
        CommentSaveDTO commentDTO = spy(new CommentSaveDTO());
        Comment comment = spy(new Comment());
    }
}