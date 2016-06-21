package be.moac.sleuth.person;

import java.util.List;

/**
 * @author Jan Van Rensbergen.
 */
public interface PersonRepository {

    void addPerson(Person person);

    List<Person> getAll();

}
