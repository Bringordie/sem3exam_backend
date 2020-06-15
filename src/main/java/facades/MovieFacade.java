package facades;

import dto.movie.MovieDTO;
import entities.Movie;
import errorhandling.AlreadyExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class MovieFacade {

    private static EntityManagerFactory emf;
    private static MovieFacade instance;

    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    public MovieDTO checkMovie(Movie movie) throws NullPointerException {
        EntityManager em = emf.createEntityManager();
        Movie result = new Movie();
        try {
            TypedQuery<Movie> m = em.createQuery("SELECT m FROM Movie m WHERE "
                    + "m.title = :title and m.year = :year", Movie.class)
                    .setParameter("title", movie.getTitle())
                    .setParameter("year", movie.getYear());
            result = m.getSingleResult();
            if (result != null) {
                em.getTransaction().begin();
                result.setNumberOfSearches(movie.getNumberOfSearches() + 1);
                em.persist(result);
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            //throw new NotFoundException("Movie not found");
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return new MovieDTO(movie);
        } finally {
            em.close();
        }
        return new MovieDTO(result);
    }

    public MovieDTO createMovie(Movie movie) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return new MovieDTO(movie);
        } finally {
            em.close();
        }
    }

    public List<MovieDTO> findMovieByYear(int year) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> m = em.createQuery("SELECT m FROM Movie m WHERE "
                    + "m.year = :year", Movie.class)
                    .setParameter("year", year);

            List<Movie> mlist = m.getResultList();
            List<MovieDTO> mdto = new ArrayList();

            if (mlist.isEmpty()) {
                throw new NotFoundException("No movies by this year was found.");
            } else {
                for (Movie movie : mlist) {
                    mdto.add(new MovieDTO(movie));
                }
                return mdto;
            }
        } finally {
            em.close();
        }
    }

    public List<MovieDTO> findMovieByActor(String actor) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> m = em.createQuery("SELECT m FROM Movie m WHERE "
                    + "m.cast LIKE :actor", Movie.class
            )
                    .setParameter("actor", "%" + actor + "%");

            List<Movie> mlist = m.getResultList();
            List<MovieDTO> mdto = new ArrayList();

            if (mlist.isEmpty()) {
                throw new NotFoundException("No movies with this actor was found.");
            } else {
                for (Movie movie : mlist) {
                    mdto.add(new MovieDTO(movie));
                }
                return mdto;
            }
        } finally {
            em.close();
        }
    }

}
