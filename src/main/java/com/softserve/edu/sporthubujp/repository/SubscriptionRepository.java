package com.softserve.edu.sporthubujp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.sporthubujp.entity.Subscription;

public interface SubscriptionRepository  extends JpaRepository<Subscription, String> {
    @Transactional
    @Query("SELECT s FROM Subscription s "
        + "JOIN s.team t "
        + "JOIN s.user u "
        + "WHERE u.id = ?1 AND t.id = ?2 ")
    Subscription getAllSubscriptionByTeams(String idUser, String teamId);
}