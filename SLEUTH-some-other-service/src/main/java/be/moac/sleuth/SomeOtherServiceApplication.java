package be.moac.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;

/**
 * @author Jan Van Rensbergen.
 */
@SpringBootApplication
@EnableIntegration
@IntegrationComponentScan
public class SomeOtherServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(SomeOtherServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SomeOtherServiceApplication.class, args);
    }

        @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedDelay(1000).get();
    }

    @Bean
    public IntegrationFlow jmsInboundFlow(ConnectionFactory connectionFactory) {
        return IntegrationFlows.from(Jms.inboundAdapter(connectionFactory)
                .configureJmsTemplate(t -> t.deliveryPersistent(true).jmsMessageConverter(new SimpleMessageConverter()))
                .destination("sample.queue"))
                .handle(message -> logger.info("Got something [{}]", message))
                .get();
    }

}
