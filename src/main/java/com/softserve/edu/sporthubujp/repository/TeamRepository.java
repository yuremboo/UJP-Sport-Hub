package com.softserve.edu.sporthubujp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.sporthubujp.entity.Team;

public interface TeamRepository  extends JpaRepository<Team, String> {
    List<Team> findAllByArticleId(String articleId);

    @Transactional
    @Query("SELECT t FROM Team t "
        + "JOIN t.subscriptions s "
        + "JOIN s.user u "
        + "WHERE u.id = ?1 ")
        //+ "ORDER BY a.createDateTime ")
    List<Team> getAllTeamsBySubscription(String idUser);


//    @Transactional
//    @Modifying
//    @Query("DELETE s FROM Subscription "
//        + "JOIN s.team t "
//        + "JOIN s.user u "
//        + "WHERE u.id = ?1 AND t.id = ?2 ")
//    void deleteTeamsBySubscription(String idUser, String teamI);
}