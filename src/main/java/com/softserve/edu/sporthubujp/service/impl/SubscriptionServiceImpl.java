package com.softserve.edu.sporthubujp.service.impl;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.sporthubujp.dto.SubscriptionDTO;
import com.softserve.edu.sporthubujp.dto.SubscriptionSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.entity.Category;
import com.softserve.edu.sporthubujp.entity.Subscription;
import com.softserve.edu.sporthubujp.entity.Team;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.exception.ArticleServiceException;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.mapper.SubscriptionMapper;
import com.softserve.edu.sporthubujp.repository.SubscriptionRepository;
import com.softserve.edu.sporthubujp.repository.TeamRepository;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.service.SubscriptionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final String SUBSCRIPTION_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Override
    public void deleteSubscriptionByTeam(String id) {
        log.info("Delete subscription by id: {}", id);
        if (!subscriptionRepository.existsById(id)) {
            log.error(String.format(SUBSCRIPTION_NOT_DELETE_BY_ID, id));
            throw new EntityNotExistsException(String.format(SUBSCRIPTION_NOT_DELETE_BY_ID, id));
        }
        subscriptionRepository.deleteById(id);
    }

    @Override
    public SubscriptionSaveDTO postSubscription(SubscriptionSaveDTO newSubscription) {
        Subscription subscription = subscriptionMapper.saveDtoToEntity(newSubscription);
        return subscriptionMapper.entityToDtoSave(
            subscriptionRepository.save(subscription));
    }
}
