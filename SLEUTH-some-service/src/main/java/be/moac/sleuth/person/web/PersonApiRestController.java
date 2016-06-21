package be.moac.sleuth.person.web;

import be.moac.sleuth.SomeServiceApplication;
import be.moac.sleuth.person.Person;
import be.moac.sleuth.person.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jan Van Rensbergen.
 */
@RestController
@RequestMapping(path = "/person")
public class PersonApiRestController {

    private static final Logger logger = LoggerFactory.getLogger(PersonApiRestController.class);

    private final PersonRepository personRepository;

    private final SomeServiceApplication.JmsGateway jmsGateway;

    @Autowired
    public PersonApiRestController(PersonRepository personRepository, SomeServiceApplication.JmsGateway jmsGateway) {
        this.personRepository = personRepository;
        this.jmsGateway = jmsGateway;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.CREATED)
    private Person register(@RequestBody Person person){

        logger.info("Some service got some person  [{} {}].", person.getFirstName(), person.getName());

        this.personRepository.addPerson(person);
        jmsGateway.sendPerson(person);

        return person;
    }



}
