package com.oasis.oasis_business.domain;

import com.oasis.oasis_business.repository.SupplyAndBookRepository;
import lombok.*;

import javax.persistence.*;

@ToString(callSuper = true)
@Setter
@Getter
@RequiredArgsConstructor
@Entity
public class SupplyAndBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Supply supply;

    @ManyToOne
    private Book book;

     public SupplyAndBook(Supply supply, Book book) {
        SupplyAndBook supplyAndBook = new SupplyAndBook();
        supplyAndBook.setSupply(supply);
        supplyAndBook.setBook(book);
    }
}
