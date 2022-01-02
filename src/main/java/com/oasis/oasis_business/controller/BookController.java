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

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable Long id) {
        Book book = bookService.getBook(id);
        return book;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/book")
    public Book createBooks(@RequestBody BookRequestDto bookRequestDto) {
        Book book = bookService.createBooks(bookRequestDto);
        return book;
    }

    @PutMapping("book/{id}")
    public Long updateBooks(@PathVariable Long id, @RequestBody BookRequestDto bookRequestDto) {
        bookService.updateBooks(id, bookRequestDto);
        return id;
    }

    @DeleteMapping("/book/{id}")
    public Long deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return id;
    }
}
