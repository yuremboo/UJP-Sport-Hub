package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.PhotoOfTheDayDTO;
import com.softserve.edu.sporthubujp.entity.PhotoOfTheDay;
import org.springframework.stereotype.Service;

@Service
public interface PhotoOfTheDayService {
    PhotoOfTheDayDTO addPhotoOfTheDay(PhotoOfTheDayDTO photoOfTheDayDTO);
    public PhotoOfTheDayDTO getPhotoTheDay();
}
