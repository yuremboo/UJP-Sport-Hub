package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.PhotoOfTheDaySectionDTO;
import com.softserve.edu.sporthubujp.entity.PhotoOfTheDay;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PhotoOfTheDayMapper {
    PhotoOfTheDay dtoToEntity(PhotoOfTheDaySectionDTO photoOfTheDaySectionDTO);

    PhotoOfTheDaySectionDTO entityToDto(PhotoOfTheDay photoOfTheDay);
}
