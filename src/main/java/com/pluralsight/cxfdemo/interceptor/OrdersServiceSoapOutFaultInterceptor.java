package com.pluralsight.cxfdemo.interceptor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.Soap11FaultOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;


import com.pluralsight.xml.FaultXML;

public class OrdersServiceSoapOutFaultInterceptor extends AbstractSoapInterceptor {

	public OrdersServiceSoapOutFaultInterceptor(){
		super(Phase.PRE_STREAM);
		addAfter(Soap11FaultOutInterceptor.class.getName());
	}
	
	
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("FAULT OUT INTERCEPTOR");
		//Getting content of message if it's a fault message then it has only Exception content
		Fault fault = new Fault(message.getContent(Exception.class));
		System.out.println("**********************************");
		System.err.println(fault.getFaultCode());
		System.err.println(fault.getMessage());
		System.err.println(fault.getStatusCode());
		System.out.println("**********************************");
		
		//message.setContent(Exception.class, fault);
		
		try {
			//GENERATING TODAYS DATE IN yyyy-mm-dd FORMAT
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String formatted = format1.format(cal.getTime());
			File file = new File("D:\\error"+cal.getTime().toString().replaceAll(" ", "-").replaceAll(":", "-")+".xml");
			
			//CREATING NEW FAULTXML INSTANCE
			FaultXML xml = new FaultXML();
			xml.setCode(500);
			xml.setMessage("HAHAHAHA");
			xml.setDate(cal.getTime().toString());
			
			//MARSHALLING FAULTXML
			JAXBContext context = JAXBContext.newInstance(FaultXML.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(xml, file);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void handleFault(SoapMessage message) throws Fault{
	}
}
