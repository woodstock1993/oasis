package com.oasis.oasis_business.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class SupplyCompany extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String contractDate;

    @Column(nullable = false)
    private Long minPriceRatio;

    @Column(nullable = false)
    private String contractStatusCode;
}
