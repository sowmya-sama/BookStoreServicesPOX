package com.bookstore.service;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;


import com.bookstore.model.customer.Customer;
import com.bookstore.model.item.Book;
import com.bookstore.model.order.Order;
import com.bookstore.model.order.Order.OrderStatus;
import com.bookstore.model.order.OrderDetail;
import com.bookstore.service.request.BookRequest;
import com.bookstore.service.request.CustomerRequest;
import com.bookstore.service.request.OrderRequest;
import com.bookstore.DAL.BookCollectionDAO;
import com.bookstore.DAL.CustomerCollectionDAO;
import com.bookstore.service.request.BookCollection;

@WebService(endpointInterface = "com.bookstore.service.BookStoreService", serviceName = "BookStoreServices")
public class BookStoreServiceImpl implements BookStoreService {


	@Override
	public Customer getCustomer(CustomerRequest customerRequest) {
		CustomerCollectionDAO dao = new CustomerCollectionDAO();
		return dao.getCustomer(customerRequest.getCustomerId());
	}

	@Override
	public boolean addCustomer(Customer customer) {
		CustomerCollectionDAO dao = new CustomerCollectionDAO();
		try
		{
			dao.addCustomer(customer);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	@Override
	public BookCollection searchBook(BookRequest bookRequest) {

		BookCollection bookCollection = new BookCollection();
		ArrayList<Book> list;
		BookCollectionDAO dao = new BookCollectionDAO();
		
		switch(bookRequest.getSearchBy())
		{
			case ISBN:
				list = dao.searchBookByISBN(bookRequest.getKey());
				break;
			case Author:
				list = dao.searchBookByAuthor(bookRequest.getKey());
				break;
			case Title:
				list = dao.searchBookByTitle(bookRequest.getKey());
				break;
			default:
				list = new ArrayList<Book>();
		}
		bookCollection.setBooks(list);
		return bookCollection;
	}

	@Override
	public boolean placeOrder(OrderRequest orderRequest) {
		
		CustomerCollectionDAO customerDAO = new CustomerCollectionDAO();
		Customer customer = customerDAO.getCustomer(orderRequest.getCustomerId());
		if(customer == null)
			return false;
		Order order = new Order();
		order.setOrderId(orderRequest.getOrderId());
		BookCollectionDAO bookDAO = new BookCollectionDAO();
		for (OrderDetail orderDetail : orderRequest.getOrderDetails())
		{
			Book book = bookDAO.getBookByID(orderDetail.getBook().getId());
			if(book != null)
			{
				order.addBook(book, orderDetail.getQuantity());
			}
			else
				return false;
			
		}
		order.setbillingAddress(orderRequest.getBillingAddress());
		order.setShippingAddress(orderRequest.getShippingAddress());
		order.setOrderState(OrderStatus.Opened);
		order.setPaymentOption(orderRequest.getPaymentOption());
		customer.addOrder(order);
		return true;
	}

	@Override
	public OrderStatus checkOrder(OrderRequest orderRequest) {
		CustomerCollectionDAO dao = new CustomerCollectionDAO();
		Customer customer = dao.getCustomer(orderRequest.getCustomerId());

		if(customer != null)
		{
			List<Order> orders = customer.getOrders();
			for (Order order : orders) 
			{
				if(order.getOrderId().equals(orderRequest.getOrderId()))
				{
					return order.getOrderState();
				}
			}
		}
		return OrderStatus.Invalid;
			
	}

	@Override
	public boolean cancelOrder(OrderRequest orderRequest) {
		CustomerCollectionDAO dao = new CustomerCollectionDAO();
		Customer customer = dao.getCustomer(orderRequest.getCustomerId());
		try
		{
			if(customer != null)
			{
				List<Order> orders = customer.getOrders();
				for (Order order : orders) 
				{
					if(order.getOrderId().equals(orderRequest.getOrderId()))
					{
						order.cancelOrder();
						return true;
					}
				}
			}
		}
		catch(Exception ex)
		{}
		return false;
	}
	
	
}