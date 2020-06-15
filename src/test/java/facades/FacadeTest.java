package facades;

import dto.movie.MovieDTO;
import entities.Movie;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

public class FacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private EntityManager em;

    private Movie m1, m2, m3;

    public FacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = MovieFacade.getMovieFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();

            m1 = new Movie("Title 1", 2020, "plot", "Directors", "genres", "castname1, castname2", "poster");
            m2 = new Movie("Title 2", 2010, "plot", "Directors", "genres", "castname1, castname3", "poster");
            m3 = new Movie("Title 3", 2010, "plot", "Directors", "genres", "castname1", "poster");

            em.persist(m1);
            em.persist(m2);
            em.persist(m3);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    //Something wrong with the iteration of number of searches
    @Test
    public void checkMovieTest() {
        Movie movie = new Movie("Title 12", 2020, "plot", "Directors", "genres", "cast", "poster");
        MovieDTO mdto = facade.checkMovie(movie);
        assertNotNull(mdto);
        assertEquals(1, mdto.getNumberOfSearches());

        MovieDTO mdto2 = facade.checkMovie(movie);
        assertEquals(2, mdto2.getNumberOfSearches());

    }

    @Test
    public void findMovieByYear() throws NotFoundException {
        List<MovieDTO> mlistdto = facade.findMovieByYear(2020);
        assertThat(mlistdto, hasSize(1));

        List<MovieDTO> mlistdto2 = facade.findMovieByYear(2010);
        assertThat(mlistdto2, hasSize(2));

    }
    
    @Test
    public void findMovieByYearFail() throws NotFoundException {
        try {
            List<MovieDTO> mlistdto2 = facade.findMovieByYear(2000);
            fail("Expected a NotFoundException to be thrown");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is("No movies by this year was found."));
        }  
    }
    
    @Test
    public void findMovieByActor() throws NotFoundException {
        List<MovieDTO> mlistdto = facade.findMovieByActor("castname1");
        assertThat(mlistdto, hasSize(3));
        
        List<MovieDTO> mlistdto2 = facade.findMovieByActor("castname2");
        assertThat(mlistdto2, hasSize(1));
    }
    
    @Test
    public void findMovieByActorFail() throws NotFoundException {
        try {
            List<MovieDTO> mlistdto2 = facade.findMovieByActor("thisnamedoesntexist");
            fail("Expected a NotFoundException to be thrown");
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage(), is("No movies with this actor was found."));
        }  
    }

}
