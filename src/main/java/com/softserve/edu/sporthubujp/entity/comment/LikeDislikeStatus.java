package com.softserve.edu.sporthubujp.entity.comment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.softserve.edu.sporthubujp.entity.User;

import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "LIKEDISLIKESTATUSES")
public class LikeDislikeStatus {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "liked_disliked", nullable = false)
    private Boolean likedDisliked;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_likes_user"),updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false, foreignKey = @ForeignKey(name = "fk_likes_comment"), updatable = false)
    private Comment comment;
}
