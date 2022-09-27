package com.softserve.edu.sporthubujp.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.sporthubujp.exception.ArticleServiceException;
import com.softserve.edu.sporthubujp.mapper.SubscriptionMapper;
import com.softserve.edu.sporthubujp.repository.SubscriptionRepository;
import com.softserve.edu.sporthubujp.service.SubscriptionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final String SUBSCRIPTION_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public void deleteSubscriptionByTeam(String id) {
        log.info("Delete subscription by id: {}", id);
        if (!subscriptionRepository.existsById(id)) {
            log.error(String.format(SUBSCRIPTION_NOT_DELETE_BY_ID, id));
            //throw new ArticleServiceException(String.format(ARTICLE_NOT_DELETE_BY_ID, id));
        }
        subscriptionRepository.deleteById(id);
    }
}
