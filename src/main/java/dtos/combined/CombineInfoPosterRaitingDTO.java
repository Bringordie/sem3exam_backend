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
public class CombineInfoPosterRaitingDTO {

    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;
    private double imdbRating;
    private int imdbVotes;
    private int metacriticsRating;
    private double tomatoesViewerRating;
    private int tomatoesViewerRatingNum;
    private double tomatoesCriticRating;
    private int tomatoesCriticRatingNum;

    public CombineInfoPosterRaitingDTO() {
    }

    public CombineInfoPosterRaitingDTO(MovieInfoDTO movieInfo, MoviePosterDTO moviePoster, MovieRatingDTO movieRating) {
        this.title = movieInfo.getTitle();
        this.year = movieInfo.getYear();
        this.plot = movieInfo.getPlot();
        this.directors = movieInfo.getDirectors();
        this.genres = movieInfo.getGenres();
        this.cast = movieInfo.getCast();
        this.poster = moviePoster.getPoster();
        this.imdbRating = movieRating.getImdb().getImdbRating();
        this.imdbVotes = movieRating.getImdb().getImdbVotes();
        this.metacriticsRating = movieRating.getMetacritics().getMetacritic();
        this.tomatoesViewerRating = movieRating.getTomatoes().getViewer().getRating();
        this.tomatoesViewerRatingNum = movieRating.getTomatoes().getViewer().getNumReviews();
        this.tomatoesCriticRating = movieRating.getTomatoes().getCritic().getRating();
        this.tomatoesCriticRatingNum = movieRating.getTomatoes().getCritic().getNumReviews();
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

    public int getMetacriticsRating() {
        return metacriticsRating;
    }

    public void setMetacriticsRating(int metacriticsRating) {
        this.metacriticsRating = metacriticsRating;
    }

    public double getTomatoesViewerRating() {
        return tomatoesViewerRating;
    }

    public void setTomatoesViewerRating(double tomatoesViewerRating) {
        this.tomatoesViewerRating = tomatoesViewerRating;
    }

    public int getTomatoesViewerRatingNum() {
        return tomatoesViewerRatingNum;
    }

    public void setTomatoesViewerRatingNum(int tomatoesViewerRatingNum) {
        this.tomatoesViewerRatingNum = tomatoesViewerRatingNum;
    }

    public double getTomatoesCriticRating() {
        return tomatoesCriticRating;
    }

    public void setTomatoesCriticRating(double tomatoesCriticRating) {
        this.tomatoesCriticRating = tomatoesCriticRating;
    }

    public int getTomatoesCriticRatingNum() {
        return tomatoesCriticRatingNum;
    }

    public void setTomatoesCriticRatingNum(int tomatoesCriticRatingNum) {
        this.tomatoesCriticRatingNum = tomatoesCriticRatingNum;
    }
    
    public CombineInfoPosterRaitingDTO fetchAllMovieDetails(String url) throws IOException {
        MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
        MoviePosterDTO moviePosterDTO = new MoviePosterDTO();
        MovieRatingDTO movieRatingDTO = new MovieRatingDTO();
        CombineInfoPosterRaitingDTO movieDTO = new CombineInfoPosterRaitingDTO(movieInfoDTO.fetchMovieIMDB(url), moviePosterDTO.fetchMovieIMDB(url), movieRatingDTO.fetchMovieRatingAll(url));
        return movieDTO;
    }

    @Override
    public String toString() {
        return "CombineInfoPosterRaitingDTO{" + "title=" + title + ", year=" + year + ", plot=" + plot + ", directors=" + directors + ", genres=" + genres + ", cast=" + cast + ", poster=" + poster + ", imdbRating=" + imdbRating + ", imdbVotes=" + imdbVotes + ", metacriticsRating=" + metacriticsRating + ", tomatoesViewerRating=" + tomatoesViewerRating + ", tomatoesViewerRatingNum=" + tomatoesViewerRatingNum + ", tomatoesCriticRating=" + tomatoesCriticRating + ", tomatoesCriticRatingNum=" + tomatoesCriticRatingNum + '}';
    }

    

}
