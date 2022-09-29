package com.softserve.edu.sporthubujp.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.TeamSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.softserve.edu.sporthubujp.dto.TeamDTO;
import com.softserve.edu.sporthubujp.dto.TeamSubscriptionDTO;
import com.softserve.edu.sporthubujp.entity.Subscription;
import com.softserve.edu.sporthubujp.entity.Team;
import com.softserve.edu.sporthubujp.mapper.SubscriptionMapper;
import com.softserve.edu.sporthubujp.mapper.TeamMapper;
import com.softserve.edu.sporthubujp.repository.SubscriptionRepository;
import com.softserve.edu.sporthubujp.repository.TeamRepository;
import com.softserve.edu.sporthubujp.service.TeamService;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class TeamServiceImpl  implements TeamService {
    private static final String NOT_SEARCH_TEAM_BY_NAME = "Record with name team: %s is not found";
    private final TeamRepository teamRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final TeamMapper teamMapper;
    private final SubscriptionMapper subscriptionMapper;
    public TeamServiceImpl(TeamRepository teamRepository, SubscriptionRepository subscriptionRepository, TeamMapper teamMapper,
        SubscriptionMapper subscriptionMapper) {
        this.teamRepository = teamRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.teamMapper = teamMapper;
        this.subscriptionMapper = subscriptionMapper;
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

    @Override public TeamSaveDTO postTeam(TeamSaveDTO newTeam) {
        Team team = teamMapper.saveDtoToEntity(newTeam);
        return teamMapper.entityToDtoSave(
            teamRepository.save(team));
    }
    @Override
    public List<TeamSubscriptionDTO> getAllTeamsBySubscription(String idUser) {
        List<Team> teams = new LinkedList<Team>();
        teams = teamRepository.getAllTeamsBySubscription(idUser);
        log.info("Get all teams by subscription through user id {}", idUser);
        List<TeamDTO> teamsDTOS = new LinkedList<>();
        for (var team : teams) {
            teamsDTOS.add(teamMapper.entityToDto(team));
        }

        List<TeamSubscriptionDTO> teamSubscriptionDTO = new LinkedList<>();
        for (var teamsDTO : teamsDTOS) {
            String subscription=subscriptionMapper.entityToDto(subscriptionRepository.getAllSubscriptionByTeams(idUser,teamsDTO.getId())).getId();
            log.info("Get all teams by subscription id {}", subscription);
            teamSubscriptionDTO.add(new TeamSubscriptionDTO(teamsDTO,subscription));
        }
        return teamSubscriptionDTO;
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
    public List<TeamDTO> getAllTeamsByLocation(String location){
        log.info(String.format("Service: getting all teams by %s location", location));

        List<Team> teams = teamRepository
                .getAllTeamsByLocation(location)
                .orElseThrow(EntityNotFoundException::new);

        return teams
                .stream()
                .map(teamMapper::entityToDto)
                .collect(Collectors.toList());
    }
}