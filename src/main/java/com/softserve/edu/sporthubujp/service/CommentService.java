package com.softserve.edu.sporthubujp.service;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.dto.comment.CommentSaveDTO;

public interface CommentService {
    /**
     * Method for getting a given number of comments for the current article
     * that are sorted by the chosen attribute
     *
     * @param articleId represents the current article
     * @param sortingOrder is an order for sorting (oldest, newest
     *                     and by the sum of likes and dislikes otherwise)
     * @param commentsNum is a number of comments that should be returned
     *
     * @return list of {@link CommentDTO}
     */
    List<CommentDTO> getNSortedCommentsByArticleId(String articleId, String sortingOrder, Integer commentsNum);
    int getNumOfCommentsByArticleId(String articleId);
    void deleteComment(String commentId);

    CommentSaveDTO updateComment(CommentSaveDTO newComment, String id);

    CommentSaveDTO addNewComment(CommentSaveDTO newComment);

}
