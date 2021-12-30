package com.oasis.oasis_business.repository;

import com.oasis.oasis_business.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
