package com.pluralsight.cxfdemo.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class OrderServiceSoapHeaderInterceptor2 extends AbstractPhaseInterceptor {

	public OrderServiceSoapHeaderInterceptor2() {
		super(Phase.PRE_INVOKE);
	}
	
	
	@Override
	public void handleMessage(Message arg0) throws Fault {
		System.out.println("CLIENT IN INTERCEPTOR");
	}


}
