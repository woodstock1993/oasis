package com.oasis.oasis_business.service;

import com.oasis.oasis_business.domain.Book;
import com.oasis.oasis_business.domain.Supply;
import com.oasis.oasis_business.domain.SupplyAndBook;
import com.oasis.oasis_business.dto.SupplyAndBookDto;
import com.oasis.oasis_business.repository.BookRepository;
import com.oasis.oasis_business.repository.SupplyAndBookRepository;
import com.oasis.oasis_business.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SupplyAndBookService {

    private final BookRepository bookRepository;
    private final SupplyRepository supplyRepository;
    private final SupplyAndBookRepository supplyAndBookRepository;


    public List<SupplyAndBook> getSupplyBook() {
        return supplyAndBookRepository.findAll();
    }

    @Transactional
    public SupplyAndBook createSupplyBook(Long id1, Long id2) {
        Supply supply1 = supplyRepository.findById(id1).orElseThrow(
                ()-> new NullPointerException("공급 아이디가 없습니다."));
        Book book1 = bookRepository.findById(id2).orElseThrow(
                ()-> new NullPointerException("도서번호가 없습니다."));

        SupplyAndBook supplyAndBook = new SupplyAndBook(supply1, book1);
        supplyAndBookRepository.saveAndFlush(supplyAndBook);
        return supplyAndBook;
    }
}
