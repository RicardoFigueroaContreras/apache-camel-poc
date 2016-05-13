package com.camel.component;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EnablePollingRouter extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("restlet:/enable?restletMethod=get")
		.routeId("enable")
		.process(new Processor(){
			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getContext().getInflightRepository().remove(exchange);
				exchange.getContext().startRoute("manual");
			}
		});
	}
}
