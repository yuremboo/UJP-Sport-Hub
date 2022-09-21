package com.softserve.edu.sporthubujp.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.softserve.edu.sporthubujp.dto.GeolocationResponseDTO;
import com.softserve.edu.sporthubujp.service.impl.GeolocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/location")
@CrossOrigin(origins = "*")
public class GeolocationController {
    private final GeolocationService locationService;

    @Autowired
    public GeolocationController(GeolocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping() // TODO: authorities?
    public ResponseEntity<GeolocationResponseDTO>
    getLocation() throws IOException, GeoIp2Exception {
        log.info("Controller: posting your geolocation by ip");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locationService.getLocation());
    }
}
