package com.softserve.edu.sporthubujp.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusDTO;
import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusSaveDTO;
import com.softserve.edu.sporthubujp.entity.comment.LikeDislikeStatus;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface LikeDislikeStatusMapper {
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "comment.id", source = "commentId")
    LikeDislikeStatus dtoToEntity(LikeDislikeStatusDTO likeDislikeStatusDTO);
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "commentId", source = "comment.id")
    LikeDislikeStatusDTO entityToDto(LikeDislikeStatus likeDislikeStatus);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "commentId", source = "comment.id")
    LikeDislikeStatusSaveDTO entityToDtoSave(LikeDislikeStatus likeDislikeStatus);
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "comment.id", source = "commentId")
    LikeDislikeStatus dtoSaveToEntity(LikeDislikeStatusSaveDTO likeDislikeStatusSaveDTO);
    void updateLikeDislikeStatus(@MappingTarget LikeDislikeStatus likeDislikeStatusFromDB, LikeDislikeStatusSaveDTO newLDStatus);
}
