package com.softserve.edu.sporthubujp.service.impl;


import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.softserve.edu.sporthubujp.dto.GeolocationResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
@Slf4j
public class GeolocationService {
    private final DatabaseReader databaseReader;

    @Autowired
    public GeolocationService() throws IOException {
        File database = new File("src/main/resources/GeoLite2-City.mmdb");
        databaseReader = new DatabaseReader.Builder(database).build();
    }

    public GeolocationResponseDTO getLocation(String ip)
            throws IOException, GeoIp2Exception {
        log.info(String.format("Service: posting geolocation by ip of %s ", ip));

        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = databaseReader.city(ipAddress);
        String cityName = response.getCity().getName();
        String latitude = response.getLocation().getLatitude().toString();
        String longitude = response.getLocation().getLongitude().toString();

        return new GeolocationResponseDTO(ip, cityName, latitude, longitude);
    }
}
