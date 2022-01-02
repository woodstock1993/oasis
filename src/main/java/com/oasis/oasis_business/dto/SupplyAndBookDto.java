package com.oasis.oasis_business.dto;

import com.oasis.oasis_business.domain.Book;
import com.oasis.oasis_business.domain.Supply;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SupplyAndBookDto {
    private Supply supply;
    private Book book;
}
