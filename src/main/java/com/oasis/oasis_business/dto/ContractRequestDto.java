package com.oasis.oasis_business.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ContractRequestDto {
    private String contractCompany;
    private String contractDate;
    private String minPriceRatio;
    private String contractCode;
}
