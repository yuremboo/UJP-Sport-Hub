package com.softserve.edu.sporthubujp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

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