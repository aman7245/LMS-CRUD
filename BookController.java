package com.lms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.modal.Book;
import com.lms.service.BookService;

@RequestMapping(value="/lms")
@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

	@GetMapping(value = "/findAllBook")
	public String home(Model model){
		
		model.addAttribute("books", bookService.findAllBook());
		model.addAttribute("mode","BOOK_VIEW");
		return "index";
	}
	
	@GetMapping(value = "/updateBook")
	public String updateBook(Model model, @RequestParam long id){
		
		model.addAttribute("book", bookService.findById(id));
		model.addAttribute("mode","BOOK_EDIT");
		return "index";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
	
	@PostMapping("/save")
	public String save(@ModelAttribute Book book, Model model){
		
		bookService.create(book);
		model.addAttribute("books", bookService.findAllBook());
		model.addAttribute("mode","BOOK_VIEW");
		return "redirect:/lms/findAllBook";
	}
	
	@GetMapping("/newBook")
	public String createNew(@ModelAttribute Book book, Model model){
		
		model.addAttribute("mode","BOOK_NEW");
		return "index";
	}
	
	@GetMapping(value = "/deleteBook")
	public String deleteBook(Model model, @RequestParam long id){
		
		bookService.delete(id);
		return "redirect:/lms/findAllBook";
	}
	
}
