package com.softserve.edu.sporthubujp.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.sporthubujp.dto.CommentDTO;
import com.softserve.edu.sporthubujp.entity.Comment;
import com.softserve.edu.sporthubujp.mapper.CommentMapper;
import com.softserve.edu.sporthubujp.repository.CommentRepository;
import com.softserve.edu.sporthubujp.service.CommentService;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl  implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDTO> getAllCommentByArticleId(String articleId) {
        List<Comment> comments = new LinkedList<Comment>();
        comments = commentRepository.findAllByArticleId(articleId);
        log.info("Get all comments by article id {} in service", articleId);
        List<CommentDTO> commentsDTOS = new LinkedList<>();
        for (var comment : comments) {
            commentsDTOS.add(commentMapper.entityToDto(comment));
        }
        return commentsDTOS;
    }
}
