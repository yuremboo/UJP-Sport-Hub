package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.PhotoOfTheDaySectionDTO;
import org.springframework.stereotype.Service;

@Service
public interface PhotoOfTheDaySectionService {

    /**
     * Method for posting photo of the day section except a photo itself
     *
     * @param photoOfTheDaySectionDTO - is photo of the day object of {@link PhotoOfTheDaySectionDTO}
     * @return photo of the day section object of {@link PhotoOfTheDaySectionDTO}
     */
    PhotoOfTheDaySectionDTO addPhotoOfTheDay(PhotoOfTheDaySectionDTO photoOfTheDaySectionDTO);

    /**
     * Method for getting photo of the day section except a photo itself
     *
     * @return photo of the day section object of {@link PhotoOfTheDaySectionDTO}
     */
    public PhotoOfTheDaySectionDTO getPhotoTheDay();
}
