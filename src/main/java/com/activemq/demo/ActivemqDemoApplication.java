package com.activemq.demo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.activemq.demo.config.OrderSender;
import com.activemq.demo.model.Order;

@SpringBootApplication
public class ActivemqDemoApplication implements ApplicationRunner{
	
	private static Logger log = LoggerFactory.getLogger(ActivemqDemoApplication.class);

    @Autowired
    private OrderSender orderSender;

	public static void main(String[] args) {
		SpringApplication.run(ActivemqDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		log.info("Spring Boot Embedded ActiveMQ Configuration Example");

        for (int i = 0; i < 5; i++){
            Order myMessage = new Order(i + " - Sending JMS Message using Embedded activeMQ", new Date());
            orderSender.send(myMessage);
        }

        log.info("Waiting for all ActiveMQ JMS Messages to be consumed");
        TimeUnit.SECONDS.sleep(3);
        System.exit(-1);
		
	}

}
