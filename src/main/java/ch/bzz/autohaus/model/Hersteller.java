package ch.bzz.autohaus.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

public class Hersteller {
    @FormParam("bezeichnung")
    @Size(min = 1, max = 30)
    private String bezeichnung;

    @FormParam("hauptsitz")
    @NotEmpty
    @Size(min = 1, max = 30)
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
