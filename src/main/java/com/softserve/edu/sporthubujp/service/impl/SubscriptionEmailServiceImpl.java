package com.softserve.edu.sporthubujp.service.impl;
    import java.io.File;
    import java.io.IOException;
    import java.nio.charset.StandardCharsets;
    import java.time.LocalDateTime;
    import java.time.format.TextStyle;
    import java.util.Locale;

    import javax.mail.SendFailedException;

    import org.springframework.stereotype.Service;

    import com.google.common.io.Files;
    import com.softserve.edu.sporthubujp.dto.SubscriptionEmailSaveDTO;
    import com.softserve.edu.sporthubujp.mapper.SubscriptionEmailMapper;
    import com.softserve.edu.sporthubujp.repository.SubscriptionEmailRepository;
    import com.softserve.edu.sporthubujp.service.EmailSenderService;
    import com.softserve.edu.sporthubujp.service.SubscriptionEmailService;

    import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubscriptionEmailServiceImpl implements SubscriptionEmailService {

    private static final String COMMENT_NOT_FOUND_BY_ID = "Comment not found by id: %s";
    public static final String ARTICLE_NOT_FOUND_BY_ID = "Article not found by id: %s";
    public static final String USER_NOT_FOUND_BY_ID = "User not found by id: %s";
    private final EmailSenderService emailSender;
    public  final SubscriptionEmailMapper  subscriptionEmailMapper;
    public  final SubscriptionEmailRepository subscriptionEmailRepository;
    private final static String EMAIL_SERVER = "sportshubsmtp@gmail.com";

    public SubscriptionEmailServiceImpl(EmailSenderService emailSender, SubscriptionEmailMapper subscriptionEmailMapper, SubscriptionEmailRepository subscriptionEmailRepository) {
        this.emailSender = emailSender;
        this.subscriptionEmailMapper = subscriptionEmailMapper;
        this.subscriptionEmailRepository = subscriptionEmailRepository;
    }

    @Override
    public SubscriptionEmailSaveDTO addNewEmail(SubscriptionEmailSaveDTO newEmail) {
        return subscriptionEmailMapper.entityToDtoSave(
            subscriptionEmailRepository.save(subscriptionEmailMapper.dtoSaveToEntity(newEmail)));
    }
    @Override
    public void sendUpdateHome() throws IOException, SendFailedException {
        String link = "http://localhost:8080/api/v1/teams/";
        emailSender.sendUpdateHome(
            EMAIL_SERVER,
            buildEmail(link));

    }
    private String buildEmail(String link) throws IOException {
        String date = "\n" + LocalDateTime.now().getMonth().getDisplayName(TextStyle.FULL , Locale.US)
            + " " + LocalDateTime.now().getDayOfMonth()
            + ", " + LocalDateTime.now().getYear();

        StringBuilder email = new StringBuilder(Files
            .asCharSource(new File("src/main/resources/templates/updateHomeEmail.html"), StandardCharsets.UTF_8)
            .read());

        email
            .insert(email.indexOf("Hub") + 3, date)
            .insert(email.indexOf("href=\"\"") + 6, link);

        return email.toString();
    }
}