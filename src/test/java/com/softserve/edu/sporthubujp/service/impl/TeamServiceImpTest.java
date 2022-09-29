package com.softserve.edu.sporthubujp.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softserve.edu.sporthubujp.dto.SubscriptionSaveDTO;
import com.softserve.edu.sporthubujp.dto.TeamSaveDTO;
import com.softserve.edu.sporthubujp.entity.Subscription;
import com.softserve.edu.sporthubujp.entity.Team;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.mapper.SubscriptionMapper;
import com.softserve.edu.sporthubujp.mapper.TeamMapper;
import com.softserve.edu.sporthubujp.repository.SubscriptionRepository;
import com.softserve.edu.sporthubujp.repository.TeamRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamServiceImpTest {
    private static final String SUBSCRIPTION_NOT_DELETE_BY_ID = "Record with provided id: %s is not found";

    @Mock
    private TeamRepository teamRepository;
    @Mock
    private TeamMapper teamMapper;
    @InjectMocks
    private TeamServiceImpl underTest;

    @Test
    void canPostTeam() {
        TeamSaveDTO teamSaveDTO = spy(new TeamSaveDTO());
        Team team = spy(new Team());

        when(teamMapper.saveDtoToEntity(any()))
            .thenReturn(team);

        when(teamMapper.entityToDtoSave(any()))
            .thenReturn(teamSaveDTO);

        TeamSaveDTO teamSaveDTOUnderTest = underTest.postTeam(teamSaveDTO);
        verify(teamRepository).save(team);

        Assertions.assertThat(teamSaveDTOUnderTest).isEqualTo(teamSaveDTO);
    }
}