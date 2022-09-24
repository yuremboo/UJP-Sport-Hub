package com.softserve.edu.sporthubujp.controller;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.dto.UserSaveProfileDTO;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(path = "/profile")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<UserDTO> updateProfile(@NotNull Principal principal,
        @RequestBody UserSaveProfileDTO newUser) {
        User user = userService.findUserByEmail(principal.getName());
        log.info(String.format("Controller: updating user with id %s", user.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(
            userService.updateUser(user, newUser));
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<UserSaveProfileDTO> getUserById(@PathVariable String id) {
        log.info("Get user by id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(
            userService.findUserById(id));
    }

    @GetMapping(path = "/profile")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<UserDTO> getUser(@NotNull Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        log.info(String.format("Controller: get user with id %s", user.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(
            userService.getUser(user));
    }
}