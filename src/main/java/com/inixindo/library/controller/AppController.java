package com.inixindo.library.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.inixindo.library.config.CustomAccountDetails;
import com.inixindo.library.model.Authors;
import com.inixindo.library.model.Books;
import com.inixindo.library.model.Borrower;
import com.inixindo.library.model.Loans;
import com.inixindo.library.model.Publisher;
import com.inixindo.library.repository.BorrowerRepository;
import com.inixindo.library.repository.LoansRepository;
import com.inixindo.library.services.AuthorService;
import com.inixindo.library.services.BookService;
import com.inixindo.library.services.BorrowerService;
import com.inixindo.library.services.LoansService;
import com.inixindo.library.services.PublisherService;

@Controller
public class AppController {

	@Autowired
	BookService bookService;
	@Autowired
	PublisherService publisherService;
	@Autowired
	AuthorService authorService;
	@Autowired
	BorrowerService borrowerService;
	@Autowired
	LoansService loansService;
	@Autowired
	BorrowerRepository borrowerRepository;
	@Autowired
	LoansRepository loansRepository;

//	@GetMapping("/login")
//	public String login(Model model) {
//		model.addAttribute("borrower", new Borrower());
//		return "login";
//	}

	@PostMapping("/")
	public String masuk() {
		return "template";
	}

	@RequestMapping("/default")
	public String RoleController(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		authorities = auth.getAuthorities();
		String myUsername = authorities.toArray()[0].toString();
		String admin = "LIBRARIAN";

		if (myUsername.equals(admin)) {
			return "redirect:/book";
		} else {
			return "redirect:/user_dashboard";
		}

	}

	@RequestMapping("/user_dashboard")
	public String userDashboard(Model model, Authentication authentication) {


		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(currentUsername);
		
		Borrower borrower = borrowerRepository.findByUsername(currentUsername);
		
		int cardNo = borrower.getCardNo();
		
		List<Loans> listLoans = loansService.getLoansByCardNo(cardNo);
	
		System.out.println(listLoans); 
		
		model.addAttribute("username", currentUsername);
		model.addAttribute("listLoans", listLoans);


		return "user_dashboard";

	}

//	@RequestMapping("/book")
//	public String getBookPage(Model model) {
//		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
//		System.out.println(currentUsername);
//		
//		List<Books> listBooks = bookService.listAll();
//		model.addAttribute("username", currentUsername);
//		model.addAttribute("listBooks", listBooks);
//		System.out.println(listBooks);
//		return "template_all_book";
//
//	}
	
	@RequestMapping("/book")
	public String getBookPage(Model model,  @Param("keyword") String keyword) {
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(currentUsername);
		
		List<Books> listBooks = bookService.listAllSearch(keyword);
		model.addAttribute("username", currentUsername);
		model.addAttribute("listBooks", listBooks);
		model.addAttribute("keyword", keyword);
		System.out.println(listBooks);
		return "template_all_book";

	}
	
	@RequestMapping("/all_book_user")
	public String getBookPageUser(Model model, @Param("keyword") String keyword) {
//		List<Books> listBooks = bookService.listAll();
//		model.addAttribute("listBooks", listBooks);
//		System.out.println(listBooks);
//		return "all_book_user";

		List<Books> listBooks = bookService.listAllSearch(keyword);
		model.addAttribute("listBooks", listBooks);
		model.addAttribute("keyword", keyword);
		System.out.println(listBooks);
		return "all_book_user";
	}
	
	@RequestMapping("/user_history")
	public String getBookUserHistory(Model model) {
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(currentUsername);
		
		Borrower borrower = borrowerRepository.findByUsername(currentUsername);
		
		int cardNo = borrower.getCardNo();
		List<Loans> listCompletedLoans = loansService.getHistoryLoan(cardNo);
		model.addAttribute("username", currentUsername);
		model.addAttribute("listLoans", listCompletedLoans);
		System.out.println(listCompletedLoans);
		return "user_history";

	}


