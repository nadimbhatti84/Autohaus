package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Autohaus;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
    public Response readAutohaus(
            @NotEmpty
            @QueryParam("name") String name
    ) throws IllegalArgumentException{
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
            @NotEmpty
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
     * @return
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertBook(
            @Valid @BeanParam Autohaus autohaus
    ) {
        DataHandler.insertAutohaus(autohaus);
        return Response
                .status(200)
                .entity("")
                .build();
    }


    /**
     * updates a new Autohaus
     * @param name
     * @return
     */
    @Path("update")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBook(
            @Valid @BeanParam Autohaus autohaus,
            @NotEmpty
            @FormParam("name") String name
    ){
        int httpStatus = 200;
        Autohaus oldautohaus = DataHandler.readAutohausByName(autohaus.getName());
        if(oldautohaus != null){
            oldautohaus.setAdresse(autohaus.getAdresse());
            oldautohaus.setName(autohaus.getName());
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


