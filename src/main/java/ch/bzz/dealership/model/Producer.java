package ch.bzz.dealership.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * Model class for a producer
 * @author Nadim Bhatti
 * @since 28-06-2022
 * @version 1.0
 */
public class Producer {
    @FormParam("name")
    @Size(min = 1, max = 30)
    private String name;

    @FormParam("headquarter")
    @NotEmpty
    @Size(min = 1, max = 30)
    private String headquarter;

    /**
     * gets name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets name
     *
     * @param name the value to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets headquarter
     * @return headquarter
     */
    public String getHeadquarter() {
        return headquarter;
    }

    /**
     * sets headquarter
     *
     * @param headquarter the value to set
     */
    public void setHeadquarter(String headquarter) {
        this.headquarter = headquarter;
    }
}
