package com.camel.component;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestletRouter extends RouteBuilder {
	@Override
	public void configure() throws Exception {
//		from("restlet:http://localhost:8081/hello?restletMethod=post").setBody().simple("hello world")
		from("restlet:/hello?restletMethod=post").setBody().simple("hello world")
		.to("direct:hello");
		from("direct:hello").transform().constant("hello world");
	}
}
