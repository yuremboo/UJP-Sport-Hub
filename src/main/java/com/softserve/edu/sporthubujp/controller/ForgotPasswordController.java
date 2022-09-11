package com.softserve.edu.sporthubujp.controller;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.service.UserService;
import com.softserve.edu.sporthubujp.service.impl.ForgotPasswordService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.SendFailedException;
import java.io.IOException;
import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1/forgot/password")
@CrossOrigin(origins = "*")
//@AllArgsConstructor
public class ForgotPasswordController {
    private final UserService userService;
    private final ForgotPasswordService forgotPasswordService;

    public ForgotPasswordController(UserService userService, ForgotPasswordService forgotPasswordService) {
        this.userService = userService;
        this.forgotPasswordService = forgotPasswordService;
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<UserDTO> updatePassword2(@NotNull Principal principal,
                                                  @RequestBody String newPassword) throws SendFailedException, IOException {
        User user = userService.findUserByEmail(principal.getName());
        log.info(String.format("Controller: reset user password with user id %s", user.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.resetUserPassword(user, newPassword));
    }

    @PostMapping("/newpassword")
//    /newpassword?password=....&token=....
    public ResponseEntity<UserDTO> updatePassword(@RequestParam String password, @RequestParam String token)
    {
        log.info(String.format("Controller: set new user password with token %s", token));
        return ResponseEntity.status(HttpStatus.OK).body(
                forgotPasswordService.setNewPassword(password, token));
    }

    @PostMapping()
//    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<String> resetPassword(@RequestParam String email)
            throws IOException, SendFailedException {
        log.info(String.format("Controller: confirm user email %s", email));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(forgotPasswordService.resetPassword(email));
    }

}
