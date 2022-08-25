package com.softserve.edu.sporthubujp.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import com.softserve.edu.sporthubujp.dto.TeamDTO;
import com.softserve.edu.sporthubujp.entity.Team;
import com.softserve.edu.sporthubujp.mapper.TeamMapper;
import com.softserve.edu.sporthubujp.repository.TeamRepository;
import com.softserve.edu.sporthubujp.service.TeamService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeamServiceImpl  implements TeamService {

    private static final String TEAM_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";
    private static final String NOT_SEARCH_TEAM_BY_NAME = "Record with name team: %s is not found";
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        List<Team> teams = new LinkedList<Team>();
        teams = teamRepository.findAll();
        log.info("Get all teams in service");
        List<TeamDTO> teamsDTOS = new LinkedList<TeamDTO>();
        for (var team : teams) {
            teamsDTOS.add(teamMapper.entityToDto(team));
        }
        return teamsDTOS;
    }

    @Override
    public List<TeamDTO> getAllTeamsBySubscription(String idUser) {
        List<Team> teams = new LinkedList<Team>();
        teams = teamRepository.getAllTeamsBySubscription(idUser);
        log.info("Get all teams by subscription through user id {}", idUser);
        List<TeamDTO> teamsDTOS = new LinkedList<>();
        for (var team : teams) {
            teamsDTOS.add(teamMapper.entityToDto(team));
        }
        return teamsDTOS;
    }

    @Override
    public List<TeamDTO> searchTeamsByName( String nameTeam) {
        List<Team> teams = new LinkedList<Team>();
        if(nameTeam.length()>=3){
            teams = teamRepository.searchTeamsByName(nameTeam);
        }
        else{
            log.error(String.format(NOT_SEARCH_TEAM_BY_NAME, nameTeam));
            //throw new TeamServiceException(String.format(NOT_SEARCH_TEAM_BY_NAME, nameTeam));
        }
        log.info("Get all teams by name: {}", nameTeam);
        List<TeamDTO> teamsDTOS = new LinkedList<>();
        for (var team : teams) {
            teamsDTOS.add(teamMapper.entityToDto(team));
        }
        return teamsDTOS;
    }

    @Override
    public void deleteTeamByIdSubscription(String idUser, String teamId) {
        log.info("Delete team by subscription through user id {}", idUser);
//        if(teamRepository.existsSubscriptionByIdTeamByIdUser(idUser,teamId))
//        {
//            log.error(String.format(TEAM_NOT_DELETE_BY_ID, teamId));
//            //throw new TeamServiceException(String.format(TEAM_NOT_DELETE_BY_ID, teamId));
//        }
        teamRepository.deleteTeamsBySubscription(idUser, teamId);
//        String st=teamRepository.deleteTeamsBySubscription(idUser, teamId);
//        teamRepository.deleteTeamsBySubscription(st);
        //teamRepository.deleteById( teamId);
    }

}