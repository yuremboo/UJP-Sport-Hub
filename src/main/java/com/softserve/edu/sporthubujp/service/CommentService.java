package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getAllCommentByArticleId(String articleId);
    int getNumOfCommentsByArticleId(String articleId);
}