	@RequestMapping("/loans")
	public String getLoansPage(Model model) {
		List<Loans> listLoans = loansService.getLoans();
		model.addAttribute("listLoans", listLoans);
		System.out.println(listLoans);
		return "list_loans";

	}
	
	@RequestMapping("/return_book/{book_id}/{loan_id}")
	public String returnBook(@PathVariable(name = "book_id") int book_id, @PathVariable(name = "loan_id") int loan_id) {
		/* System.out.println(id); */
		System.out.println(book_id);
		System.out.println(loan_id);
		bookService.returnStock(book_id);
		loansService.bookReturned(loan_id);
		return "redirect:/loans";
	}
	
	

	@RequestMapping("/book/new")
	public String newBookPage(Model model) {
		List<Publisher> listPublishers = publisherService.listAll();
		List<Authors> listAuthors = authorService.listAll();
		System.out.println(listPublishers);
		
		Books books = new Books();
		model.addAttribute("books", books);
		model.addAttribute("publishers", listPublishers);
		model.addAttribute("authors", listAuthors);
		return "new_book";
	}

	@RequestMapping(value = "/save_book", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("books") Books books, Errors errors) {
		bookService.save(books);
		System.out.println(books);
		return "redirect:/book";
	}

	@RequestMapping("/loans/new")
	public String newLoansPage(Model model) {
		List<Loans> listLoans = loansService.listAll();
		List<Books> listBooks = bookService.getBookAvailable();
		List<Borrower> listBorrower = borrowerService.getBorrowerNewBook();

		System.out.println(listBorrower);
		Loans loans = new Loans();
		model.addAttribute("loans", loans);
		model.addAttribute("books", listBooks);
		model.addAttribute("borrowers", listBorrower);
		return "new_loan";
	}

	@RequestMapping(value = "/save_loan", method = RequestMethod.POST)
	public String saveLoan(@ModelAttribute("loans") Loans loans, Errors errors) {
		int book_id = loans.books.getBookID();
		bookService.minStock(book_id);
		loansService.save(loans);
		System.out.println(loans);
		return "redirect:/loans";
	}

	@RequestMapping("/book/newPublisher")
	public String newPublisherPage(Model model) {
		Publisher publisher = new Publisher();
		model.addAttribute("publisher", publisher);
		return "new_publisher";
	}

	@RequestMapping(value = "/save_publisher", method = RequestMethod.POST)
	public String savePublisher(@ModelAttribute("publisher") Publisher publisher) {
		publisherService.save(publisher);
		System.out.println(publisher);
		return "redirect:/book";
	}

	@RequestMapping("/book/newAuthor")
	public String newAuthorPage(Model model) {
		Authors authors = new Authors();
		model.addAttribute("authors", authors);
		return "new_author";
	}

	@RequestMapping(value = "/save_author", method = RequestMethod.POST)
	public String saveAuthor(@ModelAttribute("publisher") Authors authors) {
		authorService.save(authors);
		System.out.println(authors);
		return "redirect:/book";
	}

	@RequestMapping("/delete_book/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		System.out.println(id);
		bookService.delete(id);
		return "redirect:/book";
	}

	@RequestMapping("/edit_book/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_book");
		List<Publisher> listPublishers = publisherService.listAll();
		List<Authors> listAuthors = authorService.listAll();
		Books books = bookService.get(id);
		mav.addObject("books", books);
		mav.addObject("publishers", listPublishers);
		mav.addObject("authors", listAuthors);
		return mav;
	}

	@RequestMapping("/")
	public String newBorrowerPage(Model model) {
		Borrower borrower = new Borrower();
		model.addAttribute("borrower", borrower);
		List<Books> listBooks = bookService.listAll();
		model.addAttribute("listBooks", listBooks);
		System.out.println(listBooks);
		return "template";
	}

	@RequestMapping(value = "/save_borrower", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("borrower") Borrower borrower, Errors errors) {
		if (null != errors && errors.getErrorCount() > 0) {
			return "new_borrower";
		} else {
			borrowerService.save(borrower);
			System.out.println(borrower);
			return "redirect:/";
		}
	}
}
