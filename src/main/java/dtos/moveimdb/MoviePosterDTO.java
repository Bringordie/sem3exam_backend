package dtos.moveimdb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.movieinfo.MovieInfoDTO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import utils.HttpUtils;

/**
 *
 * @author Frederik
 */
public class MoviePosterDTO {

    private String title;
    private String poster;

    public MoviePosterDTO() {
    }

    public MoviePosterDTO(String title, String poster) {
        this.title = title;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    public MoviePosterDTO fetchMovieIMDB(String url) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // load the properties file
        InputStream sa = MoviePosterDTO.class.getResourceAsStream("/api_urls.properties");
        Properties properties = new Properties();
        properties.load(sa);
        // assign properties parameters
        String path = properties.getProperty("movieposter");
        
        String fetchdata = HttpUtils.fetchData(path+url);
        MoviePosterDTO movieDTO = gson.fromJson(fetchdata, MoviePosterDTO.class);
        return movieDTO;
}

    @Override
    public String toString() {
        return "MoviePosterDTO{" + "title=" + title + ", poster=" + poster + '}';
    }

    
    
}
