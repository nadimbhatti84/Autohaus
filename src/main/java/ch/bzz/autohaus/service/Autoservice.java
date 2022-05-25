package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.*;

@Path("auto")

public class Autoservice {
    @Path("list")
    @GET
    @Produces(APPLICATION_JSON)
    public Response listAutos () {
        List<Auto> autoList = DataHandler.readAllAutos();
        Response response = Response
                .status(200)
                .entity(autoList)
                .build();
        return response;
    }


    @Path("read")
    @GET
    @Produces(APPLICATION_JSON)
    public Response readAuto(@QueryParam("id") String seriennummer) throws IllegalArgumentException{
        if(DataHandler.readAutoBySeriennummer(seriennummer) != null){
            Auto auto = DataHandler.readAutoBySeriennummer(seriennummer);
            Response response = Response
                    .status(200)
                    .entity(auto)
                    .build();
            return response;
        } else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * deletes an Auto identified by its Seriennummer
     * @param seriennummer
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAuto(
            @QueryParam("id") String seriennummer
    ){
        int httpStatus = 200;
        if(!DataHandler.deleteAuto(seriennummer)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * inserts a new Auto
     * @param modell
     * @param verbrauch
     * @param kilometerstand
     * @param leistung
     * @param preis
     * @param seriennummer
     * @return
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertAuto(
            @FormParam("modell") String modell,
            @FormParam("verbrauch") double verbrauch,
            @FormParam("kilometerstand") int kilometerstand,
            @FormParam("leistung") int leistung,
            @FormParam("preis") double preis,
            @FormParam("seriennummer") String seriennummer
    ) {
        Auto auto = new Auto();
        auto.setModell(modell);
        auto.setVerbrauch(verbrauch);
        auto.setKilometerstand(kilometerstand);
        auto.setLeistung(leistung);
        auto.setPreis(preis);
        auto.setSeriennummer(seriennummer);

        DataHandler.insertAuto(auto);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new Auto
     * @param modell
     * @param verbrauch
     * @param kilometerstand
     * @param leistung
     * @param preis
     * @param seriennummer
     * @return
     */
    @Path("update")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAuto(
            @FormParam("modell") String modell,
            @FormParam("verbrauch") double verbrauch,
            @FormParam("kilometerstand") int kilometerstand,
            @FormParam("leistung") int leistung,
            @FormParam("preis") double preis,
            @FormParam("seriennummer") String seriennummer
    ){
        int httpStatus = 200;
        Auto auto = DataHandler.readAutoBySeriennummer(seriennummer);
        if(auto != null){
            auto.setModell(modell);
            auto.setVerbrauch(verbrauch);
            auto.setKilometerstand(kilometerstand);
            auto.setLeistung(leistung);
            auto.setPreis(preis);
            auto.setSeriennummer(seriennummer);
            DataHandler.updateAuto();
        }else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}

