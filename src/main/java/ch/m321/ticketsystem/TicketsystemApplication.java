package ch.m321.ticketsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@SpringBootApplication
@EnableRabbit
public class TicketsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketsystemApplication.class, args);
    }

}
