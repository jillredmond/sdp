package com.paddysAssignment.four.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paddysAssignment.four.model.Book;
import com.paddysAssignment.four.repository.BookRepository;
import com.paddysAssignment.four.repository.PurchaseRepository;
import com.paddysAssignment.four.repository.UserRepository;

@Controller
@RequestMapping("/addBook")
public class AddBookController {
	
    @Autowired
    private BookRepository bookRepository;
	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@ModelAttribute("book")
	public Book book() {
		return new Book();
	}
	
	@GetMapping
	public String showBookForm(Model model) {
		return "addBook";
	}
	
	@PostMapping
	public String addNewBook(@ModelAttribute("book") @Valid @RequestBody Book book, BindingResult result,Model model) {
		
		
		model.addAttribute("title", book.getTitle());
		model.addAttribute("author", book.getAuthor());
		model.addAttribute("category", book.getCategory());
		model.addAttribute("stock", book.getStock());
		model.addAttribute("price", book.getPrice());
		bookRepository.save(book);
		
		return "index";
		
	}
	
	

}
