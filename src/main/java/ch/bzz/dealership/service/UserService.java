package ch.bzz.dealership.service;

import ch.bzz.dealership.data.UserData;
import ch.bzz.dealership.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * services for authentication and authorization of users
 */

@Path("user")
public class UserService {

    /**
     * login with a username and a password
     * @param username
     * @param password
     * @return Response
     */
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    ){
        int httpStatus;

        User user = UserData.findUser(username, password);
        if(user == null || user.getRole() == null || user.getRole().equals("guest")){
            httpStatus = 404;
        }else{
            httpStatus = 200;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                user.getRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false

        );
        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

    /**
     * logout current user
     * @return Response
     */
    @DELETE
    @Path("logoff")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(){

        NewCookie cookie = new NewCookie(
                "userRole",
                "guest",
                "/",
                "",
                "Login-Cookie",
                1,
                false
        );

        Response response = Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }
}

