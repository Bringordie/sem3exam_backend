package dtos.movieinfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import utils.HttpUtils;

/**
 *
 * @author Frederik
 */
public class MovieInfoDTO {
    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;

    public MovieInfoDTO() {
    }
    

    public MovieInfoDTO(String title, int year, String plot, String directors, String genres, String cast) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public MovieInfoDTO fetchMovieIMDB(String url) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // load the properties file
        InputStream sa = MovieInfoDTO.class.getResourceAsStream("/api_urls.properties");
        Properties properties = new Properties();
        properties.load(sa);
        // assign properties parameters
        String path = properties.getProperty("movieinfo");
        
        String fetchdata = HttpUtils.fetchData(path+url);
        MovieInfoDTO movieDTO = gson.fromJson(fetchdata, MovieInfoDTO.class);
        return movieDTO;
}

    @Override
    public String toString() {
        return "MovieInfoDTO{" + "title=" + title + ", year=" + year + ", plot=" + plot + ", directors=" + directors + ", genres=" + genres + ", cast=" + cast + '}';
    }
    
    
}
