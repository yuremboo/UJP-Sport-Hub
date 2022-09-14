package com.softserve.edu.sporthubujp.service.impl;
    import org.springframework.stereotype.Service;

    import com.softserve.edu.sporthubujp.dto.SubscriptionEmailSaveDTO;
    import com.softserve.edu.sporthubujp.exception.EntityNotExistsException;
    import com.softserve.edu.sporthubujp.mapper.SubscriptionEmailMapper;
    import com.softserve.edu.sporthubujp.repository.SubscriptionEmailRepository;
    import com.softserve.edu.sporthubujp.service.SubscriptionEmailService;

    import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubscriptionEmailServiceImpl implements SubscriptionEmailService {

    private static final String COMMENT_NOT_FOUND_BY_ID = "Comment not found by id: %s";
    public static final String ARTICLE_NOT_FOUND_BY_ID = "Article not found by id: %s";
    public static final String USER_NOT_FOUND_BY_ID = "User not found by id: %s";
    public  final SubscriptionEmailMapper  subscriptionEmailMapper;
    public  final SubscriptionEmailRepository subscriptionEmailRepository;

    public SubscriptionEmailServiceImpl(SubscriptionEmailMapper subscriptionEmailMapper, SubscriptionEmailRepository subscriptionEmailRepository) {
        this.subscriptionEmailMapper = subscriptionEmailMapper;
        this.subscriptionEmailRepository = subscriptionEmailRepository;
    }

    @Override
    public SubscriptionEmailSaveDTO addNewEmail(SubscriptionEmailSaveDTO newEmail) {
//        if (!articleRepository.existsById(newComment.getArticleId())) {
//            throw new EntityNotExistsException(String.format(ARTICLE_NOT_FOUND_BY_ID, newComment.getArticleId()));
//        } else if (!userRepository.existsById(newComment.getUserId())) {
//            throw new EntityNotExistsException(String.format(USER_NOT_FOUND_BY_ID, newComment.getUserId()));
//        }
        return subscriptionEmailMapper.entityToDtoSave(
            subscriptionEmailRepository.save(subscriptionEmailMapper.dtoSaveToEntity(newEmail)));
    }
}