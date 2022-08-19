package com.softserve.edu.sporthubujp.service;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.TeamDTO;

public interface TeamService {
    List<TeamDTO> getAllTeams();

    List<TeamDTO> getAllTeamsBySubscription(String idUser);

    void deleteTeamByIdSubscription(String idUser, String teamId);
}
