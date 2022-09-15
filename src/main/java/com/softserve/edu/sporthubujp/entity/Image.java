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
@Table(name = "IMAGES")
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Lob
    @Column(name = "photo")
    byte[] content;

    @Column(name = "name", length = 255, nullable = false, unique = true)
    @NameConstraint
    String name;
}