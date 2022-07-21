package com.example.sporthubujp.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="SUBSCRIPTIONS")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "user_id", length = 16, nullable = false, unique = false)
    private String userId;
    @Column(name = "team_id ", length = 16)
    private String teamId;
    @Column(name = "category_id", length = 16)
    private String categoryId;
    @CreatedDate
    @Column(name="create_date_time",  nullable=false, unique=false)
    private Timestamp createDateTime;
    @LastModifiedDate
    @Column(name="update_date_time",  nullable=false, unique=false)
    private Timestamp updateDateTime;

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