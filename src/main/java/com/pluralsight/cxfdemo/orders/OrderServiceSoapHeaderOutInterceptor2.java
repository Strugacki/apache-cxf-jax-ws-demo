package com.pluralsight.cxfdemo.orders;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.SoapOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.Phase;

import com.pluralsight.schema.order.ErrorHandlerType;
import com.pluralsight.schema.order.OrderInquiryType;

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
