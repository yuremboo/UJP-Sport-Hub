package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="SUBSCRIPTIONS")
public class SubscriptionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "USER_ID", length = 16, nullable = false, unique = false)
    private String user_id ;
    @Column(name = "TEAM_ID ", length = 16)
    private String team_id ;
    @Column(name = "CATEGORY_ID", length = 16)
    private String category_id;
    @Column(name="CREATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp create_date_time  ;
    @Column(name="UPDATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp  update_date_time;

    @OneToMany( mappedBy = "subscriptions")
            private Set<UserEntity> users;

    @OneToMany( mappedBy = "team_subscriptions")
    private Set<TeamsEntity> teams;

    @OneToMany( mappedBy = "categories_subscriptions")
    private Set<CategoriesEntity> categories;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
    public Timestamp getCreate_date_time() {
        return create_date_time;
    }

    public void setCreate_date_time(Timestamp create_date_time) {
        this.create_date_time = create_date_time;
    }

    public Timestamp getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(Timestamp update_date_time) {
        this.update_date_time = update_date_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionsEntity that = (SubscriptionsEntity) o;
        return id.equals(that.id) && user_id.equals(that.user_id) && Objects.equals(team_id, that.team_id) && Objects.equals(category_id, that.category_id) && create_date_time.equals(that.create_date_time) && Objects.equals(update_date_time, that.update_date_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, team_id, category_id, create_date_time, update_date_time);
    }

    @Override
    public String toString() {
        return "SubscriptionsEntity{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", team_id='" + team_id + '\'' +
                ", category_id='" + category_id + '\'' +
                ", create_date_time=" + create_date_time +
                ", update_date_time=" + update_date_time +
                '}';
    }
}