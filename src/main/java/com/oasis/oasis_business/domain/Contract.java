package com.oasis.oasis_business.domain;

import com.oasis.oasis_business.dto.ContractRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Contract extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String contractCompany;

    @Column(nullable = false)
    private String contractDate;

    @Column(nullable = false)
    private String minPriceRatio;

    @Column(nullable = false)
    private String contractCode;

    @OneToMany
    private List<Supply> supplies;

    public Contract(ContractRequestDto contractRequestDto) {
        this.contractDate = contractRequestDto.getContractDate();
        this.minPriceRatio = contractRequestDto.getMinPriceRatio();
        this.contractCode = contractRequestDto.getContractCode();
        this.contractCompany = contractRequestDto.getContractCompany();
    }

    public void updateContract(ContractRequestDto contractRequestDto) {
        this.contractDate = contractRequestDto.getContractDate();
        this.minPriceRatio = contractRequestDto.getMinPriceRatio();
        this.contractCode = contractRequestDto.getContractCode();
        this.contractCompany = contractRequestDto.getContractCompany();
    }
}
