package com.softserve.edu.sporthubujp.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softserve.edu.sporthubujp.dto.TeamDTO;
import com.softserve.edu.sporthubujp.dto.TeamSaveDTO;
import com.softserve.edu.sporthubujp.entity.Team;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TeamMapper {
    Team dtoToEntity(TeamDTO teamDTO);
    TeamDTO entityToDto(Team team);
    @Mapping(target = "category.id", source = "categoryId")
    Team saveDtoToEntity(TeamSaveDTO teamDTO);
    @Mapping(target = "categoryId", source = "category.id")
    TeamSaveDTO entityToDtoSave(Team team);
}