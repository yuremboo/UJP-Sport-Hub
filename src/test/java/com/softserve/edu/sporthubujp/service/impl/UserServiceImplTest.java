package com.softserve.edu.sporthubujp.service.impl;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.dto.UserSavePasswordDTO;
import com.softserve.edu.sporthubujp.dto.UserSaveProfileDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.entity.ConfirmationToken;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.exception.EmailAlreadyTakenException;
import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
import com.softserve.edu.sporthubujp.mapper.UserMapper;
import com.softserve.edu.sporthubujp.repository.UserRepository;
import com.softserve.edu.sporthubujp.security.PasswordConfig;
import com.softserve.edu.sporthubujp.validator.PasswordValidator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import net.snowflake.client.jdbc.internal.google.protobuf.ServiceException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private final static String EMAIL_ALREADY_TAKEN = "Service: email %s already taken";
    BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class, RETURNS_DEEP_STUBS);
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordValidator passwordValidator;
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
        verify(user).setUpdateDateTime(any(LocalDateTime.class));
        verify(userRepository).save(user);

        assertThat(userDTOUnderTest).isEqualTo(userDTO);
    }

    @Test
    void canUpdatePassword() throws ServiceException {
        User user = spy(new User());
        UserSavePasswordDTO userSavePasswordDTO = spy(new UserSavePasswordDTO());
        UserDTO userDTO = spy(new UserDTO());
        BCryptPasswordEncoder bCryptPasswordEncoder = spy(new BCryptPasswordEncoder());

        when(passwordConfig.passwordEncoder()).thenReturn(bCryptPasswordEncoder);

        userSavePasswordDTO.setOldPassword("Password123");
        when(passwordConfig.passwordEncoder()
            .matches(userSavePasswordDTO.getOldPassword(), user.getPassword()))
            .thenReturn(true);

        userSavePasswordDTO.setPassword("Password123");
        when(passwordValidator.test(userSavePasswordDTO.getPassword()))
            .thenReturn(true);

        when(passwordConfig.passwordEncoder()
            .encode(userSavePasswordDTO.getPassword()))
            .thenReturn("Password123");

        given(userRepository.findById(user.getId()))
            .willReturn(Optional.of(user));

        when(userMapper.updatePassword(user, userSavePasswordDTO))
            .thenReturn(user);

        when(userMapper.entityToDto(any()))
            .thenReturn(userDTO);

        UserDTO userDTOUnderTest = underTest.updatePassword(user, userSavePasswordDTO);
        verify(userRepository).save(user);
        assertThat(userDTOUnderTest).isEqualTo(userDTO);
    }

    @Test
    void firstWillThrowWhenUpdatePassword() {
        User user = spy(new User());
        UserSavePasswordDTO userSavePasswordDTO = spy(new UserSavePasswordDTO());
        BCryptPasswordEncoder bCryptPasswordEncoder = spy(new BCryptPasswordEncoder());

        when(passwordConfig.passwordEncoder()).thenReturn(bCryptPasswordEncoder);

        userSavePasswordDTO.setOldPassword("Password123");
        when(passwordConfig.passwordEncoder()
            .matches(userSavePasswordDTO.getOldPassword(), user.getPassword()))
            .thenReturn(false);

        assertThatThrownBy(() -> underTest.updatePassword(user, userSavePasswordDTO))
            .isInstanceOf(ServiceException.class)
            .hasMessage("Service: old password not matches with entered password ");
        verify(userMapper, never()).entityToDto(any(User.class));
    }

    @Test
    void secondWillThrowWhenUpdatePassword() {
        User user = spy(new User());
        UserSavePasswordDTO userSavePasswordDTO = spy(new UserSavePasswordDTO());
        BCryptPasswordEncoder bCryptPasswordEncoder = spy(new BCryptPasswordEncoder());

        when(passwordConfig.passwordEncoder()).thenReturn(bCryptPasswordEncoder);

        userSavePasswordDTO.setOldPassword("Password123");
        when(passwordConfig.passwordEncoder()
            .matches(userSavePasswordDTO.getOldPassword(), user.getPassword()))
            .thenReturn(true);

        userSavePasswordDTO.setPassword("Password123");
        when(passwordValidator.test(userSavePasswordDTO.getPassword()))
            .thenReturn(false);

        assertThatThrownBy(() -> underTest.updatePassword(user, userSavePasswordDTO))
            .isInstanceOf(ServiceException.class)
            .hasMessage("Service: password must contain at least 8 characters (letters and numbers)");
        verify(userMapper, never()).entityToDto(any(User.class));
    }

    @Test
    void thirdWillThrowWhenUpdatePassword() {
        User user = spy(new User());
        UserSavePasswordDTO userSavePasswordDTO = spy(new UserSavePasswordDTO());
        BCryptPasswordEncoder bCryptPasswordEncoder = spy(new BCryptPasswordEncoder());

        when(passwordConfig.passwordEncoder()).thenReturn(bCryptPasswordEncoder);

        userSavePasswordDTO.setOldPassword("Password123");
        when(passwordConfig.passwordEncoder()
            .matches(userSavePasswordDTO.getOldPassword(), user.getPassword()))
            .thenReturn(true);

        userSavePasswordDTO.setPassword("Password123");
        when(passwordValidator.test(userSavePasswordDTO.getPassword()))
            .thenReturn(true);

        when(passwordConfig.passwordEncoder()
            .encode(userSavePasswordDTO.getPassword()))
            .thenReturn("Password123");

        given(userRepository.findById(user.getId()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.updatePassword(user, userSavePasswordDTO))
            .isInstanceOf(EntityNotExistsException.class)
            .hasMessage("Unable to find entity.");
        verify(userMapper, never()).entityToDto(any(User.class));
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
