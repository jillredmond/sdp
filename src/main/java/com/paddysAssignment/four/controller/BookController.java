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
import org.springframework.web.bind.annotation.RestController;


import com.paddysAssignment.four.exception.ResourceNotFoundException;
import com.paddysAssignment.four.model.Book;
import com.paddysAssignment.four.repository.BookRepository;
import com.paddysAssignment.four.repository.PurchaseRepository;
import com.paddysAssignment.four.repository.UserRepository;

//@RestController
//@RequestMapping("/api")
@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;
	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@GetMapping("/allBooks")
	public String showBooks(@ModelAttribute("book") @Valid Model model) {
		
		
		return "allBooks";

		}
	

	@PostMapping("/book/search")
	public String bookSearchPost(ModelMap map, @ModelAttribute Category category) {
		List<Book> books = bookRepository.findAll();
		System.out.println("=================================");
		System.out.println(category.getOrder());
		System.out.println(category.getAuthor());
		System.out.println(category.getTitle());
		System.out.println("=================================");
		ArrayList<String> categories = new ArrayList<String>();
		ArrayList<String> links = new ArrayList<String>();

		categories.add("Science Fiction");
		categories.add("Romance");
		categories.add("Travel");
		categories.add("Science");
		categories.add("History");
		categories.add("Other");
		
		if (category.getAuthor() != "") {

			for (int i = 0; i < books.size(); i++) {

				if (books.get(i).getAuthor().toLowerCase().contains((category.getAuthor().toLowerCase()))) {
					String link = "";

					System.out.println("=================================");
					System.out.println("TEST");

					link = "<a href=\"http://localhost:8080/book/" + books.get(i).getId() + "\"> "
							+ books.get(i).getTitle() + "</a>" + " by " + books.get(i).getAuthor();
					links.add(link);
				} else {

				}

			}

			//this was my attempt to sort the results in ascending order, I did not have time to get it working. 
/*			boolean swap = true;
			while (swap == true) {
				for (int i = 0; i < links.size()-1; i++) {
	
					swap = false;
					
					if (Character.toLowerCase(links.get(i).charAt(links.get(i).indexOf('<') + 2)) > Character
							.toLowerCase(links.get(i + 1).charAt(links.get(i).indexOf('<') + 2)))
						;

					String toMove = links.get(i);
					links.set(i, links.get(i + 1));
					links.set(i + 1, toMove);
					swap = true;
					}
				}

			
*/
		}

		else if (category.getTitle() != "") {

			for (int i = 0; i < books.size(); i++) {

				if (books.get(i).getTitle().toLowerCase().contains((category.getTitle().toLowerCase()))) {
					String link = "";

					System.out.println("=================================");
					System.out.println("TEST");

					link = "<a href=\"http://localhost:8080/book/" + books.get(i).getId() + "\"> "
							+ books.get(i).getTitle() + "</a>" + " by " + books.get(i).getAuthor();
					links.add(link);
				} else {

				}

			}

		} else {
			for (int i = 0; i < books.size(); i++) {

				if (books.get(i).getCategory().equals(category.getCategory())) {
					String link = "";

					System.out.println("=================================");
					System.out.println("TEST");

					link = "<a href=\"http://localhost:8080/book/" + books.get(i).getId() + "\"> "
							+ books.get(i).getTitle() + "</a>" + " by " + books.get(i).getAuthor();
					links.add(link);
				} else {

				}

			}
		}
		
		
		
		System.out.println("=================================");
		System.out.println(books.toString());

		map.addAttribute("categories", categories);
		map.addAttribute("links", links);
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