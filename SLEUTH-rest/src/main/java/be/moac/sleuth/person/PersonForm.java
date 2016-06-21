package be.moac.sleuth.person;

import javax.validation.constraints.NotNull;

/**
 * @author Jan Van Rensbergen.
 */
public class PersonForm {

    @NotNull
    private String firstName;

    @NotNull
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

}
