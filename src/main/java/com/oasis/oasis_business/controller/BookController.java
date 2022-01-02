package com.oasis.oasis_business.controller;

import com.oasis.oasis_business.domain.Book;
import com.oasis.oasis_business.dto.BookRequestDto;
import com.oasis.oasis_business.repository.BookRepository;
import com.oasis.oasis_business.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/books")
    public Book createBooks(@RequestBody BookRequestDto bookRequestDto) {
        Book book = bookService.createBooks(bookRequestDto);
        return book;
    }

    @DeleteMapping("/book/{id}")
    public Long deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return id;
    }
}
