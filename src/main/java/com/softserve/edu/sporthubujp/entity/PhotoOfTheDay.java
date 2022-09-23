package com.softserve.edu.sporthubujp.entity;

import com.softserve.edu.sporthubujp.validator.NameConstraint;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PhotoOfTheDay {

    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
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

    @Column(name = "picture", length = 255, nullable = false, unique = false)
    private String picture;

}