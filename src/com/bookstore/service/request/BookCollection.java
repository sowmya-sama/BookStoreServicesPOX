package com.bookstore.service.request;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.item.Book;

public class BookCollection {
	private List<Book> bookList = new ArrayList<Book>();
	
	public List<Book> getBooks()
	{
		return bookList;
	}
	public void setBooks(List<Book> books)
	{
		bookList = books;
	}

}
