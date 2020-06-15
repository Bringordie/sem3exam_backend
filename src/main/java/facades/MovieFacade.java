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
                result.updateNumberOfSearches();
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

    public List<MovieDTO> findMovieByYear(int year) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        List<MovieDTO> mdto = new ArrayList();
        try {
            TypedQuery<Movie> m = em.createQuery("SELECT m FROM Movie m WHERE "
                    + "m.year = :year", Movie.class)
                    .setParameter("year", year);

            List<Movie> mlist = m.getResultList();

            if (!mlist.isEmpty()) {
                for (Movie movie : mlist) {
                    movie.updateNumberOfSearches();

                    em.getTransaction().begin();
                    em.persist(movie);
                    em.getTransaction().commit();

                    mdto.add(new MovieDTO(movie));
                }
            } else if (mlist.isEmpty()) {
                throw new NotFoundException("No movies by this year was found.");
            }
        } finally {
            em.close();
        }
        return mdto;
    }

    public List<MovieDTO> findMovieByActor(String actor) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        List<MovieDTO> mdto = new ArrayList();
        try {
            TypedQuery<Movie> m = em.createQuery("SELECT m FROM Movie m WHERE "
                    + "m.cast LIKE :actor", Movie.class)
                    .setParameter("actor", "%" + actor + "%");

            List<Movie> mlist = m.getResultList();

            if (!mlist.isEmpty()) {
                for (Movie movie : mlist) {
                    movie.updateNumberOfSearches();

                    em.getTransaction().begin();
                    em.persist(movie);
                    em.getTransaction().commit();

                    mdto.add(new MovieDTO(movie));
                }
            } else if (mlist.isEmpty()) {
                throw new NotFoundException("No movies with this actor was found.");
            }
        } finally {
            em.close();
        }
        return mdto;
    }

    public List<MovieDTO> findMovieByTitle(String title) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        List<MovieDTO> mdto = new ArrayList();
        try {
            TypedQuery<Movie> m = em.createQuery("SELECT m FROM Movie m WHERE "
                    + "m.title LIKE :title", Movie.class)
                    .setParameter("title", "%" + title + "%");

            List<Movie> mlist = m.getResultList();

            if (!mlist.isEmpty()) {
                for (Movie movie : mlist) {
                    movie.updateNumberOfSearches();

                    em.getTransaction().begin();
                    em.persist(movie);
                    em.getTransaction().commit();

                    mdto.add(new MovieDTO(movie));
                }
            } else if (mlist.isEmpty()) {
                throw new NotFoundException("No movies with this title was found.");
            }
        } finally {
            em.close();
        }
        return mdto;
    }

    public static void main(String[] args) throws NotFoundException {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        MovieFacade mf = new MovieFacade();

        //mf.checkMovie(new Movie("Title 2", 2020, "plot", "Directors", "genres", "castname1, castname2", "poster"));
        //mf.checkMovie(new Movie("test", 2020, "plot", "Directors", "genres", "castname1, castname2", "poster"));
        System.out.println(mf.findMovieByTitle("test"));
    }

}
