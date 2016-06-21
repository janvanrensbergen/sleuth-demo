package be.moac.sleuth.person.web;

import be.moac.sleuth.person.PersonForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

/**
 * @author Jan Van Rensbergen.
 */
@RestController
@RequestMapping(path = "/person")
public class PersonRestController {

    private static final Logger logger = LoggerFactory.getLogger(PersonRestController.class);



    @RequestMapping(method = RequestMethod.POST)
    public String register(@RequestBody @Valid PersonForm person) {

        logger.info("Received some person  [{} {}] through rest. Sending to some service.", person.getFirstName(), person.getName());

        final ResponseEntity<String> response = new RestTemplate()
                .postForEntity("http://localhost:8282/api/person", person, String.class);

        logger.info("Some service responded with code [{}] and body [{}]", response.getStatusCode(), response.getBody());

        return "OK";

    }



}
