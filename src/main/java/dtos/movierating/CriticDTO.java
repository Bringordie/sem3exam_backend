package dtos.movierating;

/**
 *
 * @author Frederik
 */
public class CriticDTO {
    private double rating;
    private int numReviews;
    private int meter;

    public CriticDTO() {
    }

    public CriticDTO(double rating, int numReviews, int meter) {
        this.rating = rating;
        this.numReviews = numReviews;
        this.meter = meter;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public int getMeter() {
        return meter;
    }

    public void setMeter(int meter) {
        this.meter = meter;
    }

    @Override
    public String toString() {
        return "CriticDTO{" + "rating=" + rating + ", numReviews=" + numReviews + ", meter=" + meter + '}';
    }
    
    
    
}
