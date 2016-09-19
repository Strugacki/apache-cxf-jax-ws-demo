package com.pluralsight.cxfdemo.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.SoapOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class OrderServiceSoapHeaderOutInterceptor2 extends AbstractSoapInterceptor {

	public OrderServiceSoapHeaderOutInterceptor2() {
		super(Phase.WRITE);
		this.addBefore(SoapOutInterceptor.class.getName());
		
	}
	
	@Override
	public void handleMessage(SoapMessage arg0) throws Fault {
		System.out.println("SERVER OUT INTERCEPTOR");
		 
	}

}
