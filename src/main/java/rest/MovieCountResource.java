package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import errorhandling.NotFoundException;
import facades.MovieFacade;
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


@Path("movie-count")
public class MovieCountResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final MovieFacade facade = MovieFacade.getMovieFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;
    
    @GET
    @Path("/{title}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public String movieAndCount(@PathParam("title") String title) throws NotFoundException {
        try {
            return GSON.toJson(facade.findMovieByTitle(title));
        } catch (NotFoundException ex) {
            throw new WebApplicationException(ex.getMessage(), 404);
        }
    }
    
}
