package com.paddysAssignment.four.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

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
import com.paddysAssignment.four.model.Item;
import com.paddysAssignment.four.model.Purchase;
import com.paddysAssignment.four.model.Rating;
import com.paddysAssignment.four.model.User;
import com.paddysAssignment.four.repository.BookRepository;
import com.paddysAssignment.four.repository.ItemRepository;
import com.paddysAssignment.four.repository.PurchaseRepository;
import com.paddysAssignment.four.repository.RatingRepository;
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
	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/bookSearch")
	public String bookSearch(ModelMap map) {
		// List<Book> books = bookRepository.findAll();

		ArrayList<String> books = new ArrayList<String>();



		map.addAttribute("books", books);
		return "bookSearch";

	}
	

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
	
	@RequestMapping(value = "/viewBook{id}", method = RequestMethod.GET)
	public String book(@PathVariable(value = "id") Long bookId, @ModelAttribute Book book, Model model) {

		book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
		Rating rating = new Rating();
		
		String title = book.getTitle();
		String author = book.getAuthor();
		String category = book.getCategory();
		double price = book.getPrice();
		int stock = book.getStock();
		
		
		model.addAttribute("bookId", bookId);
		model.addAttribute("book", book);
		model.addAttribute("bookAuthor", author);
		model.addAttribute("bookCategory", category);
		model.addAttribute("bookPrice", price);
		model.addAttribute("bookStock", stock);
		model.addAttribute("bookTitle", title);
		model.addAttribute("rating", rating);
		
		
		return "viewBook"; 
		
	}
	
	

	@PostMapping("/viewBook{id}")
	public String createRating(@PathVariable(value = "id") Long bookId,Book book, ModelMap map,
			@ModelAttribute("Rating") @Valid @RequestBody Rating rating,UserRegistrationController userDto, BindingResult result,  Model model) {
		
		Rating r = new Rating();
		rating.setId(null);

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName();
		
		User user = userRepository.findByEmail(email);

		String bookTitle = rating.getBook();
		
		book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
		

		user.addRating(rating);
		book.addRating(rating);
		
		ratingRepository.save(rating);
		bookRepository.save(book);
		userRepository.save(user);
		
		model.addAttribute("rating",rating.getRating());
		model.addAttribute("comment",rating.getComment());
		model.addAttribute("bookId", book.getId());
		
		return "redirect:/viewBook" + book.getId();

	}
	
	
	
	@GetMapping("/addToCart{id}")
	public String addToCart(@PathVariable(value = "id") Long bookId) {

		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName();
		User user = userRepository.findByEmail(email);
		
		Item item = new Item();
		
		item.setBookId(bookId);
		user.addItem(item);
		
		itemRepository.save(item);
		userRepository.save(user);
		

	return "addCart";
	}
	

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(Model model) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
				
		User user = userRepository.findByEmail(loggedInUser.getName());
		
		ArrayList<Long> bookIds = new ArrayList<Long>();
		ArrayList<String> bookNames = new ArrayList<String>();
		
		for(Item it:user.getItem()) {
			bookIds.add(it.getBookId());
		}

		for (int i = 0; i < bookIds.size(); i++) {
			Long itemId = bookIds.get(i);
			
			Book book = bookRepository.findById(itemId)
					.orElseThrow(() -> new ResourceNotFoundException("Book", "id", itemId));
			
			String bookName = book.getTitle();
			bookNames.add(bookName);
			
		}
		model.addAttribute("bookNames", bookNames);
		
		return "cart";
		
	}
	

	
	@GetMapping("/purchase")
	public String purchase(Model model) {
		double payment = 0;
		String bookTitle = "";

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();			
		User user = userRepository.findByEmail(loggedInUser.getName());
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
			
		ArrayList<Book> books = new ArrayList<Book>();
		
		
		for(Item it:user.getItem()) {
			
			Book book = bookRepository.findById(it.getBookId())
					.orElseThrow(() -> new ResourceNotFoundException("Book", "id", it.getBookId()));
			
			if(book.getStock() > 0){
				book.setStock(book.getStock()-1);
				bookTitle = bookTitle + "x" + book.getTitle();
				
				payment = payment + book.getPrice();	
				System.out.println(payment+"=============================================");
			}	
			
		}
		System.out.println(payment);
		
		Purchase purchase = new Purchase();
		purchase.setBookTitle(bookTitle);
		purchase.setDate(today.getTime().toString());
		purchase.setPayment(payment);

		List<Item> wipe = user.getItem();
		wipe.clear();
		user.setItem(wipe);
		
		user.addPurchase(purchase);
		purchaseRepository.save(purchase);
		userRepository.save(user);
		
		return "cart";
		
	}

	
	@GetMapping("/viewPurchases")
	public String showPurchases(Model model){
		
		Collection<User> users = userRepository.findAll();
		
		
		model.addAttribute("users", users);
	//	model.addAttribute("purchases", user.getPurchase());
		
		
	
		return "viewPurchases";
	}
}




	
