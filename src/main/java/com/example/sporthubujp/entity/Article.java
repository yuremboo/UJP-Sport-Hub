package com.example.sporthubujp.entity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="ARTICLE")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "title", length = 255, nullable = false, unique = false)
    private String title ;
    @Column(name = "text", columnDefinition="TEXT", nullable = false, unique = false)
    private String text ;
    @Column(name = "category_id", length = 16, nullable = false, unique = false)
    private String categoryId  ;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "comments_active")
    private Boolean commentsActive;
    @CreatedDate
    @Column(name="create_date_time",  nullable=false, unique=false)
    private Timestamp createDateTime;
    @LastModifiedDate
    @Column(name="update_date_time",  nullable=false, unique=false)
    private Timestamp updateDateTime;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false,foreignKey = @ForeignKey(name="fk_article_category"), insertable=false, updatable=false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article",cascade = CascadeType.REMOVE)
    private List<Comments> comments;

}