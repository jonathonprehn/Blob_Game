/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Jonathon
 */
public class Tile {
    
    private float calories;
    private float vitaminH;
    private float vitaminX;
    private float coverage;
    private BufferedImage sprite;
    private City city; //The city this tile is in
    private int x;
    private int y;
    
    public Tile(City city, int x, int y, int calories, int vitaminH, int vitaminX, int coverage, BufferedImage sprite) {
        this.calories = calories;
        this.vitaminH = vitaminH;
        this.vitaminX = vitaminX;
        this.coverage = coverage;
        this.sprite = sprite;
        this.city=city;
        this.x=x;
        this.y=y;
    }
    
    public void reduceCalories(float c) {
        calories -= c;
    }
    
    public void reduceVitaminH(float v) {
        vitaminH -= v;
    }
    
    public void reduceVitaminX(float v) {
        vitaminX -= v;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }
    
    public boolean hasCalories() {
        return calories > 0;
    }
    
    public boolean hasVitaminH() {
        return vitaminH > 0;
    }
    
    public boolean hasVitaminX() {
        return vitaminX > 0;
    }
    
    public float getCoverage() {
        return coverage;
    }
    
    public ArrayList<Tile> getAdjacentTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(city.getTile(x+1, y-1));
        tiles.add(city.getTile(x-1, y-1));
        tiles.add(city.getTile(x+1, y+1));
        tiles.add(city.getTile(x-1, y+1));
        tiles.add(city.getTile(x+1, y));
        tiles.add(city.getTile(x-1, y));
        tiles.add(city.getTile(x, y+1));
        tiles.add(city.getTile(x, y-1));
        return tiles;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    /**
     * Checks for adjacency with diagonals.
     * @param tile the tile to check on
     * @return if the tile is adjacent to this one.
     */
    public boolean isAdjacent(Tile tile) {
        int xDis = Math.abs(x-tile.getX());
        int yDis = Math.abs(y-tile.getY());
        return (xDis==0 || xDis==1) && (yDis==0 && yDis==1) && !(xDis==0 && yDis==0);
    }
    
    /**
     * Checks for adjacency without diagonals.
     * @param tile the tile to check on
     * @return if the tile is adjacent to this one.
     */
    public boolean isAdjacentNoDiagonal(Tile tile) {
        int xDis = Math.abs(x-tile.getX());
        int yDis = Math.abs(y-tile.getY());
        return !(xDis==0 && yDis==0) &&
                ( (xDis==1 && yDis==0) || (xDis==0 && yDis==1) );
    }

    public float getCalories() {
        return calories;
    }

    public float getVitaminH() {
        return vitaminH;
    }

    public float getVitaminX() {
        return vitaminX;
    }
    
    public boolean is(int x, int y) {
        return this.x==x && this.y==y;
    }
    
    public boolean is(Tile tile) {
        return is(tile.getX(), tile.getY());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tile) {
            return ((Tile)obj).is(this);
        } else {
            return false;
        }
    }
    
    
}
