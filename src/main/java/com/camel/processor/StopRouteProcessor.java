package com.camel.processor;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopRouteProcessor implements Processor {

    private final static Logger LOG = LoggerFactory.getLogger(StopRouteProcessor.class);

    private final String name;

    /**
     * @param name route to stop
     */
    public StopRouteProcessor(String name) {
        this.name = name;
    }

    public void process(Exchange exchange) throws Exception {
        LOG.info("Stopping route: " + name);
        exchange.getContext().getInflightRepository().remove(exchange);
        exchange.getContext().stopRoute(name);
    }
}

