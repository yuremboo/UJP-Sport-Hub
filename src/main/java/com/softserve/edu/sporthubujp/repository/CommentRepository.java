package com.softserve.edu.sporthubujp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.sporthubujp.entity.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findAllByArticleIdOrderByCreateDateTimeDesc(String articleId, Pageable pageable);

    List<Comment> findAllByArticleIdOrderByCreateDateTimeAsc(String articleId, Pageable pageable);

    @Transactional
    @Query(value = "select * from public.comments where article_id = ?1 order by comments.likes + comments.dislikes desc", nativeQuery = true)
    List<Comment> findMostPopularByArticleId(String articleId, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.article.id=?1")
    int countByArticleId(String articleId);

}
