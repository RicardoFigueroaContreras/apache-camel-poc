package com.camel.component;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.camel.service.FilePocService;

@Component
public class UpdateFilePocRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
        from("seda:update")
        	.routeId("update?concurrentConsumers=20").startupOrder(2)
        	.bean(FilePocService.class, "updateRecords");
	}

}
