package com.softserve.edu.sporthubujp.service;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.dto.comment.CommentSaveDTO;

public interface CommentService {
    List<CommentDTO> getAllCommentByArticleId(String articleId);
    int getNumOfCommentsByArticleId(String articleId);
    void deleteComment(String commentId);

    CommentDTO updateComment(CommentSaveDTO newComment, String id);

    CommentSaveDTO addNewComment(CommentSaveDTO newComment);

}
