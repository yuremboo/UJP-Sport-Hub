package com.softserve.edu.sporthubujp.service;

import com.softserve.edu.sporthubujp.dto.SubscriptionDTO;
import com.softserve.edu.sporthubujp.dto.SubscriptionSaveDTO;

public interface SubscriptionService {
    void deleteSubscriptionByTeam(String id);

    SubscriptionSaveDTO postSubscription(SubscriptionSaveDTO newSubscription);
}
