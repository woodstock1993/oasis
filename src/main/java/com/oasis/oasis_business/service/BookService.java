package com.oasis.oasis_business.service;

import com.oasis.oasis_business.domain.Book;
import com.oasis.oasis_business.dto.BookRequestDto;
import com.oasis.oasis_business.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(
                ()-> new NullPointerException("도서고유번호가 없습니다."));
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book createBooks(BookRequestDto bookRequestDto) {
        Book book = new Book(bookRequestDto);
        bookRepository.save(book);
        return book;
    }
}
