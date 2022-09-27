package com.softserve.edu.sporthubujp.controller;

import com.softserve.edu.sporthubujp.dto.RegistrationRequestDTO;
import com.softserve.edu.sporthubujp.service.RegistrationService;
import com.softserve.edu.sporthubujp.service.impl.RegistrationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.SendFailedException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/registration")
//@AllArgsConstructor
@Slf4j
@CrossOrigin
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid RegistrationRequestDTO request)
            throws IOException, SendFailedException {
        log.info(String.format("Controller: registering user with email %s", request.getEmail()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(registrationService.register(request));
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        log.info(String.format("Controller: confirming token %s", token));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(registrationService.confirmToken(token));
    }

}

