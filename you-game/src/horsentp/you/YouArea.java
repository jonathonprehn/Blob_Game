/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

/**
 *
 * @author Jonathon
 */
public class YouArea {
    
    public static final int MAX_DURABILITY = 100;
    
    private Tile tile;
    private float durability;
    
    public YouArea(Tile onTile) {
        this.tile = onTile;
        durability = MAX_DURABILITY;
    }

    public float getDurability() {
        return durability;
    }

    public Tile getTile() {
        return tile;
    }
    
    public void getHurt(int h) {
        durability -= h;
    }
    
    public void getRepaired(float r) {
        durability += r;
        if (durability > MAX_DURABILITY) {
            durability = MAX_DURABILITY;
        }
    }
    
    public boolean isRepaired() {
        return durability == MAX_DURABILITY;
    }
    
    public boolean isDead() {
        return durability <= 0;
    }
}
