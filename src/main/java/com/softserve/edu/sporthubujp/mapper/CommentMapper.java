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
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "article.id", source = "articleId")
    Comment dtoToEntity(CommentDTO commentDTO);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "articleId", source = "article.id")
    CommentDTO entityToDto(Comment comment);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "article.id", source = "articleId")
    Comment dtoSaveToEntity(CommentSaveDTO commentSaveDTO);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "articleId", source = "article.id")
    CommentSaveDTO entityToDtoSave(Comment comment);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "article.id", source = "articleId")
    void updateComment(@MappingTarget Comment commentFromDB, CommentSaveDTO newComment);
}
