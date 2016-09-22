package com.pluralsight.cxfdemo.interceptor;

import java.util.List;
import java.util.logging.Logger;

//import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import com.pluralsight.schema.order.ObjectFactory;
import com.pluralsight.schema.order.OrderInquiryType;

public class OrderServiceSoapHeaderInterceptor extends AbstractPhaseInterceptor {

	Logger logger = Logger.getLogger(OrderServiceSoapHeaderInterceptor.class.getName());
	
	
	public OrderServiceSoapHeaderInterceptor() {
		super(Phase.PRE_INVOKE);
	}
	
	
	@Override
	public void handleMessage(Message arg0) throws Fault {
		System.out.println("SERVER IN INTERCEPTOR");
		
		//SECOND TYPE OF VALIDATION
		
		 List list = arg0.getContent(List.class); // message parts
         OrderInquiryType order = (OrderInquiryType) list.get(0);
		
		if(order.getUniqueOrderId() == 0 ){
			throw new Fault("UniqueOrderId", logger);
		}else if(order.getAccountId() == 0){
			throw new Fault("AccountId", logger);
		}
	}
	
	@Override
	public void handleFault(Message message){
		System.out.println("SERVER IN INTERCEPTOR ERROR!!");
		
		//GETTING REASON OF EXCEPTION
		Fault fault = new Fault(message.getContent(Exception.class));
		System.out.println("*****PROBLEM WITH*******");
		System.out.println(fault.getMessage());
		System.out.println("************************");
		
		//CREATING OBJECT FACTORY
		ObjectFactory factory = new ObjectFactory();
		
		
		
		//ADJUST FAULT CONTENT TO REASON OF EXCEPTION
		if(fault.getMessage().equalsIgnoreCase("uniqueorderid")){
			com.pluralsight.schema.order.ProcessOrderPlacementError processOrderPlacementError = factory.createProcessOrderPlacementError();
			processOrderPlacementError.setResponseHeader(500);
			processOrderPlacementError.setResponseBody("THERE WAS PROBLEM WITH UniqueOrderId VALUE ");
			processOrderPlacementError.setResponseDetail("UniqueOrderId was null or 0 ");
			com.pluralsight.service.orders.ProcessOrderPlacementError myCustomError = new com.pluralsight.service.orders.ProcessOrderPlacementError("uniqueorderid error", processOrderPlacementError);
			//SETTING NEW VALUE OF EXCEPTION CONTENT
			message.setContent(Exception.class, myCustomError);
		}else if(fault.getMessage().equalsIgnoreCase("accountid")){
			com.pluralsight.schema.order.ProcessOrderPlacementError processOrderPlacementError = factory.createProcessOrderPlacementError();
			processOrderPlacementError.setResponseHeader(500);
			processOrderPlacementError.setResponseBody("THERE WAS PROBLEM WITH AccountId VALUE ");
			processOrderPlacementError.setResponseDetail("AccountId was null or 0 ");
			com.pluralsight.service.orders.ProcessOrderPlacementError myCustomError = new com.pluralsight.service.orders.ProcessOrderPlacementError("accountId error", processOrderPlacementError);
			//SETTING NEW VALUE OF EXCEPTION CONTENT
			message.setContent(Exception.class, myCustomError);
		}
	}


}
