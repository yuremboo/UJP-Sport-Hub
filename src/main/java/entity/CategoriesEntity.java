package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="CATEGORIES")
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "NAME", length = 255, nullable = false, unique = false)
    private String name;
    @Column(name = "DESCRIPTION", length = 4000, nullable = false, unique = false)
    private String description;
    @Column(name = "IS_ACTIVE")
    private Boolean is_active;
    @Column(name="CREATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp create_date_time  ;
    @Column(name="UPDATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp  update_date_time;
    @Column(name = "PARENT_CATEGORIES_ID", length = 255)
    private String parent_categories_id;

    @ManyToOne(optional = false)
    @JoinColumn(name="CATEGORY_ID_TEAMS")
    private TeamsEntity teams;

    @ManyToOne(optional = false)
    @JoinColumn(name="CATEGORY_ID_ARTICLE")
    private ArticleEntity categories_article;

    @ManyToOne(optional = false)
    @JoinColumn(name="CATEGORY_ID_SUBCRIPTION")
    private SubscriptionsEntity categories_subscriptions;

    @ManyToOne(optional = false)
    @JoinColumn(name="CATEGORY_IN_CATEGORY")
    private CategoriesEntity categories;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getParent_categories_id() {
        return parent_categories_id;
    }

    public void setParent_categories_id(String parent_categories_id) {
        this.parent_categories_id = parent_categories_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
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
        CategoriesEntity that = (CategoriesEntity) o;
        return id.equals(that.id) && name.equals(that.name) && description.equals(that.description) && Objects.equals(is_active, that.is_active) && create_date_time.equals(that.create_date_time) && Objects.equals(update_date_time, that.update_date_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, is_active, create_date_time, update_date_time);
    }

    @Override
    public String toString() {
        return "CategoriesEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", is_active=" + is_active +
                ", create_date_time=" + create_date_time +
                ", update_date_time=" + update_date_time +
                '}';
    }
}