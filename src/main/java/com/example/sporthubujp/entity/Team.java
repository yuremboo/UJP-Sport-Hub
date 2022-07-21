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
@Table(name="TEAMS")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "name", length = 255, nullable = false, unique = true)
    private String name;
    @Column(name = "location", length = 255, nullable = false, unique = false)
    private String location;
    @Column(name = "category_id", length = 16, nullable = false, unique = false)
    private String categoryId;
    @Column(name = "subcategory", length = 255, nullable = false, unique = false)
    private String subcategory;
    @Column(name = "logo", nullable = false)
    private Byte logo;
    @Column(name = "description", length = 255, nullable = false, unique = false)
    private String description;
    @CreatedDate
    @Column(name="create_date_time",  nullable=false, unique=false)
    private Timestamp createDateTime;
    @LastModifiedDate
    @Column(name="update_date_time",  nullable=false, unique=false)
    private Timestamp updateDateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team",cascade = CascadeType.REMOVE)
    private List<Subscription> subscriptions;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false,foreignKey = @ForeignKey(name="fk_team_category"), insertable=false, updatable=false)
    private Category category;
}