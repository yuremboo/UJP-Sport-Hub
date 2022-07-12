package com.example.sporthubujp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class NewsDTO {
    private Long id;
    private LocalDate publicationDate;
    private String sportType;
    private String league;
    private String location;
    private String mainText;
    private List<String> comments; //todo change String to Comment
}
