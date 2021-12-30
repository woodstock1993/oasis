package com.oasis.oasis_business.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ContractRequestDto {
    private String contractDate;
    private Long minPriceRatio;
    private String contractCode;
}
