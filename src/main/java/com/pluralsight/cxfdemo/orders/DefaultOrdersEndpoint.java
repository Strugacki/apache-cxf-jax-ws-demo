package com.pluralsight.cxfdemo.orders;

import javax.jws.HandlerChain;
import javax.jws.WebService;

import org.apache.cxf.interceptor.Fault;
import org.springframework.beans.factory.annotation.Autowired;

import com.pluralsight.schema.order.AccountType;
import com.pluralsight.schema.order.ObjectFactory;
import com.pluralsight.schema.order.OrderInquiryResponseType;
import com.pluralsight.schema.order.OrderInquiryType;
import com.pluralsight.service.orders.Orders;

@WebService(portName="OrdersSOAP", serviceName="Orders", endpointInterface="com.pluralsight.service.orders.Orders", targetNamespace="http://www.pluralsight.com/service/Orders")
//@HandlerChain(file="src/main/webapp/WEB-INF/SOAPHandlerConf.xml")
public class DefaultOrdersEndpoint implements Orders {

	
	@Autowired
	private OrdersService ordersService;
	
	@Override
	public OrderInquiryResponseType processOrderPlacement(OrderInquiryType orderInquiry) throws Fault {
		OrderInquiryResponseType response = ordersService.processOrder(orderInquiry.getUniqueOrderId(), orderInquiry.getOrderQuantity(), orderInquiry.getAccountId(), orderInquiry.getEan13());
		return response;
	}

}
