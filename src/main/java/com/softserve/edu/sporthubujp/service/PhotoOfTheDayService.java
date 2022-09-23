package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.PhotoOfTheDayDTO;
import org.springframework.stereotype.Service;

@Service
public interface PhotoOfTheDayService {
    PhotoOfTheDayDTO addPhotoOfTheDay(PhotoOfTheDayDTO photoOfTheDayDTO);
}
