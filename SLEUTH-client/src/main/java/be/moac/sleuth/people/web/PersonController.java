package be.moac.sleuth.people.web;

import be.moac.sleuth.people.PersonForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

/**
 * @author Jan Van Rensbergen.
 */
@Controller
@RequestMapping(path = "/")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);


    private final RestTemplate restTemplate;

    @Autowired
    public PersonController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String person(Model model, PersonForm person){
        model.addAttribute("person", person);

        return "index";
    }


    @RequestMapping(path = "/person", method = RequestMethod.POST)
    public String register(@Valid PersonForm person) {

        logger.info("Received some person  [{} {}] through form. Sending to some service.", person.getFirstName(), person.getName());

        final ResponseEntity<String> response = this.restTemplate
                .postForEntity("http://localhost:8282/api/person", person, String.class);

        logger.info("Some service responded with code [{}] and body [{}]", response.getStatusCode(), response.getBody());

        return "redirect:/";
    }




}
