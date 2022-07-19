package entity;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="ARTICLE")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "TITLE", length = 255, nullable = false, unique = false)
    private String title ;
    @Column(name = "TEXT", length = 4000, nullable = false, unique = false)
    private String text ;
    @Column(name = "CATEGORY_ID", length = 16, nullable = false, unique = false)
    private String category_id  ;
    @Column(name = "IS_ACTIVE")
    private Boolean is_active;
    @Column(name = "COMENTS_ACTIVE")
    private Boolean comments_active ;
    @Column(name="CREATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp create_date_time  ;
    @Column(name="UPDATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp  update_date_time;

    @OneToMany(mappedBy = "categories_article")
    private Set<CategoriesEntity> categories;

    @OneToOne(mappedBy = "comment_acticle")
    @PrimaryKeyJoinColumn
    private CommentsEntity comments;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Boolean getComments_active() {
        return comments_active;
    }

    public void setComments_active(Boolean comments_active) {
        this.comments_active = comments_active;
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
        return "ArticleEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", category_id='" + category_id + '\'' +
                ", is_active=" + is_active +
                ", comments_active=" + comments_active +
                ", create_date_time=" + create_date_time +
                ", update_date_time=" + update_date_time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return id.equals(that.id) && title.equals(that.title) && text.equals(that.text) && category_id.equals(that.category_id) && Objects.equals(is_active, that.is_active) && Objects.equals(comments_active, that.comments_active) && create_date_time.equals(that.create_date_time) && Objects.equals(update_date_time, that.update_date_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, category_id, is_active, comments_active, create_date_time, update_date_time);
    }
}