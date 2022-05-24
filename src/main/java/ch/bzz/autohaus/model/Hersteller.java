package ch.bzz.autohaus.model;

public class Hersteller {
    private String bezeichnung;
    private String hauptsitz;

    /**
     * gets Bezeichnung
     * @return bezeichnung
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * sets bezeichnung
     *
     * @param bezeichnung the value to set
     */
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    /**
     * gets Hauptsitz
     * @return hauptsitz
     */
    public String getHauptsitz() {
        return hauptsitz;
    }

    /**
     * sets hauptsitz
     *
     * @param hauptsitz the value to set
     */
    public void setHauptsitz(String hauptsitz) {
        this.hauptsitz = hauptsitz;
    }
}
