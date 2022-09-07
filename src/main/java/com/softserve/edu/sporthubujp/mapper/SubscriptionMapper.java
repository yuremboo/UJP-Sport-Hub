package com.softserve.edu.sporthubujp.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import com.softserve.edu.sporthubujp.dto.SubscriptionDTO;
import com.softserve.edu.sporthubujp.entity.Subscription;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SubscriptionMapper {
    Subscription dtoToEntity(SubscriptionDTO subscriptionDTO);
    SubscriptionDTO entityToDto(Subscription subscription);
}