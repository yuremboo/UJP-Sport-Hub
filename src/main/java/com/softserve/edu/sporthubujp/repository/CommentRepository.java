package com.softserve.edu.sporthubujp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import com.softserve.edu.sporthubujp.entity.Comment;

public interface CommentRepository  extends JpaRepository<Comment, String> {
    List<Comment> findAllByArticleId(String articleId);
}
