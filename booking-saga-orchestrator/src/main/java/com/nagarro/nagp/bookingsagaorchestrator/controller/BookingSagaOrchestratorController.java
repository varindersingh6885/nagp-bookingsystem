package com.nagarro.nagp.bookingsagaorchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;

import com.nagarro.nagp.bookingsagaorchestrator.model.OrderFlight;
import com.nagarro.nagp.bookingsagaorchestrator.util.JsonSerializerUtil;

@Controller
public class BookingSagaOrchestratorController {
	
	@Autowired 
	private JmsTemplate jmsTemplate;
	
	@JmsListener(destination = "OrderFlightRequestReceivedEvent")
	public void newFlightOrderRequestReceived(String orderPayload) {
		OrderFlight booking = JsonSerializerUtil.orderPayload(orderPayload);

		// check seats available
		jmsTemplate.convertAndSend("OrderFlightCheckSeatsAvailable",JsonSerializerUtil.serialize(booking));
	}
}
