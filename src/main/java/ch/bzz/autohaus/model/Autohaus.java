package ch.bzz.autohaus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Vector;

public class Autohaus {
    @JsonIgnore
    private Vector<Auto> alleAutos;

    private String name;
    private String adresse;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Vector<Auto> getAlleAutos() {
        return alleAutos;
    }

    public void setAlleAutos(Vector<Auto> alleAutos) {
        this.alleAutos = alleAutos;
    }
}
