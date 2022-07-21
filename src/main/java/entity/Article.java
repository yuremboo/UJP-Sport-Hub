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
@Table(name="ARTICLE")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title", length = 255, nullable = false, unique = false)
    private String title ;
    @Column(name = "text", columnDefinition="TEXT", nullable = false, unique = false)
    private String text ;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "comments_active")
    private Boolean commentsActive;
    @Column(name="create_date_time",  nullable=false, unique=false)
    private Timestamp createDateTime;
    @Column(name="update_date_time",  nullable=false, unique=false)
    private Timestamp updateDateTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false, unique = false)
    private Categories categoryId;

    @OneToMany(mappedBy = "comment_acticle")
    @PrimaryKeyJoinColumn
    private Comments comments;

}