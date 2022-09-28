package com.softserve.edu.sporthubujp.service.impl;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softserve.edu.sporthubujp.dto.comment.CommentSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.entity.comment.Comment;
import com.softserve.edu.sporthubujp.exception.CommentServiceException;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.exception.InvalidEntityException;
import com.softserve.edu.sporthubujp.mapper.CommentMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.repository.CommentRepository;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    public static final String COMMENT_NOT_VALID_WITH = "Comment is not valid with %s";
    public static final String ARTICLE_NOT_FOUND_BY_ID = "Article not found by id: %s";
    private static final String COMMENT_NOT_FOUND_BY_ID = "Comment not found by id: %s";
    public static final String USER_NOT_FOUND_BY_ID = "User not found by id: %s";
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private UserRepository userRepository;
    @Spy
    private CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);
    @InjectMocks
    private CommentServiceImpl underTest;

    @Test
    void canAddNewComment() {
        CommentSaveDTO commentDTO = new CommentSaveDTO();
        Comment comment = new Comment();
        commentDTO.setLikes(0);
        commentDTO.setDislikes(0);

        when(commentMapper.dtoSaveToEntity(any()))
            .thenReturn(comment);
        when(articleRepository.existsById(commentDTO.getArticleId()))
            .thenReturn(true);
        when(userRepository.existsById(commentDTO.getUserId()))
            .thenReturn(true);

        underTest.addNewComment(commentDTO);
        ArgumentCaptor<Comment> commentArgumentCaptor =
            ArgumentCaptor.forClass(Comment.class);
        verify(commentRepository).save(commentArgumentCaptor.capture());
        Comment capturedComment = commentArgumentCaptor.getValue();
        assertThat(capturedComment).isEqualTo(comment);
    }

    @Test
    void cannotAddCommentWithNegativeNumberOfLikesOrDislikes() {
        CommentSaveDTO commentDTO = new CommentSaveDTO();
        commentDTO.setLikes(-1);
        commentDTO.setDislikes(-1);

        when(articleRepository.existsById(commentDTO.getArticleId()))
            .thenReturn(true);
        when(userRepository.existsById(commentDTO.getUserId()))
            .thenReturn(true);
        assertThatThrownBy(() -> underTest.addNewComment(commentDTO))
            .isInstanceOf(InvalidEntityException.class)
            .hasMessageContaining(String.format(COMMENT_NOT_VALID_WITH,
                (commentDTO.getLikes() +
                " likes and " + commentDTO.getDislikes() + " dislikes")));
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    void cannotAddCommentToNotExistingArticle() {
        CommentSaveDTO commentDTO = new CommentSaveDTO();
        assertThatThrownBy(() -> underTest.addNewComment(commentDTO))
            .isInstanceOf(EntityNotExistsException.class)
            .hasMessageContaining(String.format(ARTICLE_NOT_FOUND_BY_ID,
                commentDTO.getArticleId()));
        verify(commentRepository, never()).save(any(Comment.class));
    }
    @Test
    void cannotAddCommentByNotExistingUser() {
        CommentSaveDTO commentDTO = new CommentSaveDTO();
        when(articleRepository.existsById(commentDTO.getArticleId()))
            .thenReturn(true);
        assertThatThrownBy(() -> underTest.addNewComment(commentDTO))
            .isInstanceOf(EntityNotExistsException.class)
            .hasMessageContaining(String.format(USER_NOT_FOUND_BY_ID,
                commentDTO.getUserId()));
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    void canUpdateComment() {
        CommentSaveDTO newComment = new CommentSaveDTO(5, 5, LocalDateTime.of(2022, Month.AUGUST, 5, 14, 44, 34), null, "1a", "1b", "new comment text", true);
        String id = "1";
        Comment commentFromDB = new Comment(id, "text", LocalDateTime.of(2022, Month.AUGUST, 5, 14, 44, 34), 5, 6, new User(), new Article());

        when(commentMapper.entityToDtoSave(any()))
            .thenReturn(newComment);
        when(articleRepository.existsById(newComment.getArticleId()))
            .thenReturn(true);
        when(userRepository.existsById(newComment.getUserId()))
            .thenReturn(true);
        when(commentRepository.findById(any()))
            .thenReturn(Optional.of(commentFromDB));
        doCallRealMethod().when(commentMapper).updateComment(commentFromDB, newComment);

        underTest.updateComment(newComment, id);
        ArgumentCaptor<Comment> commentArgumentCaptor =
            ArgumentCaptor.forClass(Comment.class);
        verify(commentRepository).save(commentArgumentCaptor.capture());
        Comment capturedComment = commentArgumentCaptor.getValue();
        assertThat(capturedComment).isEqualTo(commentFromDB);
    }

    @Test
    void cannotDeleteNotExistingComment() {
        String id = anyString();
        when(commentRepository.existsById(id))
            .thenReturn(false);
        assertThatThrownBy(() -> underTest.deleteComment(id))
            .isInstanceOf(EntityNotExistsException.class)
            .hasMessageContaining(String.format(COMMENT_NOT_FOUND_BY_ID,
                id));
        verify(commentRepository, never()).delete(any(Comment.class));
    }

    @Test
    void canGetNCommentsSortedByMostPopularByArticleId() {
        Comment c1 = new Comment("1", "Comment text", LocalDateTime.of(2022, Month.AUGUST, 5, 14, 44, 34), 5, 5, new User(), new Article());
        Comment c2 = new Comment("2","Comment text1", LocalDateTime.of(2022, Month.AUGUST, 6, 14, 44, 34), 5, 5, new User(), new Article());
        Comment c3 = new Comment("3","Comment text2", LocalDateTime.of(2022, Month.AUGUST, 7, 14, 44, 34), 5, 5, new User(), new Article());
        Comment c4 = new Comment("4","Comment text3", LocalDateTime.of(2022, Month.AUGUST, 8, 14, 44, 34), 5, 5, new User(), new Article());
        Comment c5 = new Comment("5","Comment text4", LocalDateTime.of(2022, Month.AUGUST, 9, 14, 44, 34), 5, 5, new User(), new Article());
        Comment c6 = new Comment("6","Comment text5", LocalDateTime.of(2022, Month.AUGUST, 10, 14, 44, 34), 5, 5, new User(), new Article());
        List<Comment> commentList = List.of(c1, c2, c3, c4, c5, c6);
        when(commentRepository.findMostPopularByArticleId(anyString(), any())).thenReturn(commentList);
        doCallRealMethod().when(commentMapper).entityToDto(any(Comment.class));
        assertThat(underTest.getNSortedCommentsByArticleId(anyString(), anyString(), 6).size()).isEqualTo(commentList.size());
    }

    @Test
    void cannotGetNegativeNumOfComments() {
        assertThatThrownBy(() -> underTest.getNSortedCommentsByArticleId("aa", "oldest", 0))
            .isInstanceOf(CommentServiceException.class)
            .hasMessageContaining("Number of comments should be > 0");
    }
}