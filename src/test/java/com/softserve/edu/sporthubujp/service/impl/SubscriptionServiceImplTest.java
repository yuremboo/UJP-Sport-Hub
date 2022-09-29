package com.softserve.edu.sporthubujp.service.impl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.softserve.edu.sporthubujp.dto.SubscriptionSaveDTO;
import com.softserve.edu.sporthubujp.entity.Subscription;
import com.softserve.edu.sporthubujp.entity.comment.Comment;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.mapper.SubscriptionMapper;
import com.softserve.edu.sporthubujp.repository.SubscriptionRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceImplTest {
    private static final String SUBSCRIPTION_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";

    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private SubscriptionMapper subscriptionMapper;
    @InjectMocks
    private SubscriptionServiceImpl underTest;
    @Test
    void canPostSubscription() {
        SubscriptionSaveDTO subscriptionSaveDTO = spy(new SubscriptionSaveDTO());
        Subscription subscription = spy(new Subscription());

        when(subscriptionMapper.saveDtoToEntity(any()))
            .thenReturn(subscription);

        when(subscriptionMapper.entityToDtoSave(any()))
            .thenReturn(subscriptionSaveDTO);

        SubscriptionSaveDTO subscriptionSaveDTOUnderTest = underTest.postSubscription(subscriptionSaveDTO);
        verify(subscriptionRepository).save(subscription);

        Assertions.assertThat(subscriptionSaveDTOUnderTest).isEqualTo(subscriptionSaveDTO);
    }
    @Test
    void cannotDeleteNotExistingSubscription() {
        String id = anyString();
        when(subscriptionRepository.existsById(id))
            .thenReturn(false);
        assertThatThrownBy(() -> underTest.deleteSubscriptionByTeam(id))
            .isInstanceOf(EntityNotExistsException.class)
            .hasMessageContaining(String.format(SUBSCRIPTION_NOT_DELETE_BY_ID,
                id));
        verify(subscriptionRepository, never()).delete(any(Subscription.class));
    }
}
