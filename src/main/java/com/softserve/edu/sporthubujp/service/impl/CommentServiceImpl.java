package com.softserve.edu.sporthubujp.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.dto.comment.CommentSaveDTO;
import com.softserve.edu.sporthubujp.entity.comment.Comment;
import com.softserve.edu.sporthubujp.exception.ArticleServiceException;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.mapper.CommentMapper;
import com.softserve.edu.sporthubujp.repository.CommentRepository;
import com.softserve.edu.sporthubujp.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private static final String COMMENT_NOT_FOUND_BY_ID = "Comment not found by id: %s";
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDTO> getAllCommentsByArticleId(String articleId) {
        List<Comment> comments = new LinkedList<Comment>();
        comments = commentRepository.findAllByArticleId(articleId);
        log.info("Get all comments by article id {} in service", articleId);
        List<CommentDTO> commentsDTOS = new LinkedList<>();
        for (var comment : comments) {
            commentsDTOS.add(commentMapper.entityToDto(comment));
        }
        return commentsDTOS;
    }

    @Override public void deleteComment(String id) {
        log.info("Delete comment by id in service");
        if (!commentRepository.existsById(id)) {
            log.error(String.format(COMMENT_NOT_FOUND_BY_ID, id));
            throw new ArticleServiceException(String.format(COMMENT_NOT_FOUND_BY_ID, id));
        }
        commentRepository.deleteById(id);
    }

    @Override public CommentDTO updateComment(CommentSaveDTO newComment, String id) {
        return commentRepository.findById(id)
            .map(comment -> {
                commentMapper.updateComment(comment, newComment);
                return commentMapper.entityToDto(commentRepository.save(comment));
            })
            .orElseThrow(EntityNotExistsException::new);
    }

    @Override public CommentSaveDTO addNewComment(CommentSaveDTO newComment) {
        return commentMapper.entityToDtoSave(commentRepository.save(commentMapper.dtoSaveToEntity(newComment)));
    }
}
