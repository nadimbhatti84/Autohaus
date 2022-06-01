package ch.bzz.autohaus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.Vector;

public class Autohaus {
    @JsonIgnore
    private Vector<Auto> alleAutos;

    @FormParam("name")
    @Size(min = 2, max = 40)
    private String name;

    @FormParam("adresse")
    @NotEmpty
    @Size(min = 4, max = 50)
    private String adresse;

    /**
     * gets Name
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
     * gets Adresse
     * @return adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * sets adresse
     *
     * @param adresse the value to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * gets alle Autos als Vector
     * @return alleAutos
     */
    public Vector<Auto> getAlleAutos() {
        return alleAutos;
    }

    /**
     * sets alleAutos
     *
     * @param alleAutos the value to set
     */
    public void setAlleAutos(Vector<Auto> alleAutos) {
        this.alleAutos = alleAutos;
    }
}
