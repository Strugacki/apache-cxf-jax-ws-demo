package com.pluralsight.cxfdemo.orders;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;
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

public class OrderServiceSoapHeaderInterceptor2 extends AbstractPhaseInterceptor {

	public OrderServiceSoapHeaderInterceptor2() {
		super(Phase.PRE_INVOKE);
	}
	
	
	@Override
	public void handleMessage(Message arg0) throws Fault {
		System.out.println("CLIENT IN INTERCEPTOR");
	}


}
