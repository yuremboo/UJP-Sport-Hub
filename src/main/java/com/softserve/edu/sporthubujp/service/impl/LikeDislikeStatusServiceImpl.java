package com.softserve.edu.sporthubujp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusDTO;
import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusSaveDTO;
import com.softserve.edu.sporthubujp.entity.comment.LikeDislikeStatus;
import com.softserve.edu.sporthubujp.exception.EntityAlreadyExistsException;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.mapper.LikeDislikeStatusMapper;
import com.softserve.edu.sporthubujp.repository.CommentRepository;
import com.softserve.edu.sporthubujp.repository.LikeDislikeStatusRepository;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.service.LikeDislikeStatusService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LikeDislikeStatusServiceImpl implements LikeDislikeStatusService {
    private static final String COMMENT_NOT_FOUND_BY_ID = "Comment not found by id: %s";
    public static final String USER_NOT_FOUND_BY_ID = "User not found by id: %s";
    private static final String LIKEDISLIKESTATUS_NOT_FOUND_BY_ID = "Like-dislike status not found by id: %s";
    private static final String LIKEDISLIKESTATUS_ALREADY_EXISTS_FOR_IDS = "Like-dislike status already exists for comment with id: %s and user with id: %s";
    private final LikeDislikeStatusRepository likeDislikeStatusRepository;
    private final LikeDislikeStatusMapper likeDislikeStatusMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public LikeDislikeStatusServiceImpl(LikeDislikeStatusRepository likeDislikeStatusRepository,
        LikeDislikeStatusMapper likeDislikeStatusMapper,
        CommentRepository commentRepository,
        UserRepository userRepository) {
        this.likeDislikeStatusRepository = likeDislikeStatusRepository;
        this.likeDislikeStatusMapper = likeDislikeStatusMapper;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override public LikeDislikeStatusDTO getStatusByUserIdAndCommentId(String userId, String commentId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotExistsException(String.format(USER_NOT_FOUND_BY_ID, userId));
        }
        if (!commentRepository.existsById(commentId)) {
            throw new EntityNotExistsException(String.format(COMMENT_NOT_FOUND_BY_ID, commentId));
        }
        LikeDislikeStatus status = likeDislikeStatusRepository.findByUserIdAndCommentId(userId, commentId);
        log.info("Get like-dislike status by user id {} and comment id {} in service", userId, commentId);
        return likeDislikeStatusMapper.entityToDto(status);
    }

    @Override public void deleteLikeDislikeStatus(String id) {
        log.info("Delete like-dislike status by id in service");
        if (!likeDislikeStatusRepository.existsById(id)) {
            log.error(String.format(LIKEDISLIKESTATUS_NOT_FOUND_BY_ID, id));
            throw new EntityNotExistsException(String.format(LIKEDISLIKESTATUS_NOT_FOUND_BY_ID, id));
        }
        likeDislikeStatusRepository.deleteById(id);
    }

    @Override
    public LikeDislikeStatusSaveDTO updateLikeDislikeStatus(LikeDislikeStatusSaveDTO newLDStatus, String id) {
        if (!commentRepository.existsById(newLDStatus.getCommentId())) {
            throw new EntityNotExistsException(String.format(COMMENT_NOT_FOUND_BY_ID, newLDStatus.getCommentId()));
        } else if (!userRepository.existsById(newLDStatus.getUserId())) {
            throw new EntityNotExistsException(String.format(USER_NOT_FOUND_BY_ID, newLDStatus.getUserId()));
        }
        return likeDislikeStatusRepository.findById(id)
            .map(likeDislikeStatus -> {
                likeDislikeStatusMapper.updateLikeDislikeStatus(likeDislikeStatus, newLDStatus);
                return likeDislikeStatusMapper.entityToDtoSave(likeDislikeStatusRepository.save(likeDislikeStatus));
            })
            .orElseThrow(EntityNotExistsException::new);
    }

    @Override
    public LikeDislikeStatusSaveDTO addNewLikeDislikeStatus(LikeDislikeStatusSaveDTO newLDStatus) {
        if (!commentRepository.existsById(newLDStatus.getCommentId())) {
            throw new EntityNotExistsException(String.format(COMMENT_NOT_FOUND_BY_ID, newLDStatus.getCommentId()));
        } else if (!userRepository.existsById(newLDStatus.getUserId())) {
            throw new EntityNotExistsException(String.format(USER_NOT_FOUND_BY_ID, newLDStatus.getUserId()));
        } else if (likeDislikeStatusRepository.findByUserIdAndCommentId(newLDStatus.getUserId(), newLDStatus.getCommentId()) != null) {
            throw new EntityAlreadyExistsException(String.format(LIKEDISLIKESTATUS_ALREADY_EXISTS_FOR_IDS, newLDStatus.getCommentId(), newLDStatus.getUserId()));
        }
        return likeDislikeStatusMapper.entityToDtoSave(
            likeDislikeStatusRepository.save(likeDislikeStatusMapper.dtoSaveToEntity(newLDStatus)));
    }
}
