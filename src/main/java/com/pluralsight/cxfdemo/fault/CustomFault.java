package com.pluralsight.cxfdemo.fault;

import org.apache.cxf.common.i18n.Message;
import org.apache.cxf.interceptor.Fault;

public class CustomFault extends Fault {

	public CustomFault(Message message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
