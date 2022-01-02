package com.oasis.oasis_business.domain;

import lombok.*;

import javax.persistence.*;

@ToString(callSuper = true)
@Setter
@Getter
@NoArgsConstructor // 인자없는 생성자 필요
@Entity
public class SupplyAndBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Supply supply;

    @ManyToOne
    private Book book;
}
