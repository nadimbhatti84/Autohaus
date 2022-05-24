package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Hersteller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@Path("hersteller")

public class HerstellerService{
    @Path("list")
    @GET
    @Produces(APPLICATION_JSON)
    public Response listHerstelller () {
        List<Hersteller> herstellerList = DataHandler.getInstance().readAllHersteller();
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
        if(DataHandler.getInstance().readHerstellerByBezeichnung(bezeichnung) != null){
            Hersteller hersteller = DataHandler.getInstance().readHerstellerByBezeichnung(bezeichnung);
            Response response = Response
                    .status(200)
                    .entity(hersteller)
                    .build();
            return response;
        } else{
            throw new IllegalArgumentException();
        }
    }
}


