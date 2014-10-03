package com.bookstore.service.request;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.customer.Address;
import com.bookstore.model.order.Order.PaymentOption;
import com.bookstore.model.order.OrderDetail;


public class OrderRequest {
	private String customerId;
	private String orderId;
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	private Address shippingAddress;
	private Address billingAddress;
	private PaymentOption paymentOption;
	

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public List<OrderDetail> getOrderDetails()
	{
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails)
	{
		this.orderDetails = orderDetails;
	}
	
	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public PaymentOption getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(PaymentOption paymentOption) {
		this.paymentOption = paymentOption;
	}
}
