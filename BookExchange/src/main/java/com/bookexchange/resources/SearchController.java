package com.bookexchange.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookexchange.entity.Book;
import com.bookexchange.service.BookService;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/home")
	public String search() {
		return "searchbooks";
	}

	@GetMapping("/searchBooks")
    public String searchBooks(@RequestParam(name = "title", required = false) String title,
    						  @RequestParam(name = "author", required = false) String author,
                              @RequestParam(name = "genre", required = false) String genre,
                              @RequestParam(name = "location", required = false) String location,
                              @RequestParam(name = "available", required = false) String available,
                              Model model) {

		if (available.equalsIgnoreCase("true")) {
			available = "yes";
		} else {
			available = "no";
		}
        // Get books based on search criteria
        List<Book> books = bookService.searchBooks(title, author, genre, location, available);

        // Add search results to the model
        model.addAttribute("books", books);
        return "searchbooks";
    }
}
