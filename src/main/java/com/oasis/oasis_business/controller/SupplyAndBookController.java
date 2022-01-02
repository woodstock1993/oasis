package com.oasis.oasis_business.controller;

import com.oasis.oasis_business.domain.SupplyAndBook;
import com.oasis.oasis_business.dto.SupplyAndBookDto;
import com.oasis.oasis_business.service.SupplyAndBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SupplyAndBookController {

    private final SupplyAndBookService supplyAndBookService;

    @PostMapping("/supplybook")
    public SupplyAndBook createSupplyBook(@RequestBody SupplyAndBookDto supplyAndBookDto) {
        System.out.println(supplyAndBookDto);
        System.out.println(supplyAndBookDto.getBook().getId());
        System.out.println(supplyAndBookDto.getSupply().getId());
        return supplyAndBookService.createSupplyBook(supplyAndBookDto);
    }
}
