/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

/**
 *
 * @author Jonathon
 */
public class GrowthArea {
    
    private Tile tile;
    private Tile originTile;
    private float coverage;
    
    public GrowthArea(Tile onTile, Tile fromTile) {
        this.tile = onTile;
        originTile = fromTile;
        coverage = 0;
    }

    public Tile getTile() {
        return tile;
    }
    
    public void grow(float c) {
        coverage += c;
    }
    
    public boolean isLots() {
        return (float)coverage/(float)tile.getCoverage() > YouEdgeManager.LOTS;
    }
    
    public boolean isAlmost() {
        return (float)coverage/(float)tile.getCoverage() > YouEdgeManager.ALMOST;
    }
    
    public boolean willBeLots(float toAdd) {
        return (float)(coverage+toAdd)/(float)tile.getCoverage() > YouEdgeManager.LOTS;
    }
    
    public boolean willBeAlmost(float toAdd) {
        return (float)(coverage+toAdd)/(float)tile.getCoverage() > YouEdgeManager.ALMOST;
    }
    
    public boolean fullyCovered() {
        return coverage >= tile.getCoverage();
    }

    public float getCoverage() {
        return coverage;
    }

    public Tile getOriginTile() {
        return originTile;
    }
    
   
}
