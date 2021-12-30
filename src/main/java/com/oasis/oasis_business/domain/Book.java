package com.oasis.oasis_business.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private String bookSection;

    @Column(nullable = false)
    private Long bookNumber;

    @Column(nullable = false)
    private Long bookSupplyPrice;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String bookPublishDate;

    @Column(nullable = false)
    private Long bookPrice;

    @Column(nullable = false)
    private Long dcRate;
}
