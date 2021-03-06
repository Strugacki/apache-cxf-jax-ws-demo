
package com.pluralsight.service.orders;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.8
 * 2016-09-20T09:34:02.042+02:00
 * Generated source version: 2.7.8
 */

@WebFault(name = "ProcessOrderPlacementError", targetNamespace = "http://www.pluralsight.com/schema/Order")
public class ProcessOrderPlacementError extends Exception {
    
    private com.pluralsight.schema.order.ProcessOrderPlacementError processOrderPlacementError;

    public ProcessOrderPlacementError() {
        super();
    }
    
    public ProcessOrderPlacementError(String message) {
        super(message);
    }
    
    public ProcessOrderPlacementError(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessOrderPlacementError(String message, com.pluralsight.schema.order.ProcessOrderPlacementError processOrderPlacementError) {
        super(message);
        this.processOrderPlacementError = processOrderPlacementError;
    }

    public ProcessOrderPlacementError(String message, com.pluralsight.schema.order.ProcessOrderPlacementError processOrderPlacementError, Throwable cause) {
        super(message, cause);
        this.processOrderPlacementError = processOrderPlacementError;
    }

    public com.pluralsight.schema.order.ProcessOrderPlacementError getFaultInfo() {
        return this.processOrderPlacementError;
    }
}
