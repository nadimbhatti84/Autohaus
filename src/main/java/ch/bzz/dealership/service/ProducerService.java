package ch.bzz.dealership.service;

import ch.bzz.dealership.data.DataHandler;
import ch.bzz.dealership.model.Dealership;
import ch.bzz.dealership.model.Producer;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@Path("producer")

public class ProducerService{
    @Path("list")
    @GET
    @Produces(APPLICATION_JSON)
    public Response listProducer () {
        List<Producer> producerList = DataHandler.readAllProducer();
        Response response = Response
                .status(200)
                .entity(producerList)
                .build();
        return response;
    }


    @Path("read")
    @GET
    @Produces(APPLICATION_JSON)
    public Response readProducer(
            @NotEmpty
            @QueryParam("id") String name
    ) throws IllegalArgumentException{
        if(DataHandler.readProducerByName(name) != null){
            Producer producer = DataHandler.readProducerByName(name);
            Response response = Response
                    .status(200)
                    .entity(producer)
                    .build();
            return response;
        } else{
            throw new IllegalArgumentException();
        }
    }
    /**
     * deletes a producer identified by its Name
     * @param name
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProducer(
            @NotEmpty
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
     * inserts a new producer
     * @return
     */
    @Path("create")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertProducer(
            @Valid @BeanParam Producer producer
    ) {
        DataHandler.insertProducer(producer);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new producer
     * @param name
     * @return
     */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProducer(
            @Valid @BeanParam Producer producer,
            @NotEmpty
            @FormParam("name") String name
    ){
        int httpStatus = 200;
        Producer oldproducer = DataHandler.readProducerByName(producer.getName());
        if(oldproducer != null){
            oldproducer.setHeadquarter(producer.getHeadquarter());
            oldproducer.setName(producer.getName());
            DataHandler.updateProducer();
        }else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}


