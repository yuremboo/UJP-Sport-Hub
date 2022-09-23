package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.PhotoOfTheDayDTO;
import com.softserve.edu.sporthubujp.entity.PhotoOfTheDay;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PhotoOfTheDayMapper {
    PhotoOfTheDay dtoToEntity(PhotoOfTheDayDTO photoOfTheDayDTO);

    PhotoOfTheDayDTO entityToDto(PhotoOfTheDay photoOfTheDay);
}
