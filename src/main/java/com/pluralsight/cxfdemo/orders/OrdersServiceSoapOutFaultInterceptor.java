package com.pluralsight.cxfdemo.orders;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.phase.Phase;

import com.pluralsight.schema.order.ErrorHandlerType;

public class OrdersServiceSoapOutFaultInterceptor extends AbstractSoapInterceptor {

	public OrdersServiceSoapOutFaultInterceptor(){
		super(Phase.MARSHAL);
	}
	
	
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("FAULT OUT INTERCEPTOR");
		Fault fault = new Fault(message.getContent(Exception.class));
		QName faultCode = new QName("500");
		System.err.println(fault.getFaultCode());
		System.err.println(fault.getMessage());
		message.setContent(Fault.class, fault);
	}
	
	@Override
	public void handleFault(SoapMessage message) throws Fault{
		System.out.println("FAULT OUT INTERCEPTOR");
		List list = message.getContent(List.class);
		Fault error = (Fault) list.get(0);
		System.out.println(error.getMessage());
	}
}
