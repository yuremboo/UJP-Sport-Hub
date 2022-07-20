package entity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Table(name="TEAMS")
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
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
    @Column(name="create_date_time",  nullable=false, unique=false)
    private Timestamp createDateTime;
    @Column(name="update_date_time",  nullable=false, unique=false)
    private Timestamp updateDateTime;

    @OneToMany( mappedBy = "categories")
    private Set<Categories> categories;


    @ManyToOne(optional = false)
    @JoinColumn(name="TEAM_ID")
    private Subscriptions team_subscriptions;


}