package com.softserve.edu.sporthubujp.entity.comment;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.entity.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "comment_text", length = 255, nullable = false, unique = false)
    private String commentText;
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
    @JoinColumn(name = "commenter_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comments_user"), updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comments_article"), updatable = false)
    private Article article;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<LikeDislikeStatus> likeDislikeStatuses;

}