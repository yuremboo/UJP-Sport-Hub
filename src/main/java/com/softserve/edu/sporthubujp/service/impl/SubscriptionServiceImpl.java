package com.softserve.edu.sporthubujp.service.impl;

import org.springframework.stereotype.Service;

import com.softserve.edu.sporthubujp.exception.ArticleServiceException;
import com.softserve.edu.sporthubujp.mapper.SubscriptionMapper;
import com.softserve.edu.sporthubujp.repository.SubscriptionRepository;
import com.softserve.edu.sporthubujp.service.SubscriptionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubscriptionServiceImpl  implements SubscriptionService {

    private static final String SUBSCRIPTION_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public void deleteSubscriptionByTeam(String id) {
        log.info("Delete subscription by id: {}", id);
        if(!subscriptionRepository.existsById(id))
        {
            log.error(String.format(SUBSCRIPTION_NOT_DELETE_BY_ID, id));
            //throw new ArticleServiceException(String.format(ARTICLE_NOT_DELETE_BY_ID, id));
        }
        subscriptionRepository.deleteById(id);
    }
}
