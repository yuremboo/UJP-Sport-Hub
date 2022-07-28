package com.softserve.edu.sporthubujp.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="SUBSCRIPTIONS")
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @CreatedDate
    @Column(name="create_date_time",  nullable=false, unique=false)
    private LocalDateTime createDateTime;
    @LastModifiedDate
    @Column(name="update_date_time",  nullable=true, unique=false)
    private LocalDateTime updateDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,foreignKey = @ForeignKey(name="fk_subscriptions_user"), insertable=false, updatable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id",foreignKey = @ForeignKey(name="fk_subscriptions_team"), insertable=false, updatable=false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "category_id",foreignKey = @ForeignKey(name="fk_subscriptions_category"), insertable=false, updatable=false)
    private Category category;
}