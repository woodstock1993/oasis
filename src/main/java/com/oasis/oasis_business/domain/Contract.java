package com.oasis.oasis_business.domain;

import com.oasis.oasis_business.dto.ContractRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Setter
@Getter
@Entity
public class Contract extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String contractDate;

    @Column(nullable = false)
    private Long minPriceRatio;

    @Column(nullable = false)
    private String contractCode;

    public Contract(ContractRequestDto contractRequestDto) {
        this.contractDate = contractRequestDto.getContractDate();
        this.minPriceRatio = contractRequestDto.getMinPriceRatio();
        this.contractCode = contractRequestDto.getContractCode();
    }

    public Contract() {

    }
}
