package be.moac.sleuth.person;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jan Van Rensbergen.
 */
public class Person {

    private String firstName;

    private String name;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> asMap() {
        final HashMap<String, String> result = new HashMap<>();
        result.put("firstName", this.firstName);
        result.put("name", this.name);
        return result;
    }

}
