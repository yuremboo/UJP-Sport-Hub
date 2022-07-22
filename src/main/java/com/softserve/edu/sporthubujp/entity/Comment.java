package com.softserve.edu.sporthubujp.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "comment", length = 255, nullable = false, unique = false)
    private String comment;
    @CreatedDate
    @Column(name = "create_date_time", nullable = false, unique = false)
    private LocalDateTime createDateTime;
    @LastModifiedDate
    @Column(name = "update_date_time", nullable = true, unique = false)
    private LocalDateTime updateDateTime;
    @Column(name = "likes", nullable = true, unique = false)
    private Integer likes;
    @Column(name = "dislikes", nullable = true, unique = false)
    private Integer dislikes;
    @ManyToOne
    @JoinColumn(name = "commenter_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comments_user"), insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comments_article"), insertable = false, updatable = false)
    private Article article;

}