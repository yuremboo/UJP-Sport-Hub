package com.softserve.edu.sporthubujp.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.softserve.edu.sporthubujp.validator.EmailConstraint;
import com.softserve.edu.sporthubujp.validator.NameConstraint;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.softserve.edu.sporthubujp.entity.comment.Comment;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="USERS", indexes = {
        @Index(columnList = "email", name = "user_email_idx")
})
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="first_name", length=255, nullable=false, unique=false)
    @NameConstraint
    private String firstName;

    @Column(name="last_name", length=255, nullable=false, unique=false)
    @NameConstraint
    private String lastName;

    @Column(name="email", length=255, nullable=false, unique=true)
    @EmailConstraint
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="role", length=10, nullable=false, unique=false)
    private Role role;

    @Column(name="password", length=255, nullable=false, unique=false)
    private String password;

    @Column(name="photo", length=255, nullable=true, unique=false)
    private String photo;

    @Column(name="is_active")
    private Boolean isActive;

    @CreatedDate
    @Column(name="create_date_time",  nullable=false, unique=false)
    private LocalDateTime createDateTime;
    @LastModifiedDate
    @Column(name="update_date_time",  nullable=true, unique=false)
    private LocalDateTime updateDateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    private List<Subscription> subscriptions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;

    @Column(name="password_reset_token")
    private String passwordResetToken;
}