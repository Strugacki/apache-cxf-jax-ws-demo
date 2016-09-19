package com.pluralsight.cxfdemo.interceptor;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.Soap11FaultOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

import com.pluralsight.schema.order.ErrorHandlerType;

public class OrdersServiceSoapOutFaultInterceptor extends AbstractSoapInterceptor {

	public OrdersServiceSoapOutFaultInterceptor(){
		super(Phase.MARSHAL);
		addAfter(Soap11FaultOutInterceptor.class.getName());
	}
	
	
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("FAULT OUT INTERCEPTOR");
		Fault fault = new Fault(message.getContent(Exception.class));
		QName faultCode = new QName("500");
		System.err.println(fault.getFaultCode());
		System.err.println(fault.getMessage());
		System.err.println(fault.getStatusCode());
		message.setContent(Exception.class, fault);
		//message.removeContent(Exception.class);
		ErrorHandlerType error = new ErrorHandlerType();
		error.setResponseHeader(500);
		error.setResponseBody("test");
		//message.setContent(ErrorHandlerType.class, error);
		
	}
	
	@Override
	public void handleFault(SoapMessage message) throws Fault{
		
	}
}
