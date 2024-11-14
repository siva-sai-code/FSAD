package com.bookexchange.service;

import java.util.List;

import com.bookexchange.entity.Book;

public interface BookService {

	Book saveBook(Book book);
	
	Book editBook(Book book, long id);
	
	Book getBookById(long id);
	
	List<Book> getAll();
	
	void delete(long id);
	
	List<Book> getAllBookByUserId(long id);
}
