package dto.movie;

import entities.Movie;
import java.util.Date;

/**
 *
 * @author Frederik
 */
public class MovieDTO {

    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;
    private int numberOfSearches;
    private Date updated;

    public MovieDTO() {
    }

    public MovieDTO(Movie movie) {
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.plot = movie.getPlot();
        this.directors = movie.getDirectors();
        this.genres = movie.getGenres();
        this.cast = movie.getCast();
        this.poster = movie.getPoster();
        this.numberOfSearches = movie.getNumberOfSearches();
        this.updated = movie.getUpdated();
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getNumberOfSearches() {
        return numberOfSearches;
    }

    public void setNumberOfSearches(int numberOfSearches) {
        this.numberOfSearches = numberOfSearches;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "MovieDTO{" + "title=" + title + ", year=" + year + ", plot=" + plot + ", directors=" + directors + ", genres=" + genres + ", cast=" + cast + ", poster=" + poster + ", numberOfSearches=" + numberOfSearches + ", updated=" + updated + '}';
    }
    
    

}
