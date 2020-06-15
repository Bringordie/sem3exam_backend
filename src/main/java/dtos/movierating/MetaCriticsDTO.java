package dtos.movierating;

/**
 *
 * @author Frederik
 */
public class MetaCriticsDTO {
    private int metacritic;

    public MetaCriticsDTO() {
    }

    public MetaCriticsDTO(int metacritic) {
        this.metacritic = metacritic;
    }

    public int getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(int metacritic) {
        this.metacritic = metacritic;
    }
    

    @Override
    public String toString() {
        return "MetaCriticsDTO{" + "metacritic=" + metacritic + '}';
    }
    
    
    
}
