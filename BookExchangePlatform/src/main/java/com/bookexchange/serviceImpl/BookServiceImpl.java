package com.bookexchange.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookexchange.entity.Book;
import com.bookexchange.repository.BookRepository;
import com.bookexchange.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book editBook(Book book, long id) {
		Book existingBook = bookRepository.getById(id);
		Book b = new Book();
		b.setId(existingBook.getId());
		if (!existingBook.getAuthor().equals(b.getAuthor())) {
			b.setAuthor(book.getAuthor());
		}
		if (!existingBook.getTitle().equals(b.getTitle())) {
			b.setTitle(book.getTitle());
		}
		if (!existingBook.getGenre().equals(b.getGenre())) {
			b.setGenre(book.getGenre());
		}
		
		return bookRepository.save(book);
	}

	@Override
	public Book getBookById(long id) {
		return bookRepository.getById(id);
	}

	@Override
	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	@Override
	public void delete(long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> getAllBookByUserId(long id) {
		return bookRepository.findAllBookByUserId(id);
	}

}
