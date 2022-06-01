package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
    public Response readAuto(
            @Pattern(regexp = "SA[2-9]{3}[A-F]{2}")
            @NotEmpty
            @QueryParam("id") String seriennummer) throws IllegalArgumentException{
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
            @Pattern(regexp = "SA[2-9]{3}[A-F]{2}")
            @NotEmpty
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
     * @return
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertAuto(
            @Valid @BeanParam Auto auto
    ) {
        DataHandler.insertAuto(auto);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new Auto
     * @param seriennummer
     * @return
     */
    @Path("update")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAuto(
            @Valid @BeanParam Auto auto,
            @NotEmpty
            @Pattern(regexp = "SA[2-9]{3}[A-F]{2}")
            @FormParam("seriennummer") String seriennummer
    ){
        int httpStatus = 200;
        Auto oldauto = DataHandler.readAutoBySeriennummer(auto.getSeriennummer());
        if(oldauto != null){
            auto.setModell(auto.getModell());
            auto.setVerbrauch(auto.getVerbrauch());
            auto.setKilometerstand(auto.getKilometerstand());
            auto.setLeistung(auto.getLeistung());
            auto.setPreis(auto.getPreis());
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

