package dtos.movierating;

/**
 *
 * @author Frederik
 */
public class TomatoesDTO {
    private ViewerDTO viewer;
    private CriticDTO critic;

    public TomatoesDTO() {
    }

    public TomatoesDTO(ViewerDTO viewer, CriticDTO critic) {
        this.viewer = viewer;
        this.critic = critic;
    }

    public ViewerDTO getViewer() {
        return viewer;
    }

    public void setViewer(ViewerDTO viewer) {
        this.viewer = viewer;
    }

    public CriticDTO getCritic() {
        return critic;
    }

    public void setCritic(CriticDTO critic) {
        this.critic = critic;
    }

    @Override
    public String toString() {
        return "TomatoesDTO{" + "viewer=" + viewer + ", critic=" + critic + '}';
    }
    
    
    
}
