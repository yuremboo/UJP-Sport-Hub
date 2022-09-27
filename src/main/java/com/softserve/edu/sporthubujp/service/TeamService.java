package com.softserve.edu.sporthubujp.service;

import java.util.List;

import com.softserve.edu.sporthubujp.dto.TeamDTO;
import com.softserve.edu.sporthubujp.dto.TeamSubscriptionDTO;

public interface TeamService {
    List<TeamDTO> getAllTeams();

    List<TeamSubscriptionDTO> getAllTeamsBySubscription(String idUser);

    List<TeamDTO> searchTeamsByName(String nameTeam);

    /**
     * Method to get all teams by a location(city)
     *
     * @param location - an actual location(city name)
     * @return a list of {@link TeamDTO} teams
     */
    List<TeamDTO> getAllTeamsByLocation(String location);

}
