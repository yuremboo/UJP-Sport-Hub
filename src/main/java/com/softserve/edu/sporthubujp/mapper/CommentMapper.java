package com.softserve.edu.sporthubujp.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import com.softserve.edu.sporthubujp.dto.CommentDTO;
import com.softserve.edu.sporthubujp.entity.Comment;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CommentMapper {
    Comment dtoToEntity(CommentDTO commentDTO);
    CommentDTO entityToDto(Comment comment);
}
