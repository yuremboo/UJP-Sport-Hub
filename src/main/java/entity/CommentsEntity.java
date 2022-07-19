package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="COMMENTS")
public class CommentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "COMMENT", length = 255, nullable = false, unique = false)
    private String comment  ;
    @Column(name = "COMMENTER_ID", length = 16, nullable = false, unique = false)
    private String commenter_id   ;

    @OneToMany( mappedBy = "comments")
    private Set<UserEntity> users;

    @OneToOne
    @MapsId
    @JoinColumn(name="comment_acticle")
    private ArticleEntity comment_acticle;

    @Column(name = "ARTICLE_ID ", length = 16, nullable = false, unique = false)
    private String article_id   ;
    @Column(name="CREATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp create_date_time  ;
    @Column(name="UPDATE_DATA_TIME",  nullable=false, unique=false)
    private Timestamp  update_date_time   ;
    private Integer likes ;
    private Integer dislikes  ;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommenter_id() {
        return commenter_id;
    }

    public void setCommenter_id(String commenter_id) {
        this.commenter_id = commenter_id;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
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
        return "CommentsEntity{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", commenter_id='" + commenter_id + '\'' +
                ", article_id='" + article_id + '\'' +
                ", create_date_time=" + create_date_time +
                ", update_date_time=" + update_date_time +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentsEntity that = (CommentsEntity) o;
        return id.equals(that.id) && comment.equals(that.comment) && commenter_id.equals(that.commenter_id) && article_id.equals(that.article_id) && create_date_time.equals(that.create_date_time) && Objects.equals(update_date_time, that.update_date_time) && Objects.equals(likes, that.likes) && Objects.equals(dislikes, that.dislikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, commenter_id, article_id, create_date_time, update_date_time, likes, dislikes);
    }
}