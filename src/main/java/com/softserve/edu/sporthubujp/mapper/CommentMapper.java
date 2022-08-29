package com.softserve.edu.sporthubujp.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.entity.comment.Comment;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CommentMapper {
    Comment dtoToEntity(CommentDTO commentDTO);
    CommentDTO entityToDto(Comment comment);

    void updateComment(@MappingTarget Comment commentFromDB, Comment newComment);
}
