package ch.bzz.autohaus.model;

import java.util.Vector;

public class Autohaus {
    private String name;
    private String adresse;
    private Vector<Auto> alleAutos;

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
