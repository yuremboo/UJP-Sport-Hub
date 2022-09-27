package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.PhotoOfTheDaySectionDTO;
import com.softserve.edu.sporthubujp.entity.PhotoOfTheDay;
import com.softserve.edu.sporthubujp.mapper.PhotoOfTheDayMapper;
import com.softserve.edu.sporthubujp.repository.PhotoOfTheDayRepository;
import com.softserve.edu.sporthubujp.service.PhotoOfTheDaySectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class PhotoOfTheDaySectionServiceImpl implements PhotoOfTheDaySectionService {
    private final PhotoOfTheDayRepository photoOfTheDayRepository;
    private final PhotoOfTheDayMapper photoOfTheDayMapper;
    private final String PHOTO_OF_THE_DAY_ID = "12195536-0328-447c-b2da-d4b126b8b3d0";

    @Autowired
    public PhotoOfTheDaySectionServiceImpl(PhotoOfTheDayRepository photoOfTheDayRepository, PhotoOfTheDayMapper photoOfTheDayMapper) {
        this.photoOfTheDayRepository = photoOfTheDayRepository;
        this.photoOfTheDayMapper = photoOfTheDayMapper;
    }

    @Override
    public PhotoOfTheDaySectionDTO addPhotoOfTheDay(PhotoOfTheDaySectionDTO photoOfTheDaySectionDTO) {
        log.info(String.format("Service: posting photo of the day with an author %s",
                photoOfTheDaySectionDTO.getAuthor()));

        photoOfTheDayRepository
                .findById(PHOTO_OF_THE_DAY_ID)
                .ifPresent(
                        photoOfTheDay -> photoOfTheDayRepository.deleteById(PHOTO_OF_THE_DAY_ID)
                );

        PhotoOfTheDay photoOfTheDay = photoOfTheDayMapper.dtoToEntity(photoOfTheDaySectionDTO);
        photoOfTheDay.setId(PHOTO_OF_THE_DAY_ID);
        photoOfTheDayRepository.save(photoOfTheDay);
        return photoOfTheDaySectionDTO;
    }
    @Override
    public PhotoOfTheDaySectionDTO getPhotoTheDay() {
        log.info("Service: getting photo of the day");

        PhotoOfTheDay photoOfTheDay = photoOfTheDayRepository
                .findById(PHOTO_OF_THE_DAY_ID)
                .orElseThrow(EntityNotFoundException::new);

        return photoOfTheDayMapper.entityToDto(photoOfTheDay);
    }
}
