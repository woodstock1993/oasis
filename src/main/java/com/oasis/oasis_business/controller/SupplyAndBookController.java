package com.oasis.oasis_business.controller;

import com.oasis.oasis_business.domain.SupplyAndBook;
import com.oasis.oasis_business.service.SupplyAndBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SupplyAndBookController {

    private final SupplyAndBookService supplyAndBookService;

    @GetMapping("/supplybook")
    public List<SupplyAndBook> getSupplyBook() {
        return supplyAndBookService.getSupplyBook();
    }

    @PostMapping("/supplybook/{id1}/{id2}")
    public SupplyAndBook createSupplyBook(@PathVariable Long id1, @PathVariable Long id2) {

        return supplyAndBookService.createSupplyBook(id1, id2);
    }
}
