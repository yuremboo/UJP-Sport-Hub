package entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;
import java.lang.Object;
import java.util.Date;
import java.sql.Timestamp;
@Entity
@Table(name="USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @Column(name="FIRST_NAME", length=255, nullable=false, unique=false)
    private String first_name ;
    @Column(name="LAST_NAME", length=255, nullable=false, unique=false)
    private String last_name  ;

    @Column(name="EMAIL", length=255, nullable=false, unique=false)
    private String email;

    @Column(name="ROLE", length=10, nullable=false, unique=false)
    private String role;

    @Column(name="PASSWORD", length=255, nullable=false, unique=false)
    private String password;

    @Column(name="IS_ACTIVE")
    private Boolean is_active ;

    @ManyToOne(optional = false)
    @JoinColumn(name="USER_ID")
    private SubscriptionsEntity subscriptions;

    @ManyToOne(optional = false)
    @JoinColumn(name="COMMENTER_ID")
    private CommentsEntity Comments;

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) && first_name.equals(that.first_name) && last_name.equals(that.last_name) && email.equals(that.email) && role.equals(that.role) && password.equals(that.password) && is_active.equals(that.is_active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, email, role, password, is_active);
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", is_active=" + is_active +
                ", Subscriptions=" + subscriptions +
                ", Comments=" + Comments +
                ", create_date_time=" + create_date_time +
                ", update_date_time=" + update_date_time +
                '}';
    }

    @Column(name="CREATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp  create_date_time  ;
    @Column(name="UPDATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp  update_date_time   ;

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
}