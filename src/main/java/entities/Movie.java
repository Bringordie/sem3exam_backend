package entities;

import dtos.combined.CombineInfoPosterDTO;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Frederik
 */
@Entity
@NamedQuery(name = "Movie.deleteAllRows", query = "DELETE from Movie")
@Table(name = "Movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;
    private int numberOfSearches;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updated;

    public Movie() {
    }

    public Movie(String title, int year, String plot, String directors, String genres, String cast, String poster) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.directors = directors;
        this.genres = genres;
        this.cast = cast;
        this.poster = poster;
        this.numberOfSearches = 1;
        this.updated = new Date();
    }
    
    public Movie(CombineInfoPosterDTO combinedDTO) {
        this.title = combinedDTO.getTitle();
        this.year = combinedDTO.getYear();
        this.plot = combinedDTO.getPlot();
        this.directors = combinedDTO.getDirectors();
        this.genres = combinedDTO.getGenres();
        this.cast = combinedDTO.getCast();
        this.poster = combinedDTO.getPoster();
        this.numberOfSearches = 1;
        this.updated = new Date();
    }
    
    public void updateNumberOfSearches() {
        ++numberOfSearches;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Movie{" + "id=" + id + ", title=" + title + ", year=" + year + ", plot=" + plot + ", directors=" + directors + ", genres=" + genres + ", cast=" + cast + ", poster=" + poster + ", numberOfSearches=" + numberOfSearches + ", updated=" + updated + '}';
    }
    
    

    
    
    

    
    
}
