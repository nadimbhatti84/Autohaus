package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Hersteller;

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
    public Response readHersteller(
            @NotEmpty
            @QueryParam("id") String bezeichnung
    ) throws IllegalArgumentException{
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
     * @param bezeichnung
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteHersteller(
            @NotEmpty
            @QueryParam("id") String bezeichnung
    ){
        int httpStatus = 200;
        if(!DataHandler.deleteHersteller(bezeichnung)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * inserts a new Hersteller
     * @return
     */
    @Path("create")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertHersteller(
            @Valid @BeanParam Hersteller hersteller
    ) {
        DataHandler.insertHersteller(hersteller);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new Hersteller
     * @param bezeichnung
     * @return
     */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateHersteller(
            @Valid @BeanParam Hersteller hersteller,
            @NotEmpty
            @FormParam("id") String bezeichnung
    ){
        int httpStatus = 200;
        Hersteller oldhersteller = DataHandler.readHerstellerByBezeichnung(hersteller.getBezeichnung());
        if(oldhersteller != null){
            oldhersteller.setHauptsitz(hersteller.getHauptsitz());
            oldhersteller.setBezeichnung(hersteller.getBezeichnung());
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


