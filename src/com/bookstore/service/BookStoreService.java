package com.bookstore.service;


import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebParam;




import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import com.bookstore.model.customer.Customer;
import com.bookstore.service.request.BookCollection;
import com.bookstore.service.request.CustomerRequest;
import com.bookstore.service.request.BookRequest;
import com.bookstore.model.order.Order;
import com.bookstore.model.order.Order.OrderStatus;
import com.bookstore.service.request.OrderRequest;



@WebService
public interface BookStoreService {
	@WebResult(targetNamespace="", name="Customer")
    Customer getCustomer(@WebParam(name="CustomerRequest") CustomerRequest customerRequest);
	
	@WebResult(targetNamespace="", name="boolean")
    boolean addCustomer(@WebParam(name="Customer") Customer customer);
	
	@WebResult(targetNamespace="", name="BookCollection")
	BookCollection searchBook(@WebParam(name="BookRequest") BookRequest bookRequest);
	
	@WebResult(targetNamespace="", name="boolean")
    boolean placeOrder(@WebParam(name="OrderRequest") OrderRequest orderRequest);
	
	@WebResult(targetNamespace="", name="OrderStatus")
	OrderStatus checkOrder(@WebParam(name="OrderRequest") OrderRequest orderRequest);
	
	@WebResult(targetNamespace="", name="boolean")
    boolean cancelOrder(@WebParam(name="OrderRequest") OrderRequest orderRequest);
	
}
