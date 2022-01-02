package com.oasis.oasis_business.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oasis.oasis_business.dto.SupplyRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Supply extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String supplyDate;

    @JsonIgnore
    @JoinColumn
    @ManyToOne
    private Contract contract;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="supply_id")
    @ToString.Exclude
    private List<SupplyAndBook> SupplyAndBook = new ArrayList<>();

    public Supply(SupplyRequestDto supplyRequestDto) {
        this.supplyDate = supplyRequestDto.getSupplyDate();
    }

    // 생성자를 통한 연관관계 설정
    public Supply(SupplyRequestDto supplyRequestDto, Contract contract) {
        this.supplyDate = supplyRequestDto.getSupplyDate();
        this.contract = contract;
    }

    public void updateSupply(SupplyRequestDto supplyRequestDto) {
        this.supplyDate = supplyRequestDto.getSupplyDate();
    }

    public void addSupplyAndBook(SupplyAndBook... supplyAndBook) {
        Collections.addAll(this.SupplyAndBook, supplyAndBook);
    }
}
