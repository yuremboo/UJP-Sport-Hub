package com.softserve.edu.sporthubujp.controller;

import com.softserve.edu.sporthubujp.service.impl.StorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/image")

public class ImageController {
    private final StorageServiceImpl storageService;

    @Autowired
    public ImageController(StorageServiceImpl storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getImage(HttpServletResponse response, @PathVariable("id") String id){
        log.info(String.format("Controller: getting image with an id %s", id));

        storageService.getImage(response, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping
    // TODO: admin
    public ResponseEntity<Map<String, String>> uploadImage(
            @RequestParam("image") @Valid MultipartFile uploadedFileRef,
            @RequestParam("photoOfTheDay") boolean isPhotoOfTheDay) {
        log.info(String.format("Controller: uploading image with a name %s",
                uploadedFileRef.getOriginalFilename()));

        HashMap<String, String> response = new HashMap<>();
        response.put("imageUrl", "api/v1/image/" +
                storageService.uploadImage(uploadedFileRef, isPhotoOfTheDay));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteImage(@PathVariable String id) {
        log.info(String.format("Controller: deleting image with an id %s", id));
        storageService.deleteImage(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
