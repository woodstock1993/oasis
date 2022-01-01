package com.oasis.oasis_business.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String bookGenre;

    @Column(nullable = false)
    private Long bookQuantity;

    @Column(nullable = false)
    private Long bookSupplyPrice;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String bookPublishedDate;

    @Column(nullable = false)
    private Long bookFullPrice;

    @Column(nullable = false)
    private Long appliedDcRate;

    @ManyToMany(mappedBy="books")
    private List<Supply> supplies = new ArrayList<>();
}
