package com.fabiorapanelo.order;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class LogOrderHandler implements SOAPHandler<SOAPMessageContext> {

	public void close(MessageContext context) {

	}

	public boolean handleFault(SOAPMessageContext context) {
		this.print(context);
		return true;
	}

	public boolean handleMessage(SOAPMessageContext context) {
		this.print(context);
		return true;
	}

	public Set<QName> getHeaders() {
		return null;
	}

	private void print(SOAPMessageContext context) {

		Boolean isOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (isOutbound) {

			try {
				SOAPMessage soapMsg = context.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();

				System.out.println(soapEnv.toString());

			} catch (SOAPException e) {
				e.printStackTrace();
			}
		}

	}

}
