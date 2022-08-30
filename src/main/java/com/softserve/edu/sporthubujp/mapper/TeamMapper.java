package com.softserve.edu.sporthubujp.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import com.softserve.edu.sporthubujp.dto.TeamDTO;
import com.softserve.edu.sporthubujp.entity.Team;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TeamMapper {
    Team dtoToEntity(TeamDTO teamDTO);
    TeamDTO entityToDto(Team team);
}