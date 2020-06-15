package dtos.movierating;

/**
 *
 * @author Frederik
 */
public class ViewerDTO {
    private double rating;
    private int numReviews;
    private int meter;

    public ViewerDTO() {
    }

    public ViewerDTO(double rating, int numReviews, int meter) {
        this.rating = rating;
        this.numReviews = numReviews;
        this.meter = meter;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
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
        return "ViewerDTO{" + "rating=" + rating + ", numReviews=" + numReviews + ", meter=" + meter + '}';
    }
    
    
}
