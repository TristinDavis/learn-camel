package com.rieckpil.learning.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class KafkaCamelRouting {

	public static void main(String[] args) {
		CamelContext context = new DefaultCamelContext();
		try {
			context.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("kafka:kafka-camel-example?brokers=localhost:9092" + "&consumersCount=1" + "&seekTo=beginning"
							+ "&groupId=group1").routeId("FromKafka").log("${body}");
				}
			});
			context.start();
			Thread.sleep(5 * 60 * 1000);
			context.stop();

		} catch (Exception e) {
			System.out.println("Inside Exception : " + e);
		}
	}
}
