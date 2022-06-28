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
    public Response listDealership (
            @CookieParam("userRole") String userRole
    ) {
        List<Dealership> dealershipList = null;
        int httpstatus;
        if(userRole == null || userRole.equals("guest")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            dealershipList = DataHandler.readAllDealerships();
        }
        Response response = Response
                .status(httpstatus)
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
            @CookieParam("userRole") String userRole,
            @NotEmpty
            @QueryParam("name") String name
    ) throws IllegalArgumentException
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
            if(DataHandler.readDealershipByName(name) != null){
                Dealership dealership = DataHandler.readDealershipByName(name);
                Response response = Response
                        .status(httpstatus)
                        .entity(dealership)
                        .build();
                return response;
            } else{
                throw new IllegalArgumentException();
            }
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
            @CookieParam("userRole") String userRole,
            @NotEmpty
            @QueryParam("name") String name
    ){
        int httpstatus;
        if(userRole == null || userRole.equals("guest")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            if(!DataHandler.deleteDealership(name)){
                httpstatus = 410;
            }
        }
        Response response = Response
                .status(httpstatus)
                .entity("")
                .build();
        return response;
    }

    /**
     * inserts a new dealership
     * @return
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertDealership(
            @CookieParam("userRole") String userRole,
            @Valid @BeanParam Dealership dealership
    ) {
        int httpstatus;
        if(userRole == null || userRole.equals("guest")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            DataHandler.insertDealership(dealership);

        }
        Response response = Response
                .status(httpstatus)
                .entity("")
                .build();
        return response;
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
            @CookieParam("userRole") String userRole,
            @Valid @BeanParam Dealership dealership,
            @NotEmpty
            @FormParam("name") String name
    ){
        int httpstatus;
        if(userRole == null || userRole.equals("guest")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            Dealership olddealership = DataHandler.readDealershipByName(dealership.getName());
            if(olddealership != null){
                olddealership.setAdress(dealership.getAdress());
                olddealership.setName(dealership.getName());
                DataHandler.updateDealership();
            }else{
                httpstatus = 410;
            }
        }
        Response response = Response
                .status(httpstatus)
                .entity("")
                .build();
        return response;
    }
}