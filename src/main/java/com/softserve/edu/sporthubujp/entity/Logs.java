package com.softserve.edu.sporthubujp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="LOGS")
@NoArgsConstructor
public class Logs {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "article_id", length = 255, nullable = false, unique = false)
    private String articleId ;

    @CreatedDate
    @Column(name="create_date_time",  nullable=false, unique=false)
    private LocalDateTime createDateTime;
    public Logs(String articleId)
    {
        this.articleId=articleId;
        this.createDateTime= LocalDateTime.now();
    }
}