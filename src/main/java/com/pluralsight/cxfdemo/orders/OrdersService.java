package com.pluralsight.cxfdemo.orders;

import com.pluralsight.schema.order.OrderInquiryResponseType;
import com.pluralsight.service.orders.ProcessOrderPlacementError;

public interface OrdersService {

	OrderInquiryResponseType processOrder(int uniqueOrderId, int orderQuantity, int accountId, long ean13) throws ProcessOrderPlacementError;
	
}
