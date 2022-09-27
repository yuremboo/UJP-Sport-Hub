package com.softserve.edu.sporthubujp.repository;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.softserve.edu.sporthubujp.entity.Logs;

public interface LogsRepository  extends JpaRepository<Logs, String> {
    @Query("SELECT a.articleId FROM Logs a "
        + "GROUP BY a.articleId "
        + "ORDER BY COUNT(*) DESC ")
    List<String> getMorePopularArticlesId(Pageable pageable);
}