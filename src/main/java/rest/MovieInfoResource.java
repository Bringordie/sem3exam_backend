package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.combine_info_poster.CombineInfoPosterDTO;
import errorhandling.AlreadyExistsException;
import errorhandling.NotFoundException;
import facades.UserFacade;
import java.io.IOException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;


//TOO DO ADD AUTHENTICATION
@Path("movie-info")
public class MovieInfoResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final CombineInfoPosterDTO combinedDTO = new CombineInfoPosterDTO();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;
    
    @GET
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(@PathParam("title") String title) throws IOException {
        return GSON.toJson(combinedDTO.fetchMovieDetails(title));
        /*
        try {
            return GSON.toJson(combinedDTO.fetchMovieDetails(title));
        } catch (NotFoundException ex) {
            throw new WebApplicationException(ex.getMessage(), 400);
        }*/
    }

        
}
