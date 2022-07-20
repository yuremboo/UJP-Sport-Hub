package entity;

import lombok.*;

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
@Table(name="SUBSCRIPTIONS")
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_id", length = 16, nullable = false, unique = false)
    private String userId;
    @Column(name = "team_id ", length = 16)
    private String teamId;
    @Column(name = "category_id", length = 16)
    private String categoryId;
    @Column(name="create_date_time",  nullable=false, unique=false)
    private Timestamp createDateTime;
    @Column(name="update_date_time",  nullable=false, unique=false)
    private Timestamp updateDateTime;

    @OneToMany( mappedBy = "subscriptions")
            private Set<User> users;

    @OneToMany( mappedBy = "team_subscriptions")
    private Set<Teams> teams;

    @OneToMany( mappedBy = "categories_subscriptions")
    private Set<Categories> categories;


}