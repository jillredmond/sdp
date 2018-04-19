package com.paddysAssignment.four.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.paddysAssignment.four.exception.ResourceNotFoundException;
import com.paddysAssignment.four.model.Book;
import com.paddysAssignment.four.repository.BookRepository;
import com.paddysAssignment.four.repository.PurchaseRepository;
import com.paddysAssignment.four.repository.UserRepository;

//@RestController
//@RequestMapping
@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;
	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@GetMapping("/bookSearch")
	public String bookSearch(ModelMap map) {
		// List<Book> books = bookRepository.findAll();

		ArrayList<String> books = new ArrayList<String>();



		map.addAttribute("books", books);
		return "bookSearch";

	}
	@RequestMapping(value = "/viewBook{id}", method = RequestMethod.GET)
	public String book(@PathVariable String id) {
		
		
		
		return "viewBook"; 
		
	}
	
	//@RequestMapping(value="/bookSearch", method=RequestMethod.GET)
	@PostMapping("/bookSearch")
	public String bookSearchPost(ModelMap map, @ModelAttribute Book book, Model model) {
		List<Book> books = bookRepository.findAll();
	//	ArrayList<String> books = new ArrayList<String>();

		if(book.getCategory()!="") {
			for (int i = 0; i < books.size(); i++) {
				if(books.get(i).getCategory().toLowerCase().equals((book.getCategory().toLowerCase()))) {
					
					Long bookId = books.get(i).getId();
					String bookTitle = books.get(i).getTitle();
					model.addAttribute("bookId", bookId);
					model.addAttribute("bookTitle", bookTitle);
					
					
				} else {
					
				}
			}
		}
		
		if (book.getAuthor() != "") {

			for (int i = 0; i < books.size(); i++) {

				if (books.get(i).getAuthor().toLowerCase().equals((book.getAuthor().toLowerCase()))) {
					Long bookId = books.get(i).getId();
					String bookTitle = books.get(i).getTitle();
					model.addAttribute("bookId", bookId);
					model.addAttribute("bookTitle", bookTitle);

				} else {

				}

			}

	
		}

		else if (book.getTitle() != "") {

			for (int i = 0; i < books.size(); i++) {

				if (books.get(i).getTitle().toLowerCase().contains((book.getTitle().toLowerCase()))) {
					Long bookId = books.get(i).getId();
					String bookTitle = books.get(i).getTitle();
					model.addAttribute("bookId", bookId);
					model.addAttribute("bookTitle", bookTitle);
				} else {

				}

			}

		} else {
			for (int i = 0; i < books.size(); i++) {

				if (books.get(i).getCategory().equals(book.getCategory())) {
					Long bookId = books.get(i).getId();
					String bookTitle = books.get(i).getTitle();
					model.addAttribute("bookId", bookId);
					model.addAttribute("bookTitle", bookTitle);
				} else {

				}

			}
		}

		map.addAttribute("books", books);

		return "bookSearch";

	}
	
	 // Get All Books
//  @GetMapping("/books")
//  public List<Book> getAllBooks(){
//      return bookRepository.findAll();
//  }
	
//	
//	@ModelAttribute("book")
//	public Book book() {
//		return new Book();
//	}
	
//	@GetMapping("/addbook")
//			public String showBookForm(Model model) {
//				return "addBook";
//			}
	
//	@PostMapping("/addBook")
//	public String addNewBook(@ModelAttribute("book") @Valid @RequestBody Book book, BindingResult result,Model model) {
//		
//		bookRepository.save(book);
//		model.addAttribute("bookTitle", book.getTitle());
//		
//		return "addBook";
//		
//	}
	
    
// // Get All Books
//    @GetMapping("/books")
//    public List<Book> getAllBooks(){
//        return bookRepository.findAll();
//    }
//    
// // Create a new Book
//    @PostMapping("/books")
//    public Book createBook(@Valid @RequestBody Book book) {
//        return bookRepository.save(book);
//    }
//    
// // Get a Single Book
//    @GetMapping("/books/{id}")
//    public Book getBookById(@PathVariable(value = "id") Long bookId) {
//        return bookRepository.findById(bookId)
//                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
//    }
//    
// // Update a Book
//    @PutMapping("/books/{id}")
//    public Book updateBook(@PathVariable(value = "id") Long bookId,
//                                            @Valid @RequestBody Book bookDetails) {
//
//    	Book book = bookRepository.findById(bookId)
//                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
//
//    	book.setTitle(bookDetails.getTitle());
//    	book.setAuthor(bookDetails.getAuthor());
//    	book.setCategory(bookDetails.getCategory());
//    	book.setPrice(bookDetails.getPrice());
//
//        Book updatedNote = bookRepository.save(book);
//        return updatedNote;
//    }
//    
// // Delete a Book
//    @DeleteMapping("/books/{id}")
//    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) {
//    	Book book = bookRepository.findById(bookId)
//                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
//
//    	bookRepository.delete(book);
//
//        return ResponseEntity.ok().build();
//    }


}