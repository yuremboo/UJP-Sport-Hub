package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.PhotoOfTheDayDTO;
import com.softserve.edu.sporthubujp.entity.PhotoOfTheDay;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PhotoOfTheDayMapper {
    PhotoOfTheDay dtoToEntity(PhotoOfTheDayDTO photoOfTheDayDTO);

    PhotoOfTheDayDTO entityToDto(PhotoOfTheDay photoOfTheDay);
}
