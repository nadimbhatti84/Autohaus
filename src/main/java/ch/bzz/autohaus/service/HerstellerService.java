package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Hersteller;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.*;

@Path("hersteller")

public class HerstellerService{
    @Path("list")
    @GET
    @Produces(APPLICATION_JSON)
    public Response listHerstelller () {
        List<Hersteller> herstellerList = DataHandler.readAllHersteller();
        Response response = Response
                .status(200)
                .entity(herstellerList)
                .build();
        return response;
    }


    @Path("read")
    @GET
    @Produces(APPLICATION_JSON)
    public Response readHersteller(@QueryParam("id") String bezeichnung) throws IllegalArgumentException{
        if(DataHandler.readHerstellerByBezeichnung(bezeichnung) != null){
            Hersteller hersteller = DataHandler.readHerstellerByBezeichnung(bezeichnung);
            Response response = Response
                    .status(200)
                    .entity(hersteller)
                    .build();
            return response;
        } else{
            throw new IllegalArgumentException();
        }
    }
    /**
     * deletes a Hersteller identified by its Name
     * @param name
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteHersteller(
            @QueryParam("id") String name
    ){
        int httpStatus = 200;
        if(!DataHandler.deleteHersteller(name)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * inserts a new Hersteller
     * @param bezeichnung
     * @param hauptsitz
     * @return
     */
    @Path("create")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertHersteller(
            @FormParam("bezeichnung") String bezeichnung,
            @FormParam("hauptsitz") String hauptsitz
    ) {
        Hersteller hersteller = new Hersteller();
        hersteller.setBezeichnung(bezeichnung);
        hersteller.setHauptsitz(hauptsitz);

        DataHandler.insertHersteller(hersteller);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new Hersteller
     * @param bezeichnung
     * @param hauptsitz
     * @return
     */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateHersteller(
            @FormParam("bezeichnung") String bezeichnung,
            @FormParam("hauptsitz") String hauptsitz
    ){
        int httpStatus = 200;
        Hersteller hersteller = DataHandler.readHerstellerByBezeichnung(bezeichnung);
        if(hersteller != null){
            hersteller.setHauptsitz(hauptsitz);
            hersteller.setBezeichnung(bezeichnung);
            DataHandler.updateHersteller();
        }else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}


