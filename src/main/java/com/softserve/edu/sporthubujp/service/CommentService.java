package com.softserve.edu.sporthubujp.service;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.entity.comment.Comment;

public interface CommentService {
    List<CommentDTO> getAllCommentsByArticleId(String articleId);

    void deleteComment(String commentId);

    CommentDTO updateComment(CommentDTO newComment, String id);

    CommentDTO addNewComment(CommentDTO newComment);

}
