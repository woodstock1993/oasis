package com.oasis.oasis_business.controller;

import com.oasis.oasis_business.domain.Book;
import com.oasis.oasis_business.domain.Supply;
import com.oasis.oasis_business.dto.SupplyAndBookDto;
import com.oasis.oasis_business.service.SupplyAndBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SupplyAndBookController {

    private final SupplyAndBookService supplyAndBookService;

    @PostMapping("/supplybook")
    public void createSupplyBook(@RequestBody SupplyAndBookDto supplyAndBookDto) {
    }
}
