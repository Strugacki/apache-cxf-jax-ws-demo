package com.pluralsight.cxfdemo.interceptor;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class OrderServiceSoapInFaultInterceptor extends AbstractSoapInterceptor {

    public OrderServiceSoapInFaultInterceptor() {
        super(Phase.UNMARSHAL);
    } 

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("FAULT IN INTERCEPTOR");
		Fault fault = new Fault(message.getContent(Exception.class));
		QName faultCode = new QName("500");
		System.err.println(fault.getFaultCode());
		System.err.println(fault.getMessage());
		fault.setFaultCode(faultCode);
		fault.setMessage("Server error!");
		System.err.println(fault.getFaultCode());
		System.err.println(fault.getMessage());
		message.setContent(Fault.class, fault);
	}
	
	@Override
	public void handleFault(SoapMessage message) throws Fault{
		System.out.println("FAULT IN INTERCEPTOR");
	}

}
