package com.bookstore.DAL;

import java.util.*;

import com.bookstore.model.item.Book;

public class BookCollectionDAO {
	
	private static List<Book> bookCollection = GenerateBookCollection();
	
	private static List<Book> GenerateBookCollection()
	{
		List<Book> books = new ArrayList<Book>();
		
		Book book = new Book();
		book.setId("11111");
		book.setISBN("ISBN11111");
		book.setAuthor("Author11111");
		book.setTitle("Title11111");
		book.setPrice(20.46);
		
		books.add(book);
		
		
		book = new Book();
		book.setId("11112");
		book.setISBN("ISBN11112");
		book.setAuthor("Author11112");
		book.setTitle("Title11112");
		book.setPrice(19.99);
		
		books.add(book);
		
		book = new Book();
		book.setId("11113");
		book.setISBN("ISBN11113");
		book.setAuthor("Author11113");
		book.setTitle("Title11113");
		book.setPrice(26.74);
		
		books.add(book);
		
		
		book = new Book();
		book.setId("11114");
		book.setISBN("ISBN11114");
		book.setAuthor("Author11114");
		book.setTitle("Title11114");
		book.setPrice(43.16);
		
		books.add(book);
		return books;
	}
	
	public Book getBookByID(String id)
	{
		for(int i = 0; i< bookCollection.size(); i++)
		{
			if(bookCollection.get(i).getId().toLowerCase().equals(id.toLowerCase()))
				return bookCollection.get(i);
		}
		return null;
	}
	
	public Book getBookByISBN(String isbn)
	{
		for(int i = 0; i< bookCollection.size(); i++)
		{
			if(bookCollection.get(i).getISBN().toLowerCase().equals(isbn.toLowerCase()))
				return bookCollection.get(i);
		}
		return null;
	}
	
	public ArrayList<Book> searchBookByISBN(String isbn)
	{
		ArrayList<Book> list = new ArrayList<Book>();
		for(int i = 0; i< bookCollection.size(); i++)
		{
			if(bookCollection.get(i).getISBN().toLowerCase().contains(isbn.toLowerCase()))
				list.add(bookCollection.get(i));
		}
		return list;
	}
	
	public ArrayList<Book> searchBookByTitle(String title)
	{
		ArrayList<Book> list = new ArrayList<Book>();
		for(int i = 0; i< bookCollection.size(); i++)
		{
			if(bookCollection.get(i).getTitle().toLowerCase().contains(title.toLowerCase()))
				list.add(bookCollection.get(i));
		}
		return list;
	}
	
	public ArrayList<Book> searchBookByAuthor(String author)
	{
		ArrayList<Book> list = new ArrayList<Book>();
		for(int i = 0; i< bookCollection.size(); i++)
		{
			if(bookCollection.get(i).getAuthor().toLowerCase().contains(author.toLowerCase()))
				list.add(bookCollection.get(i));
		}
		return list;
	}

}
