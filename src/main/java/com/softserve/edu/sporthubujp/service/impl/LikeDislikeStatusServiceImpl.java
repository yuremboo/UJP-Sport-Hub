package com.softserve.edu.sporthubujp.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.sporthubujp.dto.comment.CommentDTO;
import com.softserve.edu.sporthubujp.dto.comment.LikeDislikeStatusDTO;
import com.softserve.edu.sporthubujp.entity.comment.Comment;
import com.softserve.edu.sporthubujp.entity.comment.LikeDislikeStatus;
import com.softserve.edu.sporthubujp.mapper.LikeDislikeStatusMapper;
import com.softserve.edu.sporthubujp.repository.LikeDislikeStatusRepository;
import com.softserve.edu.sporthubujp.service.LikeDislikeStatusService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LikeDislikeStatusServiceImpl implements LikeDislikeStatusService {
    private static final String LIKEDISLIKESTATUS_NOT_FOUND_BY_ID = "Like-dislike status not found by id: %s";
    private final LikeDislikeStatusRepository likeDislikeStatusRepository;
    private final LikeDislikeStatusMapper likeDislikeStatusMapper;

    @Autowired
    public LikeDislikeStatusServiceImpl(LikeDislikeStatusRepository likeDislikeStatusRepository,
        LikeDislikeStatusMapper likeDislikeStatusMapper) {
        this.likeDislikeStatusRepository = likeDislikeStatusRepository;
        this.likeDislikeStatusMapper = likeDislikeStatusMapper;
    }

    @Override public List<LikeDislikeStatusDTO> getAllStatusesByUserId(String userId) {
        List<LikeDislikeStatus> statuses;
        statuses = likeDislikeStatusRepository.findAllByUserId(userId);
        log.info("Get all like-dislike statuses by user id {} in service", userId);
        List<LikeDislikeStatusDTO> statusDTOS = new LinkedList<>();
        for (var status : statuses) {
            statusDTOS.add(likeDislikeStatusMapper.entityToDto(status));
        }
        return statusDTOS;
    }

    @Override public void deleteLikeDislikeStatus(String id) {
        log.info("Delete like-dislike status by id in service");
        if (!likeDislikeStatusRepository.existsById(id)) {
            log.error(String.format(LIKEDISLIKESTATUS_NOT_FOUND_BY_ID, id));
        }
        likeDislikeStatusRepository.deleteById(id);
    }

    @Override public LikeDislikeStatus updateLikeDislikeStatus(LikeDislikeStatusDTO newLDStatus, String id) {
        return likeDislikeStatusRepository.findById(id)
            .map(likeDislikeStatus -> {
                likeDislikeStatusMapper.updateLikeDislikeStatus(likeDislikeStatus, likeDislikeStatusMapper.dtoToEntity(newLDStatus));
                return likeDislikeStatusRepository.save(likeDislikeStatus);
            }).orElseGet(()->{
                newLDStatus.setId(id);
                return likeDislikeStatusRepository.save( likeDislikeStatusMapper.dtoToEntity(newLDStatus));
            });
    }

    @Override public LikeDislikeStatus addNewLikeDislikeStatus(LikeDislikeStatusDTO newLDStatus) {
        return likeDislikeStatusRepository.save(likeDislikeStatusMapper.dtoToEntity(newLDStatus));
    }
}
