package com.softserve.edu.sporthubujp.controller;

import com.softserve.edu.sporthubujp.service.impl.ImageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/image")
//@AllArgsConstructor
@Slf4j
@CrossOrigin
public class ImageController {

    private final ImageServiceImpl imageService;

    @Autowired
    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile multipartImage)
            throws IOException {

        log.info(String.format("Controller: uploading image with a name %s", multipartImage.getName()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(imageService.uploadImage(multipartImage));
    }

    @GetMapping("/download/{image}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<Resource> downloadImage(@PathVariable("image") String imageId) {

        log.info(String.format("Controller: downloading image with an id %s", imageId));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(imageService.downloadImage(imageId));
    }
}
