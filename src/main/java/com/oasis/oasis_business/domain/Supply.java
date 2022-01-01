package com.oasis.oasis_business.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oasis.oasis_business.dto.SupplyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
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

    @ManyToMany
    private List<Book> books = new ArrayList<>();

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
}
