package com.pluralsight.cxfdemo.orders;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.model.SoapBodyInfo;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

import com.pluralsight.schema.order.ErrorHandlerType;
import com.pluralsight.schema.order.OrderInquiryType;

public class OrderServiceSoapHeaderInterceptor extends AbstractPhaseInterceptor {

	public OrderServiceSoapHeaderInterceptor() {
		super(Phase.PRE_INVOKE);
	}
	
	
	@Override
	public void handleMessage(Message arg0) throws Fault {
		System.out.println("SERVER IN INTERCEPTOR");
		QName qname = new QName("http://www.pluralsight.com/service/Orders/", "apikey");
		
		/*SoapHeader header = (SoapHeader) arg0.getHeader(qname);
		System.err.println(arg0.getContent());
		String apikey = ((Element) header.getObject()).getTextContent();
		if(arg0.getInterceptorChain() != null){
			System.err.println("YES" + arg0.getInterceptorChain().toString());
			
		}else{
			System.err.println("NO");
		}
		System.err.println("API key = " + apikey);*/
		 try
	        {
	            HttpServletRequest req = (HttpServletRequest)arg0.get("HTTP.REQUEST");
	            String queryString = req.getQueryString();
	            if (queryString != null
	                    && (queryString.contains("wsdl") || queryString.contains("WSDL"))
	                    && req.getMethod().equals("GET")) {
	                return; //let the schema request pass
	            }
	            System.err.println("Testrceptor - processing data");
	            List list = arg0.getContent(List.class); // message parts
	            // TDataRequest is a request object generated from WSDL and XSD
	            // and used as a request message payload part
	            OrderInquiryType order = (OrderInquiryType) list.get(0);
	            System.out.println(order.getEan13());
	            ErrorHandlerType errorHandler = new ErrorHandlerType();
	            errorHandler.setResponseHeader(500);
	            errorHandler.setResponseBody("Bad request");
	            list.set(0, errorHandler);
	        }
	        catch(Exception ex)
	        {
	        	throw new Fault(ex);
	        }
	}
	
	@Override
	public void handleFault(Message message){
		System.out.println("SERVER IN INTERCEPTOR ERROR!!");
		Fault fault = new Fault(message.getContent(Exception.class));
		QName faultCode = new QName("500");
		System.err.println(fault.getFaultCode());
		System.err.println(fault.getMessage());
		fault.setFaultCode(faultCode);
		fault.setMessage("Server error!");
		System.err.println(fault.getFaultCode());
		System.err.println(fault.getMessage());
		message.setContent(Exception.class, fault);
	}


}
