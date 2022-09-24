package com.softserve.edu.sporthubujp.entity;

import com.softserve.edu.sporthubujp.validator.NameConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="PHOTO_OF_THE_DAY")
@NoArgsConstructor
public class PhotoOfTheDay {

    @Id
    private String id;

    @Column(name = "alt", length = 255, nullable = false, unique = false)
    @NameConstraint
    private String alt;

    @Column(name = "author", length = 255, nullable = false, unique = false)
    @NameConstraint
    private String author;

    @Column(name = "title", length = 255, nullable = false, unique = false)
    @NameConstraint
    private String title;

    @Column(name = "short_description", length = 255, nullable = false, unique = false)
    @NameConstraint
    private String shortDescription;

}