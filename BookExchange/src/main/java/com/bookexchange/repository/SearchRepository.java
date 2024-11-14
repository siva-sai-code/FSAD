package com.bookexchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookexchange.entity.Book;

@Repository
public interface SearchRepository extends JpaRepository<Book, Long> {

	// Query for filtering by title, author, and availability status
	   @Query("SELECT b FROM Book b WHERE " +
	           "(:title IS NULL OR b.title LIKE %:title%) AND " +
	           "(:author IS NULL OR b.author LIKE %:author%) AND " +
	           "(:genre IS NULL OR b.genre = :genre) AND " +
	           "(:location IS NULL OR b.location LIKE %:location%) AND " +
	           "(:available IS NULL OR b.available = :available)")
	   List<Book> searchBooks(@Param("title") String title,
	                          @Param("author") String author,
	                          @Param("genre") String genre,
	                          @Param("location") String location,
	                          @Param("available") String available);
}
