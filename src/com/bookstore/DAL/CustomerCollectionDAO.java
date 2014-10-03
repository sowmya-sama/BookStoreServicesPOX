package com.bookstore.DAL;

import java.util.*;

import com.bookstore.model.customer.Address;
import com.bookstore.model.customer.Customer;
import com.bookstore.model.order.Order;
import com.bookstore.model.order.Order.OrderStatus;
import com.bookstore.model.order.Order.PaymentOption;
import com.bookstore.model.order.OrderDetail;


public class CustomerCollectionDAO {
	
private static HashMap<String, Customer> customerMap = GenerateCustomers();
		
	public Customer getCustomer(String cid) 
	{
		return customerMap.get(cid);
	}
	
	
	public boolean addCustomer(Customer customer)
	{
		if(customerMap.get(customer.getCustomerId()) == null)
		{
			customerMap.put(customer.getCustomerId(), customer);
			return true;
		}
		else 
			return false;
	}
	
	
	private static HashMap<String, Customer> GenerateCustomers()
	{
		HashMap<String, Customer> customers = new HashMap<String, Customer>();
		Customer customer = new Customer();
		
		customer.setCustomerId("12345");
		customer.setFirstName("Daniel");
		customer.setLastName("Bednarczyk");
		
		Address addr1 = new Address();
		addr1.setAddressId("123");
		addr1.setState("IL");
		addr1.setCity("Chicago");
		addr1.setStreet("1100 Milwaukee Ave");
		addr1.setZip("60111");
		customer.setDefaultShippingAddress(addr1);
		customer.setDefaultBillingAddress(addr1);
		
		Order order = new Order();
		order.setOrderId("1234");
		order.setOrderState(OrderStatus.Processing);
		order.setPaymentOption(PaymentOption.PayPal);
		order.setShippingAddress(customer.getDefaultShippingAddress());
		order.setbillingAddress(customer.getDefaultBillingAddress());
		
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setBook(new BookCollectionDAO().getBookByISBN("ISBN11111"));
		orderDetail.setQuantity(2);
		orderDetails.add(orderDetail);
		
		orderDetail = new OrderDetail();
		orderDetail.setBook(new BookCollectionDAO().getBookByISBN("ISBN11112"));
		orderDetail.setQuantity(1);
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		customer.addOrder(order);
		
		
		customers.put(customer.getCustomerId(), customer);
		
		
		
		customer = new Customer();
		
		customer.setCustomerId("123456");
		customer.setFirstName("Fred");
		customer.setLastName("Riekkie");
		
		addr1 = new Address();
		addr1.setAddressId("1234");
		addr1.setState("IL");
		addr1.setCity("Chicago");
		addr1.setStreet("11234 North Ave");
		addr1.setZip("61234");
		customer.setDefaultShippingAddress(addr1);
		customer.setDefaultBillingAddress(addr1);
		
		order = new Order();
		order.setOrderId("12345");
		order.setOrderState(OrderStatus.Shipped);
		order.setPaymentOption(PaymentOption.CreditCard);
		order.setShippingAddress(customer.getDefaultShippingAddress());
		order.setbillingAddress(customer.getDefaultBillingAddress());
		
		orderDetails = new ArrayList<OrderDetail>();
		
		orderDetail = new OrderDetail();
		orderDetail.setBook(new BookCollectionDAO().getBookByISBN("ISBN11113"));
		orderDetail.setQuantity(3);
		orderDetails.add(orderDetail);
		
		orderDetail = new OrderDetail();
		orderDetail.setBook(new BookCollectionDAO().getBookByISBN("ISBN11114"));
		orderDetail.setQuantity(4);
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		customer.addOrder(order);
		
		customers.put(customer.getCustomerId(), customer);
		
		return customers;
	}
}
