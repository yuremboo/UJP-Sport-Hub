package com.softserve.edu.sporthubujp.controller;

import com.softserve.edu.sporthubujp.dto.PhotoOfTheDaySectionDTO;
import com.softserve.edu.sporthubujp.service.PhotoOfTheDaySectionService;
import com.softserve.edu.sporthubujp.service.impl.PhotoOfTheDaySectionServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/photoOfTheDay")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PhotoOfTheDaySectionController {
    private final PhotoOfTheDaySectionService photoOfTheDayService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PhotoOfTheDaySectionDTO> addPhotoOfTheDay(
            @RequestBody @Valid PhotoOfTheDaySectionDTO photoOfTheDaySectionDTO) {
        log.info(String.format("Controller: posting photo of the day with an author %s",
                photoOfTheDaySectionDTO.getAuthor()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(photoOfTheDayService.addPhotoOfTheDay(photoOfTheDaySectionDTO));
    }

    @GetMapping
    public ResponseEntity<PhotoOfTheDaySectionDTO> getPhotoOfTheDay() {
        log.info("Controller: getting photo of the day");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(photoOfTheDayService.getPhotoTheDay());
    }
}
