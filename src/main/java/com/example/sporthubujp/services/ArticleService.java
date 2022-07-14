package com.example.sporthubujp.services;

import com.example.sporthubujp.dto.ArticleDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Service
public class ArticleService {
    public ArticleDTO getArticleById(String id) {
        return new ArticleDTO(id, "As M.L.B.'s Season Opens in Japan, at " +
                "least" +
                " the Dirt is Familiar.",
                "Pol. Decor de lotus byssus, anhelare compater! Medicinas sunt hippotoxotas de brevis adgium." +
                        " Rusticus omnias ducunt ad acipenser.", Boolean.TRUE, Boolean.TRUE, "22",
                        LocalDateTime.of(2022, 7, 12, 12, 36, 54),
                        LocalDateTime.of(2022, 7, 13, 14, 54, 32));
    }
}
