/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

import bropals.lib.simplegame.io.AssetManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Jonathon
 */
public class City {
    
    private Tile[][] tiles;
    private AssetManager assets;
    
    public City(AssetManager assets, String path) throws IOException {
        this.assets=assets;
        BufferedReader reader = new BufferedReader(new InputStreamReader(City.class.getResourceAsStream("/" + path)));
        ArrayList<char[]> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line.toCharArray());
        }
        int width = lines.get(0).length;
        int height = lines.size();
        tiles = new Tile[width][height];
        for (int y=0; y<lines.size(); y++) {
            for (int x=0; x<lines.get(y).length; x++) {
                placeTile(x, y, lines.get(y)[x]);
            }
        }
        reader.close();
    }
    
    private void placeTile(int x, int y, char c) {
        switch(c) {
            case 'b':
                tiles[x][y] = new Tile(this, x, y, 15000, 1000, 1000, 1000, assets.getImage("buildings"));
                break;
            case 'm':
                tiles[x][y] = new Tile(this, x, y, 0, 0, 0, 0, assets.getImage("mountain"));
                break;
            case 'f':
                tiles[x][y] = new Tile(this, x, y, 7500, 1000, 1000, 500, assets.getImage("forest"));
                break;
            case 's':
                tiles[x][y] = new Tile(this, x, y, 12000, 1000, 1000, 800, assets.getImage("suburbs"));
                break;
        }
    }
    
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }
    
    public int getWidth() {
        return tiles.length;
    }
    
    public int getHeight() {
        return tiles[0].length;
    }
    
    public boolean tileExists(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }
}
