package com.softserve.edu.sporthubujp.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softserve.edu.sporthubujp.dto.ArticleSaveDTO;
import com.softserve.edu.sporthubujp.dto.SubscriptionDTO;
import com.softserve.edu.sporthubujp.dto.SubscriptionSaveDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.entity.Subscription;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SubscriptionMapper {
    Subscription dtoToEntity(SubscriptionDTO subscriptionDTO);
    SubscriptionDTO entityToDto(Subscription subscription);
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "team.id", source = "teamId")
    Subscription saveDtoToEntity(SubscriptionSaveDTO subscriptionDTO);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "teamId", source = "team.id")
    SubscriptionSaveDTO entityToDtoSave(Subscription subscription);
}