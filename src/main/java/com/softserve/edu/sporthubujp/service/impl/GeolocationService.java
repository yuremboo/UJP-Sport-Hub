package com.softserve.edu.sporthubujp.service.impl;


import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.softserve.edu.sporthubujp.dto.GeolocationResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

@Service
@Slf4j
public class GeolocationService {
    private final DatabaseReader databaseReader;
    private static final String CHECK_IP = "https://checkip.amazonaws.com";
    private static final String GEO_DATABASE = "src/main/resources/GeoLite2-City.mmdb";

    @Autowired
    public GeolocationService() throws IOException {
        File database = new File(GEO_DATABASE);
        databaseReader = new DatabaseReader.Builder(database).build();
    }

    public GeolocationResponseDTO getLocation()
            throws IOException, GeoIp2Exception {

        URL whatIsMyIp = new URL(CHECK_IP);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                whatIsMyIp.openStream()));
        String actualIp = reader.readLine();
        log.info(String.format("Service: posting geolocation by ip of %s ", actualIp));

        InetAddress ipAddress = InetAddress.getByName(actualIp);
        CityResponse response = databaseReader.city(ipAddress);
        String cityName = response.getCity().getName();
        String latitude = response.getLocation().getLatitude().toString();
        String longitude = response.getLocation().getLongitude().toString();

        return new GeolocationResponseDTO(actualIp, cityName, latitude, longitude);
    }
}
