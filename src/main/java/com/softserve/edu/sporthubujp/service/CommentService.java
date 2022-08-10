package com.softserve.edu.sporthubujp.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.softserve.edu.sporthubujp.dto.CommentDTO;

public interface CommentService {
    List<CommentDTO> getAllCommentByArticleId(String articleId);
}
