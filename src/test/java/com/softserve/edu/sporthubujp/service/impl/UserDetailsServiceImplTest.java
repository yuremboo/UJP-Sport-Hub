package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.entity.Role;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {
    @Mock
    private UserRepository userRepository;
    static MockedStatic<User> dummyStaticUser = Mockito.mockStatic(User.class, RETURNS_DEEP_STUBS);
    @InjectMocks
    private UserDetailsServiceImpl underTest;

    @AfterAll
    public static void close() {
        dummyStaticUser.close();
    }

    @Test
    void firstWillThrowLoadUserByUsername() {

        given(userRepository.findByEmail(anyString()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.loadUserByUsername(anyString()))
                .isInstanceOf(InternalAuthenticationServiceException.class)
                .hasMessageContaining("Incorrect user ID or password. Try again");

        dummyStaticUser.verify(
                () -> User.withUsername(anyString()),
                never()
        );
    }

    @Test
    void secondWillThrowLoadUserByUsername() {

        com.softserve.edu.sporthubujp.entity.User user =
                spy(new com.softserve.edu.sporthubujp.entity.User());

        given(userRepository.findByEmail(anyString()))
                .willReturn(Optional.of(user));

        when(user.getIsActive()).thenReturn(null);

        assertThatThrownBy(() -> underTest.loadUserByUsername(anyString()))
                .isInstanceOf(InternalAuthenticationServiceException.class)
                .hasMessageContaining("Incorrect user ID or password. Try again");

        dummyStaticUser.verify(
                () -> User.withUsername(anyString()),
                never()
        );
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void canLoadUserByUsername() {

        com.softserve.edu.sporthubujp.entity.User user =
                spy(new com.softserve.edu.sporthubujp.entity.User());

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(anyString())
                .password(anyString())
                .authorities(anyString())
                .build();

        given(userRepository.findByEmail(anyString()))
                .willReturn(Optional.of(user));

        when(user.getIsActive()).thenReturn(true);

        dummyStaticUser.when(() -> User
                        .withUsername(anyString())
                        .password(anyString())
                        .authorities(anyString())
                        .build())
                .thenReturn(userDetails);

        underTest.loadUserByUsername(anyString());

        dummyStaticUser.verify(
                () -> User.withUsername(anyString()),
                times(2)
        );
    }
}