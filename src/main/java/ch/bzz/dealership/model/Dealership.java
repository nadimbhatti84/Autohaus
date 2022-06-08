package ch.bzz.dealership.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.Vector;

public class Dealership {
    @JsonIgnore
    private Vector<Car> allCars;

    @FormParam("name")
    @Size(min = 2, max = 40)
    private String name;

    @FormParam("adress")
    @NotEmpty
    @Size(min = 4, max = 50)
    private String adress;

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
     * gets Adress
     * @return adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * sets adress
     *
     * @param adress the value to set
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * gets all cars as a Vector
     * @return allCars
     */
    public Vector<Car> getAllCars() {
        return allCars;
    }

    /**
     * sets allCars
     *
     * @param allCars the value to set
     */
    public void setAlleAutos(Vector<Car> allCars) {
        this.allCars = allCars;
    }
}
