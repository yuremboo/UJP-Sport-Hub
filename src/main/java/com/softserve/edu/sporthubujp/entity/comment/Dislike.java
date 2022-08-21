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
@Table(name = "DISLIKES")
public class Dislike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "is_disliked")
    private Boolean isDisliked;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_dislikes_user"), insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false, foreignKey = @ForeignKey(name = "fk_dislikes_comment"), insertable = false, updatable = false)
    private Comment comment;
}
