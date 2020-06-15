package dtos.movierating;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.combined.DTOInterface;
import errorhandling.NotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.HttpUtils;

/**
 *
 * @author Frederik
 */
public class MovieRatingDTO implements DTOInterface{
    private String title;
    private ImdbDTO imdb;
    private MetaCriticsDTO metacritics;
    private TomatoesDTO tomatoes;

    public MovieRatingDTO() {
    }

    public MovieRatingDTO(String title, ImdbDTO imdb, MetaCriticsDTO metacritics, TomatoesDTO tomatoes) {
        this.title = title;
        this.imdb = imdb;
        this.metacritics = metacritics;
        this.tomatoes = tomatoes;
    }
    
    public MovieRatingDTO(String title, ImdbDTO imdb) {
        this.title = title;
        this.imdb = imdb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImdbDTO getImdb() {
        return imdb;
    }

    public void setImdb(ImdbDTO imdb) {
        this.imdb = imdb;
    }

    public MetaCriticsDTO getMetacritics() {
        return metacritics;
    }

    public void setMetacritics(MetaCriticsDTO metacritics) {
        this.metacritics = metacritics;
    }

    public TomatoesDTO getTomatoes() {
        return tomatoes;
    }

    public void setTomatoes(TomatoesDTO tomatoes) {
        this.tomatoes = tomatoes;
    }
    
    public MovieRatingDTO fetchMovieRatingAll(String url) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // load the properties file
        InputStream sa = MovieRatingDTO.class.getResourceAsStream("/api_urls.properties");
        Properties properties = new Properties();
        properties.load(sa);
        // assign properties parameters
        String path = properties.getProperty("movieratings");
        
        String fetchdata = HttpUtils.fetchData(path+url+"/imt");
        MovieRatingDTO movieDTO = gson.fromJson(fetchdata, MovieRatingDTO.class);
        return movieDTO;
}
    
    public MovieRatingDTO fetchMovieIMDB(String url) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // load the properties file
        InputStream sa = MovieRatingDTO.class.getResourceAsStream("/api_urls.properties");
        Properties properties = new Properties();
        properties.load(sa);
        // assign properties parameters
        String path = properties.getProperty("movieratings");
        
        String fetchdata = HttpUtils.fetchData(path+url+"/i");
        MovieRatingDTO movieDTO = gson.fromJson(fetchdata, MovieRatingDTO.class);
        return movieDTO;
}

    @Override
    public String toString() {
        return "MovieRatingDTO{" + "title=" + title + ", imdb=" + imdb + ", metacritics=" + metacritics + ", tomatoes=" + tomatoes + '}';
    }

    @Override
    public void fetch(String url) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // load the properties file
        InputStream sa = MovieRatingDTO.class.getResourceAsStream("/api_urls.properties");
        Properties properties = new Properties();
        properties.load(sa);
        // assign properties parameters
        String path = properties.getProperty("movieratings");
        
        String fetchdata = HttpUtils.fetchData(path+url+"/imt");
        MovieRatingDTO movieDTO = gson.fromJson(fetchdata, MovieRatingDTO.class);
        this.title = movieDTO.getTitle();
        this.imdb = movieDTO.getImdb();
        this.metacritics = movieDTO.getMetacritics();
        this.tomatoes = movieDTO.getTomatoes();
        } catch (IOException ex) {
            Logger.getLogger(MovieRatingDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
