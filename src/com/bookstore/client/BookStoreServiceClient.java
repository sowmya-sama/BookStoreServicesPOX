package com.bookstore.client;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.bookstore.model.customer.Address;
import com.bookstore.model.customer.Customer;
import com.bookstore.model.item.Book;
import com.bookstore.model.order.Order;
import com.bookstore.model.order.Order.PaymentOption;
import com.bookstore.model.order.OrderDetail;
import com.bookstore.service.BookStoreService;
import com.bookstore.service.request.BookCollection;
import com.bookstore.service.request.BookRequest;
import com.bookstore.service.request.BookRequest.SearchBy;
import com.bookstore.service.request.CustomerRequest;
import com.bookstore.service.request.OrderRequest;

import java.util.ArrayList;
import java.util.List;

public final class BookStoreServiceClient {

    private BookStoreServiceClient() {
    } 

    public static void main(String args[]) throws Exception {

    	//The following approach is called simple frontend.
    	//The simple frontend provides simple components or Java classes that use reflection to build and publish web services.
    	//The simple frontend uses factory components to create a service and the client. It does so by using Java reflection API.
    	//Here we get the implementation of the HRService API using the Java reflection API for implementing the Client.
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

    	//Interceptors are components that intercept the messages exchanged or passed between web service clients and server components. 
    	//In CXF, this is implemented through the concept of Interceptor chains. 
    	//The concept of Interceptor chaining is the core functionality of CXF runtime.
    	//the following are Interceptors to log in and out messages.
    	factory.getInInterceptors().add(new LoggingInInterceptor());
    	factory.getOutInterceptors().add(new LoggingOutInterceptor());
    	
    	//the following line is to bind for regular XML format instead of SOAP format
    	factory.setBindingId("http://cxf.apache.org/bindings/xformat");
    	
    	//Associate the intended Service Endpoint Interface
    	//Java Reflection will be used to generate the implementation.
    	factory.setServiceClass(BookStoreService.class);
    	factory.setAddress("http://localhost:8080/BookStoreServicesPOX/services/cxfBookStoreService");
    	BookStoreService bookStoreService = (BookStoreService) factory.create();

    	//Now, start sending requests and accepting responses
    	
    	//Search for a book by ISBN
    	BookRequest bookRequest = new BookRequest();
    	bookRequest.setKey("Title");
    	bookRequest.setSearchBy(SearchBy.Title);
    	BookCollection bookcollection = bookStoreService.searchBook(bookRequest);
    	for (Book book : bookcollection.getBooks()) {
    		System.out.println("Book Title: " + book.getTitle());
    	}
    	
    	
    	
    	//Search for a book by ISBN
    	bookRequest = new BookRequest();
    	bookRequest.setKey("ISBN11111");
    	bookRequest.setSearchBy(SearchBy.ISBN);
    	bookcollection = bookStoreService.searchBook(bookRequest);
    	System.out.println("Book ISBN: " + bookcollection.getBooks().get(0).getISBN());
    	
    	//Search for a book by Author
    	bookRequest = new BookRequest();
    	bookRequest.setKey("Author11112");
    	bookRequest.setSearchBy(SearchBy.Author);
    	bookcollection = bookStoreService.searchBook(bookRequest);
    	System.out.println("Book Author: " + bookcollection.getBooks().get(0).getAuthor());
    	
    	//Search for a book by Title
    	bookRequest = new BookRequest();
    	bookRequest.setKey("Title11113");
    	bookRequest.setSearchBy(SearchBy.Title);
    	bookcollection = bookStoreService.searchBook(bookRequest);
    	System.out.println("Book Title: " + bookcollection.getBooks().get(0).getTitle());
    	
    	
    	//Add customer
    	Customer customer = new Customer();
    	customer.setCustomerId("asdf");
    	customer.setFirstName("George");
    	customer.setLastName("Assmus");
    	Address addr1 = new Address();
    	addr1.setAddressId("11223344");
    	addr1.setCity("Hanover Park");
    	addr1.setStreet("Hwy 20");
    	addr1.setState("IL");
    	addr1.setUnit("1W23");
    	addr1.setZip("60111");
    	customer.setDefaultBillingAddress(addr1);
    	customer.setDefaultShippingAddress(addr1);
    	System.out.println("Adding a new customer. Result: " + bookStoreService.addCustomer(customer));
    	
    	
    	//Get customer
    	CustomerRequest customerRequest = new CustomerRequest();
    	customerRequest.setCustomerId("123456");
    	customer = bookStoreService.getCustomer(customerRequest);
    	
    	System.out.println("Customer ID: " + customer.getCustomerId() + " First Name: " + customer.getFirstName() + 
    			 " Last Name: " + customer.getLastName());
    	
    	
    	
    	//Place Order
    	customerRequest = new CustomerRequest();
    	customerRequest.setCustomerId("12345");
    	customer = bookStoreService.getCustomer(customerRequest);
    	
    	OrderRequest orderRequest = new OrderRequest();
    	orderRequest.setOrderId("abcde");
    	orderRequest.setCustomerId(customer.getCustomerId());
    	orderRequest.setShippingAddress(customer.getDefaultShippingAddress());
    	orderRequest.setBillingAddress(customer.getDefaultBillingAddress());
    	orderRequest.setPaymentOption(PaymentOption.CreditCard);
    	
    	OrderDetail orderDetail = new OrderDetail();
    	orderDetail.setQuantity(2);
    	orderDetail.setBook(bookcollection.getBooks().get(0));
    	List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
    	orderDetails.add(orderDetail);
    	orderRequest.setOrderDetails(orderDetails);
    	
    	System.out.println("Place Order for a book. Result:" + bookStoreService.placeOrder(orderRequest));
    	
    	//Check Order Status
    	orderRequest = new OrderRequest();
    	orderRequest.setOrderId("1234");
    	orderRequest.setCustomerId("12345");
    	System.out.println("Check Order Status. Result:" + bookStoreService.checkOrder(orderRequest));
    	
    	//Cancel Order
    	//orderRequest.setOrderId("1234");
    	//orderRequest.setCustomerId("123456");
    	System.out.println("Cancel Order. Result:" + bookStoreService.cancelOrder(orderRequest));
    	
    	
    	System.exit(0);

    }

}