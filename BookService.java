package com.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dao.BookRepository;
import com.lms.modal.Book;

@Service
public class BookService {

	@Autowired
	private BookRepository bookrepository;
	
	public Book create(Book book){
		
		return bookrepository.save(book);
	}
	
	public List<Book> findAllBook(){
		
		List<Book> books = new ArrayList<>();
		books = bookrepository.findAll();
		return books;
	}
	
	public Book findById(long id){
		
		return bookrepository.findOne(id);
	}
	
	public Book delete(long id){
		
		Book book = bookrepository.findOne(id);
		bookrepository.delete(id);
		return book;
	}
	
}
