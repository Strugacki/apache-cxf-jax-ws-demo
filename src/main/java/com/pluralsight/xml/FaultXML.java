package com.pluralsight.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FaultXML {

	private int code;
	private String message;
	private String date;
	
	@XmlElement
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@XmlElement
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public FaultXML(){
		
	}
	
	public FaultXML(int code, String message,  String date){
		this.code = code;
		this.message = message;
		this.date = date;
	}
	
}
