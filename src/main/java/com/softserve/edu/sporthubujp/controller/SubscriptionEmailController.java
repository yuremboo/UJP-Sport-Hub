package com.softserve.edu.sporthubujp.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import com.softserve.edu.sporthubujp.dto.SubscriptionEmailSaveDTO;
    import com.softserve.edu.sporthubujp.service.CommentService;
    import com.softserve.edu.sporthubujp.service.SubscriptionEmailService;

    import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class SubscriptionEmailController {
    private final SubscriptionEmailService subscriptionEmailService;

    @Autowired
    public SubscriptionEmailController(SubscriptionEmailService subscriptionEmailService) {
        this.subscriptionEmailService = subscriptionEmailService;
    }

    @PostMapping("/newEmail")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<SubscriptionEmailSaveDTO> addNewEmailSubscription(@RequestBody SubscriptionEmailSaveDTO newEmail) {
        log.info(String.format("Add new email to subscription %s", newEmail));
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionEmailService.addNewEmail(newEmail));
    }
}