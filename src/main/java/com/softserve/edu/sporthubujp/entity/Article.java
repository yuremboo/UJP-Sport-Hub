package com.softserve.edu.sporthubujp.entity;
import lombok.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.softserve.edu.sporthubujp.entity.comment.Comment;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="ARTICLES")
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "title", length = 255, nullable = false, unique = false)
    private String title ;
    @Column(columnDefinition="text", name = "text", nullable = false, unique = false)
    private String text ;
    @Column(name = "caption", length = 255, nullable = false, unique = false)
    private String caption;
    @Column(name = "alt", length = 255, nullable = false, unique = false)
    private String alt;
    @Column(name = "location", length = 255, nullable = false, unique = false)
    private String location;
    @Column(name = "picture", length = 255, nullable = false, unique = false)
    private String picture;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Column(name = "comments_active")
    private Boolean commentsActive;
    @Column(name = "selected_by_admin", nullable = false)
    private Boolean selectedByAdmin;
    @CreatedDate
    @Column(name="create_date_time",  nullable=false, unique=false)
    private LocalDateTime createDateTime;
    @LastModifiedDate
    @Column(name="update_date_time",  nullable=true, unique=false)
    private LocalDateTime updateDateTime;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false,foreignKey = @ForeignKey(name="fk_article_category"), insertable=false, updatable=false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false,foreignKey = @ForeignKey(name="fk_article_team"), insertable=false, updatable=false)
    private Team team;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;

}