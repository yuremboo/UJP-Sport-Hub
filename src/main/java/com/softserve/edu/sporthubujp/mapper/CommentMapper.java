package com.softserve.edu.sporthubujp.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.dto.comment.CommentSaveDTO;
import com.softserve.edu.sporthubujp.entity.comment.Comment;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CommentMapper {
    Comment dtoToEntity(CommentDTO commentDTO);
    CommentDTO entityToDto(Comment comment);

    @Mapping(target = "article.id", source = "articleId")
    @Mapping(target = "user.id", source = "commenterId")
    Comment dtoSaveToEntity(CommentSaveDTO commentSaveDTO);
    @Mapping(target = "articleId", source = "article.id")
    @Mapping(target = "commenterId", source = "user.id")
    CommentSaveDTO entityToDtoSave(Comment comment);

    void updateComment(@MappingTarget Comment commentFromDB, CommentSaveDTO newComment);
}
