package ch.bzz.dealership.service;

import ch.bzz.dealership.data.DataHandler;
import ch.bzz.dealership.model.Car;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@Path("car")
public class CarService {

    @Path("list")
    @GET
    @Produces(APPLICATION_JSON)
    public Response listCar () {
        List<Car> carList = DataHandler.readAllCars();
        Response response = Response
                .status(200)
                .entity(carList)
                .build();
        return response;
    }


    /**
     * reads a car by its Serial Number
     * @param serialNum
     * @return
     * @throws IllegalArgumentException
     */
    @Path("read")
    @GET
    @Produces(APPLICATION_JSON)
    public Response readCar(
            @Pattern(regexp = "SA[2-9]{3}[A-F]{2}")
            @NotEmpty
            @QueryParam("id") String serialNum) throws IllegalArgumentException{
        if(DataHandler.readCarBySerialNum(serialNum) != null){
            Car car = DataHandler.readCarBySerialNum(serialNum);
            Response response = Response
                    .status(200)
                    .entity(car)
                    .build();
            return response;
        } else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * deletes a car identified by its serial number
     * @param serialNum
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCar(
            @Pattern(regexp = "SA[2-9]{3}[A-F]{2}")
            @NotEmpty
            @QueryParam("id") String serialNum
    ){
        int httpStatus = 200;
        if(!DataHandler.deleteAuto(serialNum)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * inserts a new car
     * @return
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertCar(
            @Valid @BeanParam Car car
    ) {
        DataHandler.insertCar(car);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new car
     * @param serialNum
     * @return
     */
    @Path("update")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateCar(
            @Valid @BeanParam Car car,
            @NotEmpty
            @Pattern(regexp = "SA[2-9]{3}[A-F]{2}")
            @FormParam("serialNum") String serialNum
    ){
        int httpStatus = 200;
        Car oldcar = DataHandler.readCarBySerialNum(car.getSerialNum());
        if(oldcar != null){
            oldcar.setModel(car.getModel());
            oldcar.setConsumption(car.getConsumption());
            oldcar.setMileage(car.getMileage());
            oldcar.setHorsepower(car.getHorsepower());
            oldcar.setPrice(car.getPrice());
            oldcar.setSerialNum(serialNum);
            DataHandler.updateCar();
        }else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}

