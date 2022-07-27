package com.softserve.edu.sporthubujp.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="CATEGORIES")
@NoArgsConstructor
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
    private LocalDateTime createDateTime;
    @LastModifiedDate
    @Column(name="update_date_time",  nullable=true, unique=false)
    private LocalDateTime updateDateTime;
    @ManyToOne(fetch = FetchType.LAZY, optional=true)
    @JoinColumn(name="parent_category_id")
    private Category parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    @EqualsAndHashCode.Exclude
    private Set<Category> children = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category",cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    private List<Article> articles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category",cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    private List<Team> teams;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category",cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    private List<Subscription> subscriptions;
}