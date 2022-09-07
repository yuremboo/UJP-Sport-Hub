package com.softserve.edu.sporthubujp.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.dto.comment.CommentSaveDTO;
import com.softserve.edu.sporthubujp.entity.comment.Comment;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.mapper.CommentMapper;
import com.softserve.edu.sporthubujp.repository.ArticleRepository;
import com.softserve.edu.sporthubujp.repository.CommentRepository;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.service.CommentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private static final String COMMENT_NOT_FOUND_BY_ID = "Comment not found by id: %s";
    public static final String ARTICLE_NOT_FOUND_BY_ID = "Article not found by id: %s";
    public static final String USER_NOT_FOUND_BY_ID = "User not found by id: %s";
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper,
        ArticleRepository articleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CommentDTO> getAllCommentsByArticleId(String articleId) {
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        log.info("Get all comments by article id {} in service", articleId);
        List<CommentDTO> commentsDTOS = new LinkedList<>();
        for (var comment : comments) {
            commentsDTOS.add(commentMapper.entityToDto(comment));
        }
        return commentsDTOS;
    }

    @Override
    public int getNumOfCommentsByArticleId(String articleId) {
        List<CommentDTO> commentDTOS = getAllCommentsByArticleId(articleId);
        return commentDTOS.size();
    }

    @Override
    public void deleteComment(String id) {
        log.info("Delete comment by id in service");
        if (!commentRepository.existsById(id)) {
            log.error(String.format(COMMENT_NOT_FOUND_BY_ID, id));
            throw new EntityNotExistsException(String.format(COMMENT_NOT_FOUND_BY_ID, id));
        }
        commentRepository.deleteById(id);
    }

    @Override
    public CommentSaveDTO updateComment(CommentSaveDTO newComment, String id) {
        if (!articleRepository.existsById(newComment.getArticleId())) {
            throw new EntityNotExistsException(String.format(ARTICLE_NOT_FOUND_BY_ID, newComment.getArticleId()));
        } else if (!articleRepository.existsById(newComment.getUserId())) {
            throw new EntityNotExistsException(String.format(USER_NOT_FOUND_BY_ID, newComment.getUserId()));
        }
        return commentRepository.findById(id)
            .map(comment -> {
                commentMapper.updateComment(comment, newComment);
                return commentMapper.entityToDtoSave(commentRepository.save(comment));
            })
            .orElseThrow(EntityNotExistsException::new);
    }

    @Override
    public CommentSaveDTO addNewComment(CommentSaveDTO newComment) {
        if (!articleRepository.existsById(newComment.getArticleId())) {
            throw new EntityNotExistsException(String.format(ARTICLE_NOT_FOUND_BY_ID, newComment.getArticleId()));
        } else if (!userRepository.existsById(newComment.getUserId())) {
            throw new EntityNotExistsException(String.format(USER_NOT_FOUND_BY_ID, newComment.getUserId()));
        }
        return commentMapper.entityToDtoSave(
            commentRepository.save(commentMapper.dtoSaveToEntity(newComment)));
    }
}
