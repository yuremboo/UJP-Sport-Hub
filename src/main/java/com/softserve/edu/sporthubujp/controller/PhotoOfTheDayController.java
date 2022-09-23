package com.softserve.edu.sporthubujp.controller;

import com.softserve.edu.sporthubujp.dto.PhotoOfTheDayDTO;
import com.softserve.edu.sporthubujp.dto.RegistrationRequestDTO;
import com.softserve.edu.sporthubujp.service.impl.PhotoOfTheDayServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.SendFailedException;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/photoOFTheDay")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhotoOfTheDayController {

    private final PhotoOfTheDayServiceImpl photoOfTheDayService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PhotoOfTheDayDTO> addPhotoOfTheDay(
            @RequestBody @Valid PhotoOfTheDayDTO photoOfTheDayDTO) {
        log.info(String.format("Controller: posting photo of the day with an author %s",
                photoOfTheDayDTO.getAuthor()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(photoOfTheDayService.addPhotoOfTheDay(photoOfTheDayDTO));
    }
}
