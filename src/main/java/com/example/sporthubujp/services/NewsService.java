package com.example.sporthubujp.services;

import com.example.sporthubujp.dto.NewsDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Service
public class NewsService {
    @GetMapping
    public NewsDTO getNewsById(Long id) {
        return new NewsDTO(id, LocalDate.of(2022, 7, 12),
                "NFL", "AFC South", "Tennessee", "Hercle, torus germanus!," +
                " bi-color plasmator! Pol, a bene brabeuta, glos!", List.of("comment1", "comment2")); // todo change
    }
}
