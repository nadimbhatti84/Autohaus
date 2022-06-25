package ch.bzz.dealership.data;

import ch.bzz.dealership.model.Car;
import ch.bzz.dealership.model.Dealership;
import ch.bzz.dealership.model.Producer;
import ch.bzz.dealership.model.User;
import ch.bzz.dealership.service.Config;

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
    private static List<Car> carList;
    private static List<Dealership> dealershipList;
    private static List<Producer> producerList;
    private static List<User> userList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
    }

    /**
     * initialize the lists with the data
     */
    public static void initLists() {
        DataHandler.setProducerList(null);
        DataHandler.setCarList(null);
        DataHandler.setDealershipList(null);
    }

    /**
     * reads all producer
     * @return list of producer
     */
    public static List<Producer> readAllProducer(){
        return getProducerList();
    }

    /**
     * reads a Producer by its name
     * @param name
     * @return the producer (null=not found)
     */
    public static Producer readProducerByName(String name){
        Producer producer = null;
        for (Producer entry : getProducerList()) {
            if (entry.getName().equals(name)) {
                producer = entry;
            }
        }
        return producer;
    }

    /**
     * inserts a new producer into the producerList
     * @param producer
     */
    public static void insertProducer(Producer producer){
        getProducerList().add(producer);
        writeProducerJSON();
    }

    /**
     * updates the producerList
     */
    public static void updateProducer(){
        writeProducerJSON();
    }

    /**
     * deletes a producer identified by the name
     * @param name
     * @return
     */
    public static boolean deleteProducer(String name) {
        Producer producer = readProducerByName(name);
        if (producer != null) {
            getProducerList().remove(producer);
            writeProducerJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * writes the producerList to the JSON-File
     */
    private static void writeProducerJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String producerPath = Config.getProperty("herstellerJSON");
        try {
            fileOutputStream = new FileOutputStream(producerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getProducerList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the producer from the JSON-file
     */
    private static void readProducerJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("herstellerJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Producer[] producers = objectMapper.readValue(jsonData, Producer[].class);
            for (Producer producer : producers) {
                getProducerList().add(producer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets producerList
     *
     * @return value of producerList
     */
    private static List<Producer> getProducerList(){
        if(producerList == null){
            setProducerList(new ArrayList<>());
            readProducerJSON();
        }
        return producerList;
    }

    /**
     * sets producerList
     *
     * @param producerList the value to set
     */
    private static void setProducerList(List<Producer> producerList) {
        DataHandler.producerList = producerList;
    }


    /**
     * reads all cars
     * @return list of cars
     */
    public static List<Car> readAllCars() {
        return getCarList();
    }

    /**
     * reads a car by its serial number
     * @param serialNum
     * @return the car (null=not found)
     */
    public static Car readCarBySerialNum(String serialNum) {
        Car car = null;

        for (Car entry : getCarList()) {
            if (entry.getSerialNum().equals(serialNum)) {
                car = entry;
            }
        }
        return car;
    }

    /**
     * inserts a new car into the carList
     * @param car
     */
    public static void insertCar(Car car){
        getCarList().add(car);
        writeCarJSON();
    }

    /**
     * writes the carList to the JSON-File
     */
    private static void writeCarJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String carPath = Config.getProperty("autoJSON");
        try {
            fileOutputStream = new FileOutputStream(carPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getCarList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * updates the carList
     */
    public static void updateCar(){
        writeCarJSON();
    }

    /**
     * deletes a car identified by the serial number
     * @param serialNum the key
     * @return success = true / false
     */
    public static boolean deleteAuto(String serialNum){
        Car car = readCarBySerialNum(serialNum);
        if(car != null){
            getCarList().remove(car);
            writeCarJSON();
            return true;
        }else{
            return false;
        }
    }


    /**
     * reads all dealerships
     * @return list of dealerships
     */
    public static List<Dealership> readAllDealerships() {
        return getDealershipList();
    }

    /**
     * reads a dealership by its name
     * @param name
     * @return the dealership (null=not found)
     */
    public static Dealership readDealershipByName(String name) {
        Dealership dealership = null;
        for (Dealership entry : getDealershipList()) {
            if (entry.getName().equals(name)) {
                dealership = entry;
            }
        }
        return dealership;
    }

    /**
     * inserts a new dealership into the dealershipList
     *
     * @param dealership the dealership to be saved
     */
    public static void insertDealership(Dealership dealership) {
        getDealershipList().add(dealership);
        writeDealershipJSON();
    }

    /**
     * writes the dealershipList to the JSON-File
     */
    private static void writeDealershipJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String dealershipPath = Config.getProperty("autohauserJSON");
        try {
            fileOutputStream = new FileOutputStream(dealershipPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getDealershipList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * updates the dealershipList
     */
    public static void updateDealership() {
        writeDealershipJSON();
    }


    /**
     * deletes a dealership identified by the name
     * @param name  the key
     * @return  success=true/false
     */
    public static boolean deleteDealership(String name) {
        Dealership dealership = readDealershipByName(name);
        if (dealership != null) {
            getDealershipList().remove(dealership);
            writeDealershipJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the car from the JSON-file
     */
    private static void readCarJSON() {
        try {
            String path = Config.getProperty("autoJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Car[] cars = objectMapper.readValue(jsonData, Car[].class);
            for (Car car : cars) {
                getCarList().add(car);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the dealership from the JSON-file
     */
    private static void readDealershipJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("autohauserJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Dealership[] dealerships = objectMapper.readValue(jsonData, Dealership[].class);
            for (Dealership dealership : dealerships) {
                getDealershipList().add(dealership);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets carList
     *
     * @return value of carList
     */
    private static List<Car> getCarList() {
        if(carList == null){
            setCarList(new ArrayList<>());
            readCarJSON();
        }
        return carList;
    }

    /**
     * sets carList
     *
     * @param carList the value to set
     */
    private static void setCarList(List<Car> carList) {
        DataHandler.carList = carList;
    }

    /**
     * gets dealershipList
     *
     * @return value of dealershipList
     */
    private static List<Dealership> getDealershipList() {
        if(dealershipList == null){
            setDealershipList(new ArrayList<>());
            readDealershipJSON();
        }
        return dealershipList;
    }

    /**
     * sets dealershipList
     *
     * @param dealershipList the value to set
     */
    private static void setDealershipList(List<Dealership> dealershipList) {
        DataHandler.dealershipList = dealershipList;
    }

    public String readUserRole(String username, String password) {
        for (User user : getUserList()) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user.getUsername();
            }
        }
        return "guest";
    }

    /**
     * reads the users from the JSON-file
     */
    private static void readUserJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("userJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets userList
     *
     * @return value of userList
     */

    public static List<User> getUserList() {
        if (DataHandler.userList == null) {
            DataHandler.setUserList(new ArrayList<>());
            readUserJSON();
        }
        return userList;
    }

    /**
     * sets userList
     *
     * @param userList the value to set
     */

    public static void setUserList(List<User> userList) {
        DataHandler.userList = userList;
    }
}