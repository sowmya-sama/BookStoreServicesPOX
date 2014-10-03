package com.bookstore.model.customer;

import java.util.*;

import com.bookstore.model.customer.Address;
import com.bookstore.model.order.Order;


public class Customer {
	private String customerId;
	private String lastName;
	private String firstName;
	private Address defaultBillingAddress;
	private Address defaultShippingAddress;
	private List<Order> orders = new ArrayList<Order>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public Address getDefaultBillingAddress() {
		return defaultBillingAddress;
	}

	public void setDefaultBillingAddress(Address billingAddress) {
		this.defaultBillingAddress = billingAddress;
	}

	public Address getDefaultShippingAddress() {
		return defaultShippingAddress;
	}

	public void setDefaultShippingAddress(Address shippingAddress) {
		this.defaultShippingAddress = shippingAddress;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String id) {
		this.customerId = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
}
