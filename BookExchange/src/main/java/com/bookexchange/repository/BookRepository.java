package com.bookexchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookexchange.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findAllBookByUserId(long id);
	
	List<Book> findAllBooksByTitle(String title);

}
