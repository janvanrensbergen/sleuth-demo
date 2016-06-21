package be.moac.sleuth;

import be.moac.sleuth.person.Person;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.messaging.handler.annotation.Payload;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 * @author Jan Van Rensbergen.
 */
@SpringBootApplication
@EnableJms
@EnableIntegration
@IntegrationComponentScan
public class SomeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SomeServiceApplication.class, args);
    }


    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sample.queue");
    }

    @MessagingGateway
    public interface JmsGateway {

        @Gateway(requestChannel = "jmsOutboundGatewayChannel")
        void sendPerson(@Payload Person person);

    }

    @Bean
    public IntegrationFlow jmsOutboundGatewayFlow(ConnectionFactory connectionFactory) {
        return IntegrationFlows.from("jmsOutboundGatewayChannel")
                .transform(Person::asMap)
                .wireTap(flow -> flow.handle(new LoggingHandler(LoggingHandler.Level.INFO.name())))
                .handle(Jms.outboundAdapter(connectionFactory)
                        .destination("sample.queue"))
                .get();
    }

}
