package dtos.movierating;

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
public class ImdbDTO {
    private double imdbRating;
    private int imdbVotes;

    public ImdbDTO() {
    }

    public ImdbDTO(int imdbRating, int imdbVotes) {
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }
    

    @Override
    public String toString() {
        return "ImdbDTO{" + "imdbRating=" + imdbRating + ", imdbVotes=" + imdbVotes + '}';
    }
        
    
}
