package ch.bzz.autohaus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Auto {

    @JsonIgnore
    private Hersteller hersteller;

    private String modell;
    private double verbrauch;
    private int kilometerstand;
    private int leistung;
    private double preis;
    private String seriennummer;

    /**
     * gets Hersteller
     * @return hersteller
     */
    public Hersteller getHersteller() {
        return hersteller;
    }

    /**
     * sets Hersteller
     *
     * @param hersteller the value to set
     */
    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }

    /**
     * gets Modell
     * @return modell
     */
    public String getModell() {
        return modell;
    }

    /**
     * sets Modell
     *
     * @param modell the value to set
     */
    public void setModell(String modell) {
        this.modell = modell;
    }

    /**
     * gets Verbrauch
     * @return verbrauch
     */
    public double getVerbrauch() {
        return verbrauch;
    }

    /**
     * sets Verbrauch
     *
     * @param verbrauch the value to set
     */
    public void setVerbrauch(double verbrauch) {
        this.verbrauch = verbrauch;
    }

    /**
     * gets Kilometerstand
     * @return kilometerstand
     */
    public int getKilometerstand() {
        return kilometerstand;
    }

    /**
     * sets kilometerstand
     *
     * @param kilometerstand the value to set
     */
    public void setKilometerstand(int kilometerstand) {
        this.kilometerstand = kilometerstand;
    }

    /**
     * gets Leistung
     * @return leistung
     */
    public int getLeistung() {
        return leistung;
    }

    /**
     * sets leistung
     *
     * @param leistung the value to set
     */
    public void setLeistung(int leistung) {
        this.leistung = leistung;
     }

    /**
     * gets Preis
     * @return preis
     */
    public double getPreis() {
        return preis;
    }

    /**
     * sets preis
     *
     * @param preis the value to set
     */
    public void setPreis(double preis) {
        this.preis = preis;
    }

    /**
     * gets Seriennummer
     * @return seriennummer
     */
    public String getSeriennummer() {
        return seriennummer;
    }

    /**
     * sets seriennummer
     *
     * @param seriennummer the value to set
     */
    public void setSeriennummer(String seriennummer) {
        this.seriennummer = seriennummer;
    }
}
