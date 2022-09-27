package com.softserve.edu.sporthubujp.service;

    import java.io.IOException;
    import java.util.List;

    import javax.mail.SendFailedException;

    import com.softserve.edu.sporthubujp.dto.SubscriptionEmailSaveDTO;

public interface SubscriptionEmailService {

    SubscriptionEmailSaveDTO addNewEmail(SubscriptionEmailSaveDTO newEmail);

    void sendUpdateHome() throws IOException, SendFailedException;
}