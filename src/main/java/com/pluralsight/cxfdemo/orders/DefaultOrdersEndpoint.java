package com.pluralsight.cxfdemo.orders;

import java.util.logging.Logger;

import javax.jws.WebService;
import javax.xml.namespace.QName;

import org.apache.cxf.interceptor.Fault;
import org.springframework.beans.factory.annotation.Autowired;

import com.pluralsight.schema.order.ObjectFactory;
import com.pluralsight.schema.order.OrderInquiryResponseType;
import com.pluralsight.schema.order.OrderInquiryType;
import com.pluralsight.service.orders.Orders;
import com.pluralsight.service.orders.ProcessOrderPlacementError;


@WebService(portName="OrdersSOAP", serviceName="Orders", endpointInterface="com.pluralsight.service.orders.Orders", targetNamespace="http://www.pluralsight.com/service/Orders")
public class DefaultOrdersEndpoint implements Orders {

	Logger logger = Logger.getLogger(DefaultOrdersEndpoint.class.getName());
	
	@Autowired
	private OrdersService ordersService;
	
	@Override
	public OrderInquiryResponseType processOrderPlacement(OrderInquiryType orderInquiry) throws ProcessOrderPlacementError {
		OrderInquiryResponseType response = ordersService.processOrder(orderInquiry.getUniqueOrderId(), orderInquiry.getOrderQuantity(), orderInquiry.getAccountId(), orderInquiry.getEan13());
		//FIRST TYPE OF VALIDATION
		/*if(orderInquiry.getUniqueOrderId() == 0){
			throw new Fault("UniqueOrderId", logger);
		}else if(orderInquiry.getAccountId() == 0){
			throw new Fault("AccountId", logger);
		}*/
		return response;
	}

}
