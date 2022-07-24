package com.softserve.edu.sporthubujp.entity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="ARTICLE")
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "title", length = 255, nullable = false, unique = false)
    private String title ;
    @Column(name = "text", columnDefinition="TEXT", nullable = false, unique = false)
    private String text ;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "comments_active")
    private Boolean commentsActive;
    @CreatedDate
    @Column(name="create_date_time",  nullable=false, unique=false)
    private LocalDateTime createDateTime;
    @LastModifiedDate
    @Column(name="update_date_time",  nullable=true, unique=false)
    private LocalDateTime updateDateTime;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false,foreignKey = @ForeignKey(name="fk_article_category"), insertable=false, updatable=false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article",cascade = CascadeType.REMOVE)
    private List<Comment> comments;

}