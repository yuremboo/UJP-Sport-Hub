package entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="COMMENTS")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "comment", length = 255, nullable = false, unique = false)
    private String comment  ;
    @Column(name = "commenter_id", length = 16, nullable = false, unique = false)
    private String commenterId;
    @Column(name = "article_id ", length = 16, nullable = false, unique = false)
    private String articleId;
    @Column(name="create_date_time",  nullable=false, unique=false)
    private Timestamp createDateTime;
    @Column(name="update_date_time",  nullable=false, unique=false)
    private Timestamp updateDateTime;
    private Integer likes ;
    private Integer dislikes  ;

    @ManyToOne(optional = false)
    @JoinColumn(name="commenter_id")
    private User users;

    @OneToOne
    @MapsId
    @JoinColumn(name="comment_acticle")
    private Article comment_acticle;
}