package com.softserve.edu.sporthubujp.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusDTO;
import com.softserve.edu.sporthubujp.entity.comment.LikeDislikeStatus;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface LikeDislikeStatusMapper {
    LikeDislikeStatus dtoToEntity(LikeDislikeStatusDTO likeDislikeStatusDTO);
    LikeDislikeStatusDTO entityToDto(LikeDislikeStatus likeDislikeStatus);
    void updateLikeDislikeStatus(@MappingTarget LikeDislikeStatus likeDislikeStatusFromDB, LikeDislikeStatus newLDStatus);
}
