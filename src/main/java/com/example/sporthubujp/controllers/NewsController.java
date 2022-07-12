package com.example.sporthubujp.controllers;

import com.example.sporthubujp.dto.NewsDTO;
import com.example.sporthubujp.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/news")//todo change
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/find/")
    public ResponseEntity<NewsDTO> getNewsById(@RequestParam("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(newsService.getNewsById(id));
    }
}
