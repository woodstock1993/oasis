package com.oasis.oasis_business.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oasis.oasis_business.dto.BookRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Book extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String bookGenre;

    @Column(nullable = false)
    private String bookQuantity;

    @Column(nullable = false)
    private String bookSupplyPrice;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String bookPublishedDate;

    @Column(nullable = false)
    private String bookFullPrice;

    @Column(nullable = false)
    private String appliedDcRate;


    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    @JsonIgnore
    private List<SupplyAndBook> supplyAndBook = new ArrayList<>(); // null 방지

    public Book(BookRequestDto bookRequestDto) {
        this.bookTitle = bookRequestDto.getBookTitle();
        this.bookGenre = bookRequestDto.getBookGenre();
        this.bookQuantity = bookRequestDto.getBookQuantity();
        this.bookSupplyPrice = bookRequestDto.getBookSupplyPrice();
        this.author = bookRequestDto.getAuthor();
        this.bookPublishedDate = bookRequestDto.getBookPublishedDate();
        this.bookFullPrice = bookRequestDto.getBookFullPrice();
        this.appliedDcRate = bookRequestDto.getAppliedDcRate();
    }

    public Book(String bookTitle, String bookGenre, String bookQuantity, String bookSupplyPrice, String author, String bookPublishedDate, String bookFullPrice, String appliedDcRate) {
        this.bookTitle = bookTitle;
        this.bookGenre = bookGenre;
        this.bookQuantity = bookQuantity;
        this.bookSupplyPrice = bookSupplyPrice;
        this.author = author;
        this.bookPublishedDate = bookPublishedDate;
        this.bookFullPrice = bookFullPrice;
        this.appliedDcRate = appliedDcRate;
    }

    public void addSupplyAndBook(SupplyAndBook... supplyAndBook) {
        Collections.addAll(this.supplyAndBook, supplyAndBook);
    }

    public void updateBooks(BookRequestDto bookRequestDto) {
        this.bookTitle = bookRequestDto.getBookTitle();
        this.bookGenre = bookRequestDto.getBookGenre();
        this.bookQuantity = bookRequestDto.getBookQuantity();
        this.bookSupplyPrice = bookRequestDto.getBookSupplyPrice();
        this.author = bookRequestDto.getAuthor();
        this.bookPublishedDate = bookRequestDto.getBookPublishedDate();
        this.bookFullPrice = bookRequestDto.getBookFullPrice();
        this.appliedDcRate = bookRequestDto.getAppliedDcRate();
    }
}
