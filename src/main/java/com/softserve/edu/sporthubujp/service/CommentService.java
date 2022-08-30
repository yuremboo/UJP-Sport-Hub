package com.softserve.edu.sporthubujp.service;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.dto.comment.CommentSaveDTO;
import com.softserve.edu.sporthubujp.entity.comment.Comment;

public interface CommentService {
    List<CommentDTO> getAllCommentsByArticleId(String articleId);

    void deleteComment(String commentId);

    CommentDTO updateComment(CommentSaveDTO newComment, String id);

    CommentSaveDTO addNewComment(CommentSaveDTO newComment);

}
