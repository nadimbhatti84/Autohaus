package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;
import ch.bzz.autohaus.model.Auto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@Path("auto")

public class Autoservice {
    @Path("list")
    @GET
    @Produces(APPLICATION_JSON)
    public Response listAutos () {
        List<Auto> autoList = DataHandler.getInstance().readAllAutos();
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
        if(DataHandler.getInstance().readAutoBySeriennummer(seriennummer) != null){
            Auto auto = DataHandler.getInstance().readAutoBySeriennummer(seriennummer);
            Response response = Response
                    .status(200)
                    .entity(auto)
                    .build();
            return response;
        } else{
            throw new IllegalArgumentException();
        }
    }
}

