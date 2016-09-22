package com.pluralsight.cxfdemo.orders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.cxf.interceptor.Fault;
import org.springframework.stereotype.Service;

import com.pluralsight.schema.order.AccountType;
import com.pluralsight.schema.order.BookType;
import com.pluralsight.schema.order.ObjectFactory;
import com.pluralsight.schema.order.OrderInquiryResponseType;
import com.pluralsight.schema.order.OrderItemType;
import com.pluralsight.schema.order.OrderStatusType;
import com.pluralsight.schema.order.OrderType;
import com.pluralsight.service.orders.ProcessOrderPlacementError;

@Service
public class OrderServiceImpl implements OrdersService {

	@Override
	public OrderInquiryResponseType processOrder(int uniqueOrderId, int orderQuantity, int accountId, long ean13) throws ProcessOrderPlacementError {
		ObjectFactory factory = new ObjectFactory();
		OrderInquiryResponseType response = factory.createOrderInquiryResponseType();
		AccountType account = factory.createAccountType();
		account.setAccountId(accountId);
		response.setAccount(account);
		
		OrderItemType orderItem = factory.createOrderItemType();
		BookType book = factory.createBookType();
		book.setEan13(ean13);
		book.setTitle("CXF BOOK");
		orderItem.setBook(book);
		try{
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(new Date(System.currentTimeMillis()));
			XMLGregorianCalendar estimatedShippingDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			orderItem.setExpectedShippingdate(estimatedShippingDate);
			orderItem.setLineNumber(1);
			orderItem.setPrice(new BigDecimal(5));
			orderItem.setQunatityShipped(orderQuantity);
		}catch(Exception e){
			
		}
		
		OrderType orderType = factory.createOrderType();
		orderType.setOrderStatus(OrderStatusType.READY);
		orderType.getOrderItems().add(orderItem);
		response.setOrder(orderType);
		return response;
	}

}
