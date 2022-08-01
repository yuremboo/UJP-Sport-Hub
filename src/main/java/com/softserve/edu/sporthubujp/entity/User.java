package com.softserve.edu.sporthubujp.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="USERS")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name="first_name", length=255, nullable=false, unique=false)
    private String firstName;
    @Column(name="last_name", length=255, nullable=false, unique=false)
    private String lastName;
    @Column(name="email", length=255, nullable=false, unique=false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name="role", length=10, nullable=false, unique=false)
    private Role role;
    @Column(name="password", length=255, nullable=false, unique=false)
    private String password;
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
}