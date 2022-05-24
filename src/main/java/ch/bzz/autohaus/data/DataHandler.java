package ch.bzz.autohaus.data;

import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Autohaus;
import ch.bzz.autohaus.model.Hersteller;
import ch.bzz.autohaus.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Auto> autoList;
    private List<Autohaus> autohauserList;
    private List<Hersteller> herstellerList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setAutohauserList(new ArrayList<>());
        readAutohausJSON();
        setAutoList(new ArrayList<>());
        readAutoJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    public List<Hersteller> readAllHersteller(){
        return getHerstellerList();
    }

    public Hersteller readHerstellerByBezeichnung(String bezeichnung){
        Hersteller hersteller = null;
        for (Hersteller entry : getHerstellerList()) {
            if (entry.getBezeichnung().equals(bezeichnung)) {
                hersteller = entry;
            }
        }
        return hersteller;
    }

    private List<Hersteller> getHerstellerList(){
        return herstellerList;
    }


    /**
     * reads all autos
     * @return list of autos
     */
    public List<Auto> readAllAutos() {
        return getAutoList();
    }

    /**
     * reads an auto by its seriennummer
     * @param seriennummer
     * @return the Auto (null=not found)
     */
    public Auto readAutoBySeriennummer(String seriennummer) {
        Auto auto = null;
        for (Auto entry : getAutoList()) {
            if (entry.getSeriennummer().equals(seriennummer)) {
                auto = entry;
            }
        }
        return auto;
    }

    /**
     * reads all Autohäuser
     * @return list of autohauser
     */
    public List<Autohaus> readAllAutohauser() {
        return getAutohauserList();
    }

    /**
     * reads a autohaus by its name
     * @param autohausName
     * @return the Autohaus (null=not found)
     */
    public Autohaus readAutohausByName(String autohausName) {
        Autohaus autohaus = null;
        for (Autohaus entry : getAutohauserList()) {
            if (entry.getName().equals(autohausName)) {
                autohaus = entry;
            }
        }
        return autohaus;
    }

    /**
     * reads the autos from the JSON-file
     */
    private void readAutoJSON() {
        try {
            String path = Config.getProperty("autoJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Auto[] autos = objectMapper.readValue(jsonData, Auto[].class);
            for (Auto auto : autos) {
                getAutoList().add(auto);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the autohauser from the JSON-file
     */
    private void readAutohausJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("autohauserJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Autohaus[] autohauser = objectMapper.readValue(jsonData, Autohaus[].class);
            for (Autohaus autohaus : autohauser) {
                getAutohauserList().add(autohaus);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets autoList
     *
     * @return value of autoList
     */
    private List<Auto> getAutoList() {
        return autoList;
    }

    /**
     * sets autoList
     *
     * @param autoList the value to set
     */
    private void setAutoList(List<Auto> autoList) {
        this.autoList = autoList;
    }

    /**
     * gets autohauserList
     *
     * @return value of autohauserList
     */
    private List<Autohaus> getAutohauserList() {
        return autohauserList;
    }

    /**
     * sets autohauserList
     *
     * @param autohauserList the value to set
     */
    private void setAutohauserList(List<Autohaus> autohauserList) {
        this.autohauserList = autohauserList;
    }
}