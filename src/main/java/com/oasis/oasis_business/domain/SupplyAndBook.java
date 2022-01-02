package com.oasis.oasis_business.domain;

import com.oasis.oasis_business.dto.SupplyAndBookDto;
import lombok.*;

import javax.persistence.*;

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

     public SupplyAndBook(SupplyAndBookDto supplyAndBookDto) {
        this.supply = supplyAndBookDto.getSupply();
        this.book = supplyAndBookDto.getBook();
    }
}
