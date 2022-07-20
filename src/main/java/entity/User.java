package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;
import java.sql.Timestamp;
@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @Column(name="first_name", length=255, nullable=false, unique=false)
    private String firstName;
    @Column(name="last_name", length=255, nullable=false, unique=false)
    private String lastName;
    @Column(name="email", length=255, nullable=false, unique=false)
    private String email;
    //@Column(name="role", length=10, nullable=false, unique=false)
    //private String role;
    private enum Role{ADMIN,USER};
    @Column(name="password", length=255, nullable=false, unique=false)
    private String password;
    @Column(name="is_active")
    private Boolean isActive;
    @Column(name="create_date_time",  nullable=false, unique=false)
    private Timestamp createDateTime;
    @Column(name="update_date_time",  nullable=false, unique=false)
    private Timestamp updateDateTime;

    @ManyToOne(optional = false)
    @JoinColumn(name="USER_ID")
    private Subscriptions subscriptions;

    @OneToMany( mappedBy = "comments")
    private Set<Comments> comments;

}