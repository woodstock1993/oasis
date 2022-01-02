package com.oasis.oasis_business.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 파라미터가 없는 생성자를 생성
@Getter
public class BookRequestDto {

    private String bookTitle;

    private String bookGenre;

    private String bookQuantity;

    private String bookSupplyPrice;

    private String author;

    private String bookPublishedDate;

    private String bookFullPrice;

    private String appliedDcRate;
}
