package com.bookexchange.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookexchange.configuration.CustomUserDetails;
import com.bookexchange.entity.Book;
import com.bookexchange.entity.User;
import com.bookexchange.service.BookService;
import com.bookexchange.service.UserService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String register() {
		return "registerbook";
	}
	
	@PostMapping("/registerBook")
	public String addBook(Model model, Book book) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		User user = new User(userDetails.getUsername(), userDetails.getPassword(), userDetails.getFullname());
		User user = userService.findByEmail(userDetails.getUsername());
		book.setUserId(user.getId());
		book.setAvailable("yes");
		book.setBookCondition("New");
		Book saveBook = bookService.saveBook(book);
		model.addAttribute("SavedBook", saveBook);
		return "redirect:/book/register?success";
	}
	
	@GetMapping("/getBookById")
	public String getBookById(Model model, Long id) {
		Book bookById = bookService.getBookById(id);
		model.addAttribute("Book", bookById);
		return "";
	}
	
	@GetMapping("/getAllBooksByUser")
	public String getAll(Model model) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByEmail(userDetails.getUsername());
		List<Book> books = bookService.getAllBooksByUserId(user.getId());
		model.addAttribute("books", books);
		return "viewmybooks";
	}
	
	@GetMapping("/getAllBooks")
	public String getAllBooks(Model model) {
		List<Book> allBooks = bookService.getAllBooks();
		model.addAttribute("books", allBooks);
		return "viewallbooks";
	}
	
	@PutMapping("/editBook")
	public String editBook(Model model, long id) {
		Book editBook = null;
		Book bookById = bookService.getBookById(id);
		if (bookById != null) {
			editBook = bookService.editBook(bookById, id);
		}
		model.addAttribute("editBook", editBook);
		return "";
	}
	
	@DeleteMapping("/delete")
	public String delete(Model model, long id) {
		bookService.delete(id);
		return "redirect:/book/getAll";
	}
	
	@GetMapping("/viewBooks")
	public String viewBooks(Model model) {
		return getAll(model);
	}

}
