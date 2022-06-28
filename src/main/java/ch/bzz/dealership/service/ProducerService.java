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

/**
 * All Services available for the producers
 * @author Nadim Bhatti
 * @since 28-06-2022
 * @version 1.0
 */
@Path("producer")
public class ProducerService{

    /**
     * lists all producers
     * @param userRole
     * @return the response with the status and the list
     */
    @Path("list")
    @GET
    @Produces(APPLICATION_JSON)
    public Response listProducer (
            @CookieParam("userRole") String userRole
    ) {
        List<Producer> producerList = null;
        int httpstatus;
        if(userRole == null || userRole.equals("guest")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            producerList = DataHandler.readAllProducer();
        }
        Response response = Response
                .status(httpstatus)
                .entity(producerList)
                .build();
        return response;
    }


    /**
     * lists a single producer by its name
     * @param userRole
     * @param name
     * @return the response with the status
     * @throws IllegalArgumentException
     */
    @Path("read")
    @GET
    @Produces(APPLICATION_JSON)
    public Response readProducer(
            @CookieParam("userRole") String userRole,
            @NotEmpty
            @QueryParam("name") String name
    ) throws IllegalArgumentException
    {
        int httpstatus;
        if(userRole == null || userRole.equals("guest")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            if(DataHandler.readProducerByName(name) != null){
                Producer producer = DataHandler.readProducerByName(name);
                Response response = Response
                        .status(httpstatus)
                        .entity(producer)
                        .build();
                return response;
            } else{
                throw new IllegalArgumentException();
            }
        }
        Response response = Response
                .status(httpstatus)
                .entity("")
                .build();
        return response;
    }
    /**
     * deletes a producer identified by its Name
     * @param name
     * @return the response with its status
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProducer(
            @CookieParam("userRole") String userRole,
            @NotEmpty
            @QueryParam("name") String name
    ){
        int httpstatus;
        if(userRole == null || userRole.equals("guest") || userRole.equals("user")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            if(!DataHandler.deleteProducer(name)){
                httpstatus = 410;
            }
        }
        Response response = Response
                .status(httpstatus)
                .entity("")
                .build();
        return response;
    }

    /**
     * inserts a new producer
     * @return the response with its status
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertProducer(
            @CookieParam("userRole") String userRole,
            @Valid @BeanParam Producer producer
    ) {
        int httpstatus;
        if(userRole == null || userRole.equals("guest") || userRole.equals("user")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            DataHandler.insertProducer(producer);
        }
        Response response = Response
                .status(httpstatus)
                .entity("")
                .build();
        return response;
    }

    /**
     * updates a new producer
     * @param name
     * @return the response with its status
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProducer(
            @CookieParam("userRole") String userRole,
            @Valid @BeanParam Producer producer,
            @FormParam("name") String name
    ){
        int httpstatus;
        if(userRole == null || userRole.equals("guest") || userRole.equals("user")){
            httpstatus = 403;
        }else{
            httpstatus = 200;
            Producer oldproducer = DataHandler.readProducerByName(producer.getName());
            if(oldproducer != null){
                oldproducer.setHeadquarter(producer.getHeadquarter());
                oldproducer.setName(producer.getName());
                DataHandler.updateProducer();
            }else{
                httpstatus = 410;
            }
        }
        Response response = Response
                .status(httpstatus)
                .entity("")
                .build();
        return response;
    }
}


