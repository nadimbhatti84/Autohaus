package ch.bzz.autohaus.service;

import ch.bzz.autohaus.data.DataHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * test service
 */
@Path("test")
public class TestService {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {

        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }

    /**
     * restores the json-files
     * @return Response
     */
    @GET
    @Path("restore")
    @Produces(MediaType.TEXT_PLAIN)
    public Response restore() {
        try {
            java.nio.file.Path path = Paths.get(Config.getProperty("autoJSON"));
            String filename = path.getFileName().toString();
            String folder = path.getParent().toString();

            byte[] autoJSON = Files.readAllBytes(Paths.get(folder, "data backup", filename));
            FileOutputStream fileOutputStream = new FileOutputStream(Config.getProperty("autoJSON"));
            fileOutputStream.write(autoJSON);

            path = Paths.get(Config.getProperty("autohauserJSON"));
            filename = path.getFileName().toString();
            folder = path.getParent().toString();

            byte[] autohauserJSON = Files.readAllBytes(Paths.get(folder, "data backup", filename));
            fileOutputStream = new FileOutputStream(Config.getProperty("autohauserJSON"));
            fileOutputStream.write(autohauserJSON);

            path = Paths.get(Config.getProperty("herstellerJSON"));
            filename = path.getFileName().toString();
            folder = path.getParent().toString();

            byte[] herstellerJSON = Files.readAllBytes(Paths.get(folder, "data backup", filename));
            fileOutputStream = new FileOutputStream(Config.getProperty("herstellerJSON"));
            fileOutputStream.write(herstellerJSON);


        } catch (IOException e) {
            e.printStackTrace();
        }

        DataHandler.initLists();
        return Response
                .status(200)
                .entity("Erfolgreich")
                .build();
    }
}
