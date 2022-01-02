package com.oasis.oasis_business.repository;

import com.oasis.oasis_business.domain.Book;
import com.oasis.oasis_business.domain.Supply;
import com.oasis.oasis_business.domain.SupplyAndBook;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class SupplyRepositoryTest {

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SupplyAndBookRepository supplyAndBookRepository;

    @Test
    @Transactional
    void manyToManyTest() {
        Book book1 = givenBook("어린왕자1","동화책1","10(1)권","5000원","생쥐작가1","어린이날출판1","만원1","20프로할인1");
        Book book2 = givenBook("어린왕자2","동화책2","10(2)권","500원","생쥐작가2","어린이날출판2","만원2","20프로할인2");
        Book book3 = givenBook("어린왕자3","동화책3","10(3)권","50원","생쥐작가3","어린이날출판3","만원3","20프로할인3");
        Book book4 = givenBook("어린왕자4","동화책4","10(4)권","5원","생쥐작가4","어린이날출판4","만원4","20프로할인4");

        Supply supply1 = givenSupply("2022_임인년");
        Supply supply2 = givenSupply("크리스마스");

        SupplyAndBook supplyAndBook1 = givenSupplyAndBook(supply1, book1);
        SupplyAndBook supplyAndBook2 = givenSupplyAndBook(supply2, book2);
        SupplyAndBook supplyAndBook3 = givenSupplyAndBook(supply1, book3);
        SupplyAndBook supplyAndBook4 = givenSupplyAndBook(supply2, book3);
        SupplyAndBook supplyAndBook5 = givenSupplyAndBook(supply1, book4);
        SupplyAndBook supplyAndBook6 = givenSupplyAndBook(supply2, book4);


        book1.addSupplyAndBook(supplyAndBook1);
        book2.addSupplyAndBook(supplyAndBook2);
        book3.addSupplyAndBook(supplyAndBook3,supplyAndBook4);
        book4.addSupplyAndBook(supplyAndBook5,supplyAndBook6);

        supply1.addSupplyAndBook(supplyAndBook1,supplyAndBook3, supplyAndBook5);
        supply2.addSupplyAndBook(supplyAndBook2,supplyAndBook4, supplyAndBook6);

        bookRepository.saveAll(Lists.newArrayList(book1,book2,book3,book4));
        supplyRepository.saveAll(Lists.newArrayList(supply1,supply2));

        bookRepository.findAll().get(2).getSupplyAndBook().forEach(o-> System.out.println(o.getSupply().getSupplyDate()));
        supplyRepository.findAll().get(0).getSupplyAndBook().forEach(o-> System.out.println(o.getBook().getBookTitle()));
    }

    private Supply givenSupply(String supplyDate) {
        Supply supply = new Supply();
        supply.setSupplyDate(supplyDate);
        return supplyRepository.save(supply);
    }

    private Book givenBook(String a1, String a2,String a3,String a4,String a5,String a6,String a7,String a8) {
        Book book = new Book(a1,a2,a3,a4,a5,a6,a7,a8);
        return bookRepository.save(book);
    }

    private SupplyAndBook givenSupplyAndBook(Supply supply, Book book) {
        SupplyAndBook supplyAndBook = new SupplyAndBook();
        supplyAndBook.setSupply(supply);
        supplyAndBook.setBook(book);

        return supplyAndBookRepository.save(supplyAndBook);
    }
}