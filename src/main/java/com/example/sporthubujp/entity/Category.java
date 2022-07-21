package com.example.sporthubujp.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="CATEGORIES")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "name", length = 255, nullable = false, unique = false)
    private String name;
    @Column(name = "description", length = 4000, nullable = false, unique = false)
    private String description;
    @Column(name = "is_active")
    private Boolean isActive;
    @CreatedDate
    @Column(name="create_date_time",  nullable=false, unique=false)
    private Timestamp createDateTime;
    @LastModifiedDate
    @Column(name="update_date_time", unique=false)
    private Timestamp updateDateTime;
    @Column(name = "parent_categories_id", length = 255)
    private String parentCategoriesId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category",cascade = CascadeType.REMOVE)
    private List<Article> articles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category",cascade = CascadeType.REMOVE)
    private List<Team> teams;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category",cascade = CascadeType.REMOVE)
    private List<Subscription> subscriptions;
}