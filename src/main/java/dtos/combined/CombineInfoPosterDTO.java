package dtos.combined;

import dtos.moveimdb.MoviePosterDTO;
import dtos.movieinfo.MovieInfoDTO;
import java.io.IOException;

/**
 *
 * @author Frederik
 */
public class CombineInfoPosterDTO {
    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;

    

    public CombineInfoPosterDTO() {
    }

    public CombineInfoPosterDTO(MovieInfoDTO movieInfo, MoviePosterDTO moviePoster) {
        this.title = movieInfo.getTitle();
        this.year = movieInfo.getYear();
        this.plot = movieInfo.getPlot();
        this.directors = movieInfo.getDirectors();
        this.genres = movieInfo.getGenres();
        this.cast = movieInfo.getCast();
        this.poster = moviePoster.getPoster();
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
    

    public CombineInfoPosterDTO fetchMovieDetails(String url) throws IOException {
        MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
        MoviePosterDTO moviePosterDTO = new MoviePosterDTO();
        CombineInfoPosterDTO movieDTO = new CombineInfoPosterDTO(movieInfoDTO.fetchMovieIMDB(url), moviePosterDTO.fetchMovieIMDB(url));
        return movieDTO;
    }

    @Override
    public String toString() {
        return "CombineInfoPosterDTO{" + "title=" + title + ", year=" + year + ", plot=" + plot + ", directors=" + directors + ", genres=" + genres + ", cast=" + cast + ", poster=" + poster + '}';
    }
        
}
