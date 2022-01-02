package com.oasis.oasis_business.service;

import com.oasis.oasis_business.domain.Book;
import com.oasis.oasis_business.domain.Supply;
import com.oasis.oasis_business.domain.SupplyAndBook;
import com.oasis.oasis_business.dto.SupplyAndBookDto;
import com.oasis.oasis_business.repository.SupplyAndBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SupplyAndBookService {

    private final SupplyAndBookRepository supplyAndBookRepository;


    @Transactional
    public SupplyAndBook createSupplyBook(SupplyAndBookDto supplyAndBookDto) {
        SupplyAndBook supplyAndBook = new SupplyAndBook(supplyAndBookDto);
        supplyAndBookRepository.save(supplyAndBook);
        return supplyAndBook;
    }
}
