package com.pluralsight.cxfdemo.orders;

import com.pluralsight.schema.order.OrderInquiryResponseType;

public interface OrdersService {

	OrderInquiryResponseType processOrder(int uniqueOrderId, int orderQuantity, int accountId, long ean13);
	
}
