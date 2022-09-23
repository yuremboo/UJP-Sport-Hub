package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.PhotoOfTheDayDTO;
import com.softserve.edu.sporthubujp.mapper.PhotoOfTheDayMapper;
import com.softserve.edu.sporthubujp.repository.PhotoOfTheDayRepository;
import com.softserve.edu.sporthubujp.service.PhotoOfTheDayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhotoOfTheDayServiceImpl implements PhotoOfTheDayService {
    private final PhotoOfTheDayRepository photoOfTheDayRepository;
    private final PhotoOfTheDayMapper photoOfTheDayMapper;

    public PhotoOfTheDayDTO addPhotoOfTheDay(PhotoOfTheDayDTO photoOfTheDayDTO){
        log.info(String.format("Service: posting photo of the day with an author %s",
                photoOfTheDayDTO.getAuthor()));

        Integer PHOTO_OF_THE_DAY_ID = 1;
        photoOfTheDayRepository
                .findById(PHOTO_OF_THE_DAY_ID)
                .ifPresent(
                        photoOfTheDay -> photoOfTheDayRepository.deleteAll()
                );

        photoOfTheDayRepository.save(photoOfTheDayMapper.dtoToEntity(photoOfTheDayDTO));
        return photoOfTheDayDTO;
    }
}
