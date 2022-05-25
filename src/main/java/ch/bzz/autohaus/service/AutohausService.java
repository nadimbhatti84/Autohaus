package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;
import ch.bzz.autohaus.model.Autohaus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

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
    public Response readAutohaus(@QueryParam("id") String name) throws IllegalArgumentException{
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
}


