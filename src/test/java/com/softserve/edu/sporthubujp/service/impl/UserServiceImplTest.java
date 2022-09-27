package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.exception.EmailAlreadyTakenException;
import com.softserve.edu.sporthubujp.mapper.UserMapper;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.security.PasswordConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private final static String EMAIL_ALREADY_TAKEN = "Service: email %s already taken";
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordConfig passwordConfig;
    @Mock
    private UserMapper userMapper;
    @Mock
    private ConfirmationTokenService confirmationTokenService;
    @InjectMocks
    private UserServiceImpl underTest;

    @Test
    void willThrowEmailAlreadyTakenException() {

        UserDTO userDTO = spy(new UserDTO());
        User user = spy(new User());

        BCryptPasswordEncoder bCryptPasswordEncoder = spy(new BCryptPasswordEncoder());

        when(userMapper.dtoToEntity(userDTO))
                .thenReturn(user);

        when(passwordConfig.passwordEncoder()).thenReturn(bCryptPasswordEncoder);

        given(userRepository.findByEmail(userDTO.getEmail()))
                .willReturn(Optional.of(user));

        assertThatThrownBy(() -> underTest.signUpUser(userDTO))
                .isInstanceOf(EmailAlreadyTakenException.class)
                .hasMessageContaining(String.format(EMAIL_ALREADY_TAKEN, userDTO.getEmail()));

        verify(passwordConfig.passwordEncoder(), never()).encode(anyString());
    }

    @Test
    void signUpUser() {

        UserDTO userDTO = spy(new UserDTO());
        User user = spy(new User());

        BCryptPasswordEncoder bCryptPasswordEncoder = spy(new BCryptPasswordEncoder());

        when(userMapper.dtoToEntity(userDTO))
                .thenReturn(user);

        when(passwordConfig.passwordEncoder()).thenReturn(bCryptPasswordEncoder);

        given(userRepository.findByEmail(userDTO.getEmail()))
                .willReturn(Optional.empty());

        when(userDTO.getPassword()).thenReturn("nonEmptyPassword");

        underTest.signUpUser(userDTO);

        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository)
                .save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);

        verify(passwordConfig.passwordEncoder()).encode(anyString());

        verify(user).setPassword(anyString());
        verify(user).setCreateDateTime(any(LocalDateTime.class));

        verify(confirmationTokenService)
                .saveConfirmationToken(any(ConfirmationToken.class));
    }

    @Test
    void canEnableUser() {

        underTest.enableUser(anyString());

        verify(userRepository).enableUser(anyString());
    }

    @Test
    @Disabled
    void canFindUserByEmail() {

    }

    //
    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void canUpdateUser() {
        UserDTO userDTO = spy(new UserDTO());
        User user = spy(new User());
        UserSaveProfileDTO userSaveProfileDTO = spy(new UserSaveProfileDTO());

        given(userRepository.findByEmail(userSaveProfileDTO.getEmail()))
            .willReturn(Optional.of(user));

        when(userMapper.updateUser(user, userSaveProfileDTO))
            .thenReturn(user);

        when(userMapper.entityToDto(any()))
            .thenReturn(userDTO);

        UserDTO userDTOUnderTest = underTest.updateUser(user, userSaveProfileDTO);
        verify(userRepository).save(user);

        Assertions.assertThat(userDTOUnderTest).isEqualTo(userDTO);
    }

    @Test
    void canGetUser() {
        User user = spy(new User());
        UserDTO userDTO = spy(new UserDTO());

        given(userRepository.getReferenceById(user.getId()))
            .willReturn(user);

        when(userMapper.entityToDto(any(User.class)))
            .thenReturn(userDTO);

        UserDTO userDTOUnderTest = underTest.getUser(user);
        verify(userMapper, times(1)).entityToDto(any(User.class));
        assertThat(userDTOUnderTest).isEqualTo(userDTO);
    }
}
