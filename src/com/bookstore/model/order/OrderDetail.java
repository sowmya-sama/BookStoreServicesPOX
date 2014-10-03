package com.bookstore.model.order;


import com.bookstore.model.item.Book;

public class OrderDetail {
	private Book book;
	private int quantity;

	public OrderDetail() {}
	
	public OrderDetail(Book book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
