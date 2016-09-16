package com.pluralsight.cxfdemo.intercpetor;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SOAPHandler implements javax.xml.ws.handler.soap.SOAPHandler<SOAPMessageContext> {

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	 public boolean handleFault(SOAPMessageContext context) {  
	     logToSystemOut(context);  
	     return true;  
	 } 
	
	@Override
     public boolean handleMessage(SOAPMessageContext context) {  
         logToSystemOut(context);
         System.out.println("TATATATATATATATAT");
         return true;  
     }  
 
     private void logToSystemOut(SOAPMessageContext smc) {  
         Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);  
 
         if (outboundProperty.booleanValue()) {  
             System.out.println("Outgoing message:");  
         } else {  
             System.out.println("Incoming message:");  
         }  
 
         SOAPMessage message = smc.getMessage();  
         try {  
             message.writeTo(System.out);  
         } catch (Exception e) {  
             System.out.println("Exception in handler: " + e);  
         }  
     }

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
