package com.softserve.edu.sporthubujp.service;

    import java.util.List;

    import com.softserve.edu.sporthubujp.dto.SubscriptionEmailSaveDTO;

public interface SubscriptionEmailService {

    SubscriptionEmailSaveDTO addNewEmail(SubscriptionEmailSaveDTO newEmail);

}