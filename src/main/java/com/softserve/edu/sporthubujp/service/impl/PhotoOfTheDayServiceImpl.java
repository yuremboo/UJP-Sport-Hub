package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.PhotoOfTheDayDTO;
import com.softserve.edu.sporthubujp.entity.PhotoOfTheDay;
import com.softserve.edu.sporthubujp.mapper.PhotoOfTheDayMapper;
import com.softserve.edu.sporthubujp.repository.PhotoOfTheDayRepository;
import com.softserve.edu.sporthubujp.service.PhotoOfTheDayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PhotoOfTheDayServiceImpl implements PhotoOfTheDayService {
    private final PhotoOfTheDayRepository photoOfTheDayRepository;
    private final PhotoOfTheDayMapper photoOfTheDayMapper;

    @Autowired
    public PhotoOfTheDayServiceImpl(PhotoOfTheDayRepository photoOfTheDayRepository, PhotoOfTheDayMapper photoOfTheDayMapper) {
        this.photoOfTheDayRepository = photoOfTheDayRepository;
        this.photoOfTheDayMapper = photoOfTheDayMapper;
    }

    @Override
    public PhotoOfTheDayDTO addPhotoOfTheDay(PhotoOfTheDayDTO photoOfTheDayDTO) {
        log.info(String.format("Service: posting photo of the day with an author %s",
                photoOfTheDayDTO.getAuthor()));

        String PHOTO_OF_THE_DAY_ID = "1";
        photoOfTheDayRepository
                .findById(PHOTO_OF_THE_DAY_ID)
                .ifPresent(
                        photoOfTheDay -> photoOfTheDayRepository.deleteAll()
                );

        PhotoOfTheDay photoOfTheDay = photoOfTheDayMapper.dtoToEntity(photoOfTheDayDTO);
        photoOfTheDay.setId(PHOTO_OF_THE_DAY_ID);
        photoOfTheDayRepository.save(photoOfTheDay);
        return photoOfTheDayDTO;
    }
}
