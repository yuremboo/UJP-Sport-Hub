package entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="CATEGORIES")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name", length = 255, nullable = false, unique = false)
    private String name;
    @Column(name = "description", length = 4000, nullable = false, unique = false)
    private String description;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name="create_date_time",  nullable=false, unique=false)
    private Timestamp createDateTime;
    @Column(name="update_date_time",  nullable=false, unique=false)
    private Timestamp updateDateTime;

    @ManyToOne(optional = false)
    @JoinColumn(name="CATEGORY_ID_TEAMS")
    private Teams teams;

    @OneToMany(mappedBy = "category_id")
    private Set<Article> articles;

    @ManyToOne(optional = false)
    @JoinColumn(name="CATEGORY_ID_SUBCRIPTION")
    private Subscriptions categories_subscriptions;

    @ManyToOne(optional = false)
    @JoinColumn(name="parent_categories_id")
    private Categories parentCategoriesId;


}