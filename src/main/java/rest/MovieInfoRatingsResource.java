package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.combined.CombineInfoPosterRaitingDTO;
import java.io.IOException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;


@Path("movie-Info-all-ratings")
public class MovieInfoRatingsResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final CombineInfoPosterRaitingDTO combinedDTO = new CombineInfoPosterRaitingDTO();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;
    
    @GET
    @Path("/{title}")
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public String fetchFullMovie(@PathParam("title") String title) throws IOException, InterruptedException {
        try {
            return GSON.toJson(combinedDTO.fetchAllMovieDetails(title));
        } catch (IOException ex) {
            throw new WebApplicationException(ex.getMessage(), 404);
        } catch (InterruptedException ex) {
            throw new WebApplicationException(ex.getMessage(), 500);
        }

    }
        
}
