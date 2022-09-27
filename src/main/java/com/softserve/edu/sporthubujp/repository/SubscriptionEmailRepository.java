package com.softserve.edu.sporthubujp.repository;

    import java.util.List;
    import org.springframework.data.jpa.repository.JpaRepository;

    import com.softserve.edu.sporthubujp.entity.SubscriptionEmail;

public interface SubscriptionEmailRepository extends JpaRepository<SubscriptionEmail, String> {
    //List<SubscriptionEmail> findAllByArticleId(String articleId);
}