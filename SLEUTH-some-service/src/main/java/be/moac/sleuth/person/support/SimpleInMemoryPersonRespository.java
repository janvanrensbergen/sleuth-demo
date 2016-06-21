package be.moac.sleuth.person.support;

import be.moac.sleuth.person.Person;
import be.moac.sleuth.person.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jan Van Rensbergen.
 */
@Component
public class SimpleInMemoryPersonRespository implements PersonRepository {

    private static final Logger logger = LoggerFactory.getLogger(SimpleInMemoryPersonRespository.class);

    private final List<Person> persons = new ArrayList<>();

    @Override
    public void addPerson(Person person) {
        logger.info("Save some person [{} {}] to repository.", person.getFirstName(), person.getName());
        persons.add(person);
    }

    @Override
    public List<Person> getAll() {
        return Collections.unmodifiableList(persons);
    }
}
