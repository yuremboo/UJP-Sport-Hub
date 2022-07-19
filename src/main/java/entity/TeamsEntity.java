package entity;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="TEAMS")
public class TeamsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "NAME", length = 255, nullable = false, unique = true)
    private String name;
    @Column(name = "LOCATION", length = 255, nullable = false, unique = false)
    private String location;
    @Column(name = "CATEGORY_ID", length = 16, nullable = false, unique = false)
    private String category_id;
    @Column(name = "SUBCATEGORY", length = 255, nullable = false, unique = false)
    private String subcategory;
    @Column(name = "LOGO", nullable = false)
    private Byte logo;
    @Column(name = "DESCRIPTION", length = 255, nullable = false, unique = false)
    private String description;
    @Column(name="CREATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp create_date_time  ;
    @Column(name="UPDATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp  update_date_time;


    @OneToMany( mappedBy = "categories")
    private Set<CategoriesEntity> categories;


    @ManyToOne(optional = false)
    @JoinColumn(name="TEAM_ID")
    private SubscriptionsEntity team_subscriptions;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public Byte getLogo() {
        return logo;
    }

    public void setLogo(Byte logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public String toString() {
        return "TeamsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", category_id='" + category_id + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", logo=" + logo +
                ", description='" + description + '\'' +
                ", create_date_time=" + create_date_time +
                ", update_date_time=" + update_date_time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamsEntity that = (TeamsEntity) o;
        return id.equals(that.id) && name.equals(that.name) && location.equals(that.location) && category_id.equals(that.category_id) && subcategory.equals(that.subcategory) && logo.equals(that.logo) && description.equals(that.description) && create_date_time.equals(that.create_date_time) && Objects.equals(update_date_time, that.update_date_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, category_id, subcategory, logo, description, create_date_time, update_date_time);
    }
}