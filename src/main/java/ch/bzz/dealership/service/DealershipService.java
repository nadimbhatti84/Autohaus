package ch.bzz.dealership.service;

import ch.bzz.dealership.data.DataHandler;
import ch.bzz.dealership.model.Car;
import ch.bzz.dealership.model.Dealership;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@Path("dealership")

public class DealershipService {
    @Path("list")
    @GET
    @Produces(APPLICATION_JSON)
    public Response listDealership () {
        List<Dealership> dealershipList = DataHandler.readAllDealerships();
        Response response = Response
                .status(200)
                .entity(dealershipList)
                .build();
        return response;
    }


    /**
     * reads a dealership by its name
     * @param name
     * @return
     * @throws IllegalArgumentException
     */
    @Path("read")
    @GET
    @Produces(APPLICATION_JSON)
    public Response readDealership(
            @NotEmpty
            @QueryParam("name") String name
    ) throws IllegalArgumentException{
        if(DataHandler.readDealershipByName(name) != null){
            Dealership dealership = DataHandler.readDealershipByName(name);
            Response response = Response
                    .status(200)
                    .entity(dealership)
                    .build();
            return response;
        } else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * deletes an dealership identified by its name
     * @param name
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteDealership(
            @NotEmpty
            @QueryParam("name") String name
    ){
        int httpStatus = 200;
        if(!DataHandler.deleteDealership(name)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * inserts a new dealership
     * @return
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertDealership(
            @Valid @BeanParam Dealership dealership
    ) {
        DataHandler.insertDealership(dealership);
        return Response
                .status(200)
                .entity("")
                .build();
    }


    /**
     * updates a new dealership
     * @param name
     * @return
     */
    @Path("update")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateDealership(
            @Valid @BeanParam Dealership dealership,
            @NotEmpty
            @FormParam("name") String name
    ){
        int httpStatus = 200;
        Dealership olddealership = DataHandler.readDealershipByName(dealership.getName());
        if(olddealership != null){
            olddealership.setAdress(dealership.getAdress());
            olddealership.setName(dealership.getName());
            DataHandler.updateDealership();
        }else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}