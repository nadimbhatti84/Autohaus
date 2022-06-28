package ch.bzz.dealership.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

/**
 * Model class for a car
 * @author Nadim Bhatti
 * @since 28-06-2022
 * @version 1.0
 */
public class Car {

    @JsonIgnore
    private Producer producer;

    @FormParam("model")
    @NotEmpty
    @Size(min = 1, max = 20)
    private String model;

    @FormParam("consumption")
    @NotNull
    @DecimalMin(value = "1.0")
    @DecimalMax(value = "50.0")
    private double consumption;

    @FormParam("mileage")
    @NotNull
    @Min(0)
    @Max(1000000)
    private int mileage;

    @FormParam("horsepower")
    @NotNull
    @Min(1)
    @Max(2000)
    private int horsepower;

    @FormParam("price")
    @NotNull
    @DecimalMin(value = "10.0")
    @DecimalMax(value = "20000000.0")
    private double price;

    @FormParam("serialNum")
    @NotEmpty
    @Pattern(regexp = "SA[2-9]{3}[A-F]{2}")
    private String serialNum;

    /**
     * gets producer
     * @return producer
     */
    public Producer getProducer() {
        return producer;
    }

    /**
     * sets producer
     *
     * @param producer the value to set
     */
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    /**
     * gets Model
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * sets Model
     *
     * @param model the value to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * gets consumption
     * @return consumption
     */
    public double getConsumption() {
        return consumption;
    }

    /**
     * sets consumption
     *
     * @param consumption the value to set
     */
    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    /**
     * gets mileage
     * @return mileage
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * sets mileage
     *
     * @param mileage the value to set
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * gets horsepower
     * @return horsepower
     */
    public int getHorsepower() {
        return horsepower;
    }

    /**
     * sets horsepower
     *
     * @param horsepower the value to set
     */
    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
     }

    /**
     * gets price
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * sets price
     *
     * @param price the value to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * gets serialNum
     * @return serialNum
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * sets serialNum
     *
     * @param serialNum the value to set
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
}
