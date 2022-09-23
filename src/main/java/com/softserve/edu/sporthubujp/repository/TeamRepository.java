package com.softserve.edu.sporthubujp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.sporthubujp.entity.Team;

public interface TeamRepository extends JpaRepository<Team, String> {
    @Transactional
    @Query("SELECT t FROM Team t "
            + "JOIN t.subscriptions s "
            + "JOIN s.user u "
            + "WHERE u.id = ?1 ")
    List<Team> getAllTeamsBySubscription(String idUser);

    @Transactional
    @Query("SELECT t FROM Team t "
            + "WHERE t.name LIKE ?1% ")
    List<Team> searchTeamsByName(String nameTeam);

    Optional<List<Team>> getAllTeamsByLocation(String location);
}