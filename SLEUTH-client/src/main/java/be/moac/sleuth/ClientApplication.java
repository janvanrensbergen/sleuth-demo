package be.moac.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jan Van Rensbergen.
 */
@SpringBootApplication
public class ClientApplication {


    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    Sampler sampler() {
        return span -> true;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
