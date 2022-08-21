package com.softserve.edu.sporthubujp.service;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.entity.comment.Comment;

public interface CommentService {
    List<CommentDTO> getAllCommentByArticleId(String articleId);

    void deleteComment(String commentId);

    Comment updateComment(Comment newComment, String id);

    Comment addNewComment(Comment newComment);

}
