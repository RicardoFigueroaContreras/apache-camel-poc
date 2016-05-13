package com.camel.component;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.camel.processor.StopRouteProcessor;
import com.camel.service.FilePocService;

@Component
public class PollFilePocRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onCompletion().process(new StopRouteProcessor("manual"));

		from("file:target/file?noop=false&delay=500&move=backup/${date:now:yyyyMMddhhmmss}_${file:name}&maxMessagesPerPoll=1&include=.*.csv")
//		from("file:target/file?maxMessagesPerPoll=1")
			 .routeId("manual").noAutoStartup().startupOrder(1)
			 .log("Starting to process big file: ${header.CamelFileName}")
			 .split(body().tokenize("\n")).streaming().parallelProcessing()
			 	.bean(FilePocService.class, "csvToObject")
 			 	.to("seda:update")
			 .end();
	}

}
