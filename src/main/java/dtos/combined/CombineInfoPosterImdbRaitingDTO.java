package dtos.combined;

import dtos.moveimdb.MoviePosterDTO;
import dtos.movieinfo.MovieInfoDTO;
import dtos.movierating.ImdbDTO;
import dtos.movierating.MetaCriticsDTO;
import dtos.movierating.TomatoesDTO;
import dtos.movierating.MovieRatingDTO;
import java.io.IOException;

/**
 *
 * @author Frederik
 */
public class CombineInfoPosterImdbRaitingDTO {

    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;
    private double imdbRating;
    private int imdbVotes;

    public CombineInfoPosterImdbRaitingDTO() {
    }

    public CombineInfoPosterImdbRaitingDTO(MovieInfoDTO movieInfo, MoviePosterDTO moviePoster, MovieRatingDTO movieRating) {
        this.title = movieInfo.getTitle();
        this.year = movieInfo.getYear();
        this.plot = movieInfo.getPlot();
        this.directors = movieInfo.getDirectors();
        this.genres = movieInfo.getGenres();
        this.cast = movieInfo.getCast();
        this.poster = moviePoster.getPoster();
        this.imdbRating = movieRating.getImdb().getImdbRating();
        this.imdbVotes = movieRating.getImdb().getImdbVotes();
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
    
    public CombineInfoPosterImdbRaitingDTO fetchImdbMovieDetails(String url) throws IOException {
        MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
        MoviePosterDTO moviePosterDTO = new MoviePosterDTO();
        MovieRatingDTO movieRatingDTO = new MovieRatingDTO();
        CombineInfoPosterImdbRaitingDTO movieDTO = new CombineInfoPosterImdbRaitingDTO(movieInfoDTO.fetchMovieIMDB(url), moviePosterDTO.fetchMovieIMDB(url), movieRatingDTO.fetchMovieIMDB(url));
        return movieDTO;
    }

    @Override
    public String toString() {
        return "CombineInfoPosterImdbRaitingDTO{" + "title=" + title + ", year=" + year + ", plot=" + plot + ", directors=" + directors + ", genres=" + genres + ", cast=" + cast + ", poster=" + poster + ", imdbRating=" + imdbRating + ", imdbVotes=" + imdbVotes + '}';
    }
    
    

    

}
