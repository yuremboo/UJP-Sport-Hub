package com.softserve.edu.sporthubujp.entity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="TEAMS")
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "name", length = 255, nullable = false, unique = true)
    private String name;
    @Column(name = "alt", length = 255, nullable = false, unique = false)
    private String alt;
    @Column(name = "location", length = 255, nullable = false, unique = false)
    private String location;
    @Column(name = "logo", nullable = false)
    private Byte logo;
    @Column(name = "description", length = 255, nullable = false, unique = false)
    private String description;
    @CreatedDate
    @Column(name="create_date_time",  nullable=false, unique=false)
    private LocalDateTime createDateTime;
    @LastModifiedDate
    @Column(name="update_date_time",  nullable=true, unique=false)
    private LocalDateTime updateDateTime;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false,foreignKey = @ForeignKey(name="fk_team_category"), insertable=false, updatable=false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team",cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    private List<Subscription> subscriptions;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team",cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    private List<Article> article;
}