package com.softserve.edu.sporthubujp.controller;

import com.softserve.edu.sporthubujp.dto.TeamDTO;
import com.softserve.edu.sporthubujp.service.TeamService;
import com.softserve.edu.sporthubujp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import java.security.Principal;
import java.util.List;

import com.softserve.edu.sporthubujp.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.softserve.edu.sporthubujp.dto.TeamSubscriptionDTO;


@Slf4j
@RestController
@RequestMapping("/api/v1/teams") // TODO: changed routes
public class TeamController {
    private final TeamService teamService;

    private final UserService userService;

    @Autowired
    public TeamController(TeamService teamService, UserService userService) {
        this.teamService = teamService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping()
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        log.info("Get all teams");
        return ResponseEntity.status(HttpStatus.OK).body(
                teamService.getAllTeams());
    }

    @GetMapping("/subscription")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<List<TeamSubscriptionDTO>>
    getAllTeamsBySubscription(@NotNull Principal principal) {
        String email = principal.getName();
        log.info("Get all teams of the user with an email under {} subscription", email);
        User user = userService.findUserByEmail(email);
        log.info("Id user = {}", user.getId());

        return ResponseEntity.status(HttpStatus.OK).body(
                teamService.getAllTeamsBySubscription(user.getId()));
    }

    @GetMapping("/search_name/{searchName}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<List<TeamDTO>>
    searchTeamsByName(@PathVariable String searchName) {
        log.info("Get all teams of the name {}", searchName);
        return ResponseEntity.status(HttpStatus.OK).body(
                teamService.searchTeamsByName(searchName));
    }

    @GetMapping("/{location}")
    public ResponseEntity<List<TeamDTO>>
    getAllTeamsByLocation(@PathVariable("location") String location) {
        log.info(String.format("Controller: getting all teams by %s location", location));
        return ResponseEntity.status(HttpStatus.OK).body(
                teamService.getAllTeamsByLocation(location));
    }
}
