package ch.bzz.autohaus.data;

import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Autohaus;
import ch.bzz.autohaus.model.Hersteller;
import ch.bzz.autohaus.service.Config;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public final class DataHandler {
    private static List<Auto> autoList;
    private static List<Autohaus> autohauserList;
    private static List<Hersteller> herstellerList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
    }

    /**
     * initialize the lists with the data
     */
    public static void initLists() {
        DataHandler.setHerstellerList(null);
        DataHandler.setAutoList(null);
        DataHandler.setAutohauserList(null);
    }

    /**
     * reads all hersteller
     * @return list of hersteller
     */
    public static List<Hersteller> readAllHersteller(){
        return getHerstellerList();
    }

    /**
     * reads an Auto by its bezeichnung
     * @param bezeichnung
     * @return the Hersteller (null=not found)
     */
    public static Hersteller readHerstellerByBezeichnung(String bezeichnung){
        Hersteller hersteller = null;
        for (Hersteller entry : getHerstellerList()) {
            if (entry.getBezeichnung().equals(bezeichnung)) {
                hersteller = entry;
            }
        }
        return hersteller;
    }

    /**
     * inserts a new book into the herstellerList
     * @param hersteller
     */
    public static void insertHersteller(Hersteller hersteller){
        getHerstellerList().add(hersteller);
        writeHerstellerJSON();
    }

    /**
     * updates the herstellerList
     */
    public static void updateHersteller(){
        writeHerstellerJSON();
    }

    /**
     * deletes a hersteller identified by the bezeichnung
     * @param bezeichnung
     * @return
     */
    public static boolean deleteHersteller(String bezeichnung) {
        Hersteller hersteller = readHerstellerByBezeichnung(bezeichnung);
        if (bezeichnung != null) {
            getHerstellerList().remove(bezeichnung);
            writeHerstellerJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * writes the herstellerList to the JSON-File
     */
    private static void writeHerstellerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String herstellerPath = Config.getProperty("herstellerJSON");
        try {
            fileOutputStream = new FileOutputStream(herstellerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getHerstellerList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the hersteller from the JSON-file
     */
    private static void readHerstellerJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("herstellerJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Hersteller[] herstellers = objectMapper.readValue(jsonData, Hersteller[].class);
            for (Hersteller hersteller : herstellers) {
                getHerstellerList().add(hersteller);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets herstellerList
     *
     * @return value of herstellerList
     */
    private static List<Hersteller> getHerstellerList(){
        if(herstellerList == null){
            setHerstellerList(new ArrayList<>());
            readHerstellerJSON();
        }
        return herstellerList;
    }

    /**
     * sets herstellerList
     *
     * @param herstellerList the value to set
     */
    private static void setHerstellerList(List<Hersteller> herstellerList) {
        DataHandler.herstellerList = herstellerList;
    }


    /**
     * reads all autos
     * @return list of autos
     */
    public static List<Auto> readAllAutos() {
        return getAutoList();
    }

    /**
     * reads an auto by its seriennummer
     * @param seriennummer
     * @return the Auto (null=not found)
     */
    public static Auto readAutoBySeriennummer(String seriennummer) {
        Auto auto = null;

        for (Auto entry : getAutoList()) {
            if (entry.getSeriennummer().equals(seriennummer)) {
                auto = entry;
            }
        }
        return auto;
    }

    /**
     * inserts a new auto into the autoList
     * @param auto
     */
    public static void insertAuto(Auto auto){
        getAutoList().add(auto);
        writeAutoJSON();
    }

    /**
     * writes the autoList to the JSON-File
     */
    private static void writeAutoJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String herstellerPath = Config.getProperty("autoJSON");
        try {
            fileOutputStream = new FileOutputStream(herstellerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getAutoList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * updates the autolist
     */
    public static void updateAuto(){
        writeAutoJSON();
    }

    /**
     * deletes a Auto identifies by the seriennummer
     * @param seriennummer the key
     * @return success = true / false
     */
    public static boolean deleteAuto(String seriennummer){
        Auto auto = readAutoBySeriennummer(seriennummer);
        if(auto != null){
            getAutoList().remove(auto);
            writeAutoJSON();
            return true;
        }else{
            return false;
        }
    }


    /**
     * reads all Autohäuser
     * @return list of autohauser
     */
    public static List<Autohaus> readAllAutohauser() {
        return getAutohauserList();
    }

    /**
     * reads a autohaus by its name
     * @param autohausName
     * @return the Autohaus (null=not found)
     */
    public static Autohaus readAutohausByName(String autohausName) {
        Autohaus autohaus = null;
        for (Autohaus entry : getAutohauserList()) {
            if (entry.getName().equals(autohausName)) {
                autohaus = entry;
            }
        }
        return autohaus;
    }

    /**
     * inserts a new autohaus into the autohauserList
     *
     * @param autohaus the book to be saved
     */
    public static void insertAutohaus(Autohaus autohaus) {
        getAutohauserList().add(autohaus);
        writeAutohauserJSON();
    }

    /**
     * writes the autohauserList to the JSON-File
     */
    private static void writeAutohauserJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String herstellerPath = Config.getProperty("autohauserJSON");
        try {
            fileOutputStream = new FileOutputStream(herstellerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getAutohauserList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * updates the autohauserList
     */
    public static void updateAutohauser() {
        writeAutohauserJSON();
    }


    /**
     * deletes an autohaus identified by the name
     * @param name  the key
     * @return  success=true/false
     */
    public static boolean deleteAutohauser(String name) {
        Autohaus autohaus = readAutohausByName(name);
        if (autohaus != null) {
            getAutohauserList().remove(autohaus);
            writeAutohauserJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the autos from the JSON-file
     */
    private static void readAutoJSON() {
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
    private static void readAutohausJSON() {
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
    private static List<Auto> getAutoList() {
        if(autoList == null){
            setAutoList(new ArrayList<>());
            readAutoJSON();
        }
        return autoList;
    }

    /**
     * sets autoList
     *
     * @param autoList the value to set
     */
    private static void setAutoList(List<Auto> autoList) {
        DataHandler.autoList = autoList;
    }

    /**
     * gets autohauserList
     *
     * @return value of autohauserList
     */
    private static List<Autohaus> getAutohauserList() {
        if(autohauserList == null){
            setAutohauserList(new ArrayList<>());
            readAutohausJSON();
        }
        return autohauserList;
    }

    /**
     * sets autohauserList
     *
     * @param autohauserList the value to set
     */
    private static void setAutohauserList(List<Autohaus> autohauserList) {
        DataHandler.autohauserList = autohauserList;
    }
}