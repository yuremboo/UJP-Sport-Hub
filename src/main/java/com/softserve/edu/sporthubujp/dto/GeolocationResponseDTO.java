package com.softserve.edu.sporthubujp.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GeolocationResponseDTO {
    private String ipAddress;
    private String city;
    private String latitude;
    private String longitude;
}