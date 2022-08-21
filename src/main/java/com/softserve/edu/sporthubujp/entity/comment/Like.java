package com.softserve.edu.sporthubujp.entity.comment;

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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.entity.comment.Comment;

import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "is_liked")
    private Boolean isLiked;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_likes_user"), insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false, foreignKey = @ForeignKey(name = "fk_likes_comment"), insertable = false, updatable = false)
    private Comment comment;
}
