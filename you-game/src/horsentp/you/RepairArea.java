/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

/**
 *
 * @author Jonathon
 */
public class RepairArea {
    
    private YouArea youArea;
    
    public RepairArea(YouArea repairing) {
        this.youArea = repairing;
    }
    
    public void repair(float r) {
        youArea.getRepaired(r);
    }
    
    public boolean isRepaired() {
        return youArea.isRepaired();
    }
    
    public Tile getTile() {
        return youArea.getTile();
    }
}
