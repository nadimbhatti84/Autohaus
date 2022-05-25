package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Autohaus;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.*;

@Path("autohaus")

public class AutohausService {
    @Path("list")
    @GET
    @Produces(APPLICATION_JSON)
    public Response listAutohauser () {
        List<Autohaus> autohausList = DataHandler.readAllAutohauser();
        Response response = Response
                .status(200)
                .entity(autohausList)
                .build();
        return response;
    }


    @Path("read")
    @GET
    @Produces(APPLICATION_JSON)
    public Response readAutohaus(@QueryParam("name") String name) throws IllegalArgumentException{
        if(DataHandler.readAutohausByName(name) != null){
            Autohaus autohaus = DataHandler.readAutohausByName(name);
            Response response = Response
                    .status(200)
                    .entity(autohaus)
                    .build();
            return response;
        } else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * deletes an Autohaus identified by its Name
     * @param name
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAutohaus(
            @QueryParam("name") String name
    ){
        int httpStatus = 200;
        if(!DataHandler.deleteAutohauser(name)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * inserts a new Autohaus
     * @param name
     * @param adresse
     * @return
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertBook(
            @FormParam("name") String name,
            @FormParam("adresse") String adresse
    ) {
        Autohaus autohaus = new Autohaus();
        autohaus.setName(name);
        autohaus.setAdresse(adresse);

        DataHandler.insertAutohaus(autohaus);
        return Response
                .status(200)
                .entity("")
                .build();
    }


    /**
     * updates a new Autohaus
     * @param name
     * @param adresse
     * @return
     */
    @Path("update")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBook(
            @FormParam("name") String name,
            @FormParam("adresse") String adresse
    ){
        int httpStatus = 200;
        Autohaus autohaus = DataHandler.readAutohausByName(name);
        if(autohaus != null){
            autohaus.setAdresse(adresse);
            autohaus.setName(name);
            DataHandler.updateAutohauser();
        }else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}


