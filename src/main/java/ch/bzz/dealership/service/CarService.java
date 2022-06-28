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

/**
 * ALl the Services available for the cars
 * @author Nadim Bhatti
 * @since 28-06-2022
 * @version 1.0
 */
@Path("car")
public class CarService {

    /**
     * lists all the cars
     * @param userRole
     * @return response
     */
    @Path("list")
    @GET
    @Produces(APPLICATION_JSON)
    public Response listCar (
            @CookieParam("userRole") String userRole
    ) {
        List<Car> carList = null;
        int httpstatus;
        if(userRole == null || userRole.equals("guest")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            carList = DataHandler.readAllCars();
        }
        Response response = Response
                .status(httpstatus)
                .entity(carList)
                .build();
        return response;
    }


    /**
     * reads a car by its Serial Number
     * @param serialNum
     * @return response
     * @throws IllegalArgumentException
     */
    @Path("read")
    @GET
    @Produces(APPLICATION_JSON)
    public Response readCar(
            @CookieParam("userRole") String userRole,
            @Pattern(regexp = "SA[2-9]{3}[A-F]{2}")
            @NotEmpty
            @QueryParam("id") String serialNum) throws IllegalArgumentException
    {
        int httpstatus;
        if(userRole == null || userRole.equals("guest")){
            httpstatus = 403;
            Response response = Response
                    .status(httpstatus)
                    .entity("")
                    .build();
            return response;
        }else{
            httpstatus = 200;
            if(DataHandler.readCarBySerialNum(serialNum) != null){
                Car car = DataHandler.readCarBySerialNum(serialNum);
                Response response = Response
                        .status(httpstatus)
                        .entity(car)
                        .build();
                return response;
            } else{
                throw new IllegalArgumentException();
            }
        }

    }

    /**
     * deletes a car identified by its serial number
     * @param serialNum
     * @return response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCar(
            @CookieParam("userRole") String userRole,
            @Pattern(regexp = "SA[2-9]{3}[A-F]{2}")
            @NotEmpty
            @QueryParam("id") String serialNum
    ){

        int httpstatus;
        if(userRole == null || userRole.equals("guest") || userRole.equals("user")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            if(!DataHandler.deleteAuto(serialNum)){
                httpstatus = 410;
            }
        }
        return Response
                .status(httpstatus)
                .entity("")
                .build();
    }

    /**
     * inserts a new car
     * @return response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertCar(
            @CookieParam("userRole") String userRole,
            @Valid @BeanParam Car car
    ) {

        int httpstatus;
        if(userRole == null || userRole.equals("guest") || userRole.equals("user")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            DataHandler.insertCar(car);
        }
        return Response
                .status(httpstatus)
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
            @CookieParam("userRole") String userRole,
            @Valid @BeanParam Car car,
            @NotEmpty
            @Pattern(regexp = "SA[2-9]{3}[A-F]{2}")
            @FormParam("serialNum") String serialNum
    ){
        int httpstatus;
        if(userRole == null || userRole.equals("guest") || userRole.equals("user")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
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
                httpstatus = 410;
            }
        }
        return Response
                .status(httpstatus)
                .entity("")
                .build();
    }
}

