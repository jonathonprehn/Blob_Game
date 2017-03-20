/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

import bropals.lib.simplegame.animation.Animation;
import bropals.lib.simplegame.animation.Track;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Controls the drawing of "you" edges.
 * @author Jonathon
 */
public class YouEdgeManager {
    public static final float LOTS = 0.33f;
    public static final float ALMOST = 0.66f;
    public static final int EDGE_MILLIS = 200;
    
    private Track youTopLeft;
    private Track youTopRight;
    private Track youBottomLeft;
    private Track youBottomRight;
    private Track youLeft;
    private Track youRight;
    private Track youTop;
    private Track youBottom;
    private Track youGrowingToTheLeftKinda;
    private Track youGrowingToTheLeftLots;
    private Track youGrowingToTheLeftAlmost;
    private Track youGrowingToTheRightKinda;
    private Track youGrowingToTheRightLots;
    private Track youGrowingToTheRightAlmost;
    private Track youGrowingToTheTopKinda;
    private Track youGrowingToTheTopLots;
    private Track youGrowingToTheTopAlmost;
    private Track youGrowingToTheBottomKinda;
    private Track youGrowingToTheBottomLots;
    private Track youGrowingToTheBottomAlmost;
    private Track youLeftAndBottomAndTopRight;
    private Track youLeftAndBottomAndRight;
    private Track youLeftAndBottomAndTop;
    private Track youLeftAndBottom;
    private Track youLeftAndRight;
    private Track youLeftAndTopAndBottomRight;
    private Track youLeftAndTop;
    private Track youRightAndBottomAndTopLeft;
    private Track youRightAndBottom;
    private Track youRightAndTopAndBottomLeft;
    private Track youRightAndTop;
    private Track youTopAndBottomAndRight;
    private Track youTopAndBottom;
    private Track youTopAndLeftAndRight;
    private Track youTopAndLeftAndRightAndBottom;
    private Track youTopRightAndBottomLeft;
    private Track youTopLeftAndTopRight;
    private Track youTopLeftAndTopRightAndBottomLeft;
    private Track youTopLeftAndBottomRight;
    private Track youTopLeftAndBottomLeft;
    private Track youTopRightAndBottomRight;
    private Track youBottomLeftAndBottomRight;
    private Track youTopLeftAndTopRightAndBottomRight;
    private Track youTopRightAndBottomLeftAndBottomRight;
    private Track youTopLeftAndTopRightAndBottomLeftAndBottomRight;
    private Track youTopLeftAndBottom;
    private Track youTopLeftAndTopRightAndBottom;
    private Track youTopRightAndBottom;
    private Track youTopRightAndLeft;
    private Track youTopRightAndLeftAndBottomRight;
    private Track youLeftAndBottomRight;
    private Track youTopAndBottomRight;
    private Track youTopAndBottomLeftAndBottomRight;
    private Track youTopAndBottomLeft;
    private Track youRightAndBottomLeft;
    private Track youTopLeftAndRightAndBottomLeft;
    private Track youTopLeftAndRight;
    
    private City city;
    private ArrayList<YouEdge> edges = new ArrayList<>();
    private YouGameState youGameState;

    public YouEdgeManager(City city, YouGameState g) {
        this.city = city;
        this.youGameState = g;
        youTopLeft = makeTrack("youTopLeft");
        youTopRight = makeTrack("youTopRight");
        youBottomLeft = makeTrack("youBottomLeft");
        youBottomRight = makeTrack("youBottomRight");
        youLeft = makeTrack("youLeft");
        youRight = makeTrack("youRight");
        youTop = makeTrack("youTop");
        youBottom = makeTrack("youBottom");
        youGrowingToTheLeftKinda = makeTrack("youGrowingToTheLeftKinda");
        youGrowingToTheLeftLots = makeTrack("youGrowingToTheLeftLots");
        youGrowingToTheLeftAlmost = makeTrack("youGrowingToTheLeftAlmost");
        youGrowingToTheRightKinda = makeTrack("youGrowingToTheRightKinda");
        youGrowingToTheRightLots = makeTrack("youGrowingToTheRightLots");
        youGrowingToTheRightAlmost = makeTrack("youGrowingToTheRightAlmost");
        youGrowingToTheTopKinda = makeTrack("youGrowingToTheTopKinda");
        youGrowingToTheTopLots = makeTrack("youGrowingToTheTopLots");
        youGrowingToTheTopAlmost = makeTrack("youGrowingToTheTopAlmost");
        youGrowingToTheBottomKinda = makeTrack("youGrowingToTheBottomKinda");
        youGrowingToTheBottomLots = makeTrack("youGrowingToTheBottomLots");
        youGrowingToTheBottomAlmost = makeTrack("youGrowingToTheBottomAlmost");
        youLeftAndBottomAndTopRight = makeTrack("youLeftAndBottomAndTopRight");
        youLeftAndBottomAndRight = makeTrack("youLeftAndBottomAndRight");
        youLeftAndBottomAndTop = makeTrack("youLeftAndBottomAndTop");
        youLeftAndBottom = makeTrack("youLeftAndBottom");
        youLeftAndRight = makeTrack("youLeftAndRight");
        youLeftAndTopAndBottomRight = makeTrack("youLeftAndTopAndBottomRight");
        youLeftAndTop = makeTrack("youLeftAndTop");
        youRightAndBottomAndTopLeft = makeTrack("youRightAndBottomAndTopLeft");
        youRightAndBottom = makeTrack("youRightAndBottom");
        youRightAndTopAndBottomLeft = makeTrack("youRightAndTopAndBottomLeft");
        youRightAndTop = makeTrack("youRightAndTop");
        youTopAndBottomAndRight = makeTrack("youTopAndBottomAndRight");
        youTopAndBottom = makeTrack("youTopAndBottom");
        youTopAndLeftAndRight = makeTrack("youTopAndLeftAndRight");
        youTopAndLeftAndRightAndBottom = makeTrack("youTopAndLeftAndRightAndBottom");
        youTopRightAndBottomLeft = makeTrack("youTopRightAndBottomLeft");
        youTopLeftAndTopRight = makeTrack("youTopLeftAndTopRight");
        youTopLeftAndTopRightAndBottomLeft = makeTrack("youTopLeftAndTopRightAndBottomLeft");
        youTopLeftAndBottomRight = makeTrack("youTopLeftAndBottomRight");
        youTopLeftAndBottomLeft = makeTrack("youTopLeftAndBottomLeft");
        youTopRightAndBottomRight = makeTrack("youTopRightAndBottomRight");
        youBottomLeftAndBottomRight = makeTrack("youBottomLeftAndBottomRight");
        youTopLeftAndTopRightAndBottomRight = makeTrack("youTopLeftAndTopRightAndBottomRight");
        youTopRightAndBottomLeftAndBottomRight = makeTrack("youTopRightAndBottomLeftAndBottomRight");
        youTopLeftAndTopRightAndBottomLeftAndBottomRight = makeTrack("youTopLeftAndTopRightAndBottomLeftAndBottomRight");
        youTopLeftAndBottom = makeTrack("youTopLeftAndBottom");
        youTopLeftAndTopRightAndBottom = makeTrack("youTopLeftAndTopRightAndBottom");
        youTopRightAndBottom = makeTrack("youTopRightAndBottom");
        youTopRightAndLeft = makeTrack("youTopRightAndLeft");
        youTopRightAndLeftAndBottomRight = makeTrack("youTopRightAndLeftAndBottomRight");
        youLeftAndBottomRight = makeTrack("youLeftAndBottomRight");
        youTopAndBottomRight = makeTrack("youTopAndBottomRight");
        youTopAndBottomLeftAndBottomRight = makeTrack("youTopAndBottomLeftAndBottomRight");
        youTopAndBottomLeft = makeTrack("youTopAndBottomLeft");
        youRightAndBottomLeft = makeTrack("youRightAndBottomLeft");
        youTopLeftAndRightAndBottomLeft = makeTrack("youTopLeftAndRightAndBottomLeft");
        youTopLeftAndRight = makeTrack("youTopLeftAndRight");
    }
        
    public void update(int dt) {
        for (YouEdge edge : edges) {
            edge.update(dt);
        }
    }
    
    public void renderEdges(Graphics g) {
        for (YouEdge edge : edges) {
            edge.draw(g);
        }
    }
    
    /**
     * Updates the edges so that they fit you.
     */
    public void refreshEdges() {
        edges.clear();
        ArrayList<GrowthArea> growthAreas = youGameState.getYou().getGrowingAreas();
        ArrayList<Tile> updatedTiles = new ArrayList<>();
        //Do the growth tiles first
        for (GrowthArea ga : growthAreas) {
            int diffX = ga.getTile().getX()-ga.getOriginTile().getX();
            int diffY = ga.getTile().getY()-ga.getOriginTile().getY();
            float percent = (float)ga.getCoverage()/(float)ga.getTile().getCoverage();
            Animation anim = new Animation();
            Track t = null;
            if (diffX == 1) {
                //To the right
                if (percent > ALMOST) {
                    anim.addTrack(t = youGrowingToTheRightAlmost.clone());
                    anim.setTrack(0);
                } else if (percent > LOTS) {
                    anim.addTrack(t = youGrowingToTheRightLots.clone());
                    anim.setTrack(0);
                } else {
                    anim.addTrack(t = youGrowingToTheRightKinda.clone());
                    anim.setTrack(0);
                }
            } else if (diffX == -1) {
                //To the left
                if (percent > ALMOST) {
                    anim.addTrack(t = youGrowingToTheLeftAlmost.clone());
                    anim.setTrack(0);
                } else if (percent > LOTS) {
                    anim.addTrack(t = youGrowingToTheLeftLots.clone());
                    anim.setTrack(0);
                } else {
                    anim.addTrack(t = youGrowingToTheLeftKinda.clone());
                    anim.setTrack(0);
                }
            } else if (diffY == 1) {
                //To the bottom
                if (percent > ALMOST) {
                    anim.addTrack(t = youGrowingToTheBottomAlmost.clone());
                    anim.setTrack(0);
                } else if (percent > LOTS) {
                    anim.addTrack(t = youGrowingToTheBottomLots.clone());
                    anim.setTrack(0);
                } else {
                    anim.addTrack(t = youGrowingToTheBottomKinda.clone());
                    anim.setTrack(0);
                }
            } else if (diffY == -1) {
                //To the top
                if (percent > ALMOST) {
                    anim.addTrack(t = youGrowingToTheTopAlmost.clone());
                    anim.setTrack(0);
                } else if (percent > LOTS) {
                    anim.addTrack(t = youGrowingToTheTopLots.clone());
                    anim.setTrack(0);
                } else {
                    anim.addTrack(t = youGrowingToTheTopKinda.clone());
                    anim.setTrack(0);
                }
            }
            if (t != null) {
                anim.update(0);
            }
            YouEdge edge = new YouEdge(anim, ga.getTile(), ga.getOriginTile().getX(), ga.getOriginTile().getY());
            updatedTiles.add(ga.getTile());
            edges.add(edge);
        }
        //Now update all the tiles
        Track track = null;
        for (int x=0; x<city.getWidth(); x++) {
            for (int y=0; y<city.getHeight(); y++) {
                Tile t = city.getTile(x, y);
                if (!updatedTiles.contains(t) && !youGameState.getYou().isOn(x, y)) {
                    //See what kind of adjacency it is
                    boolean top = youIsTop(t);
                    boolean bottom = youIsBottom(t);
                    boolean left = youIsLeft(t);
                    boolean right = youIsRight(t);
                    boolean topLeft = youIsTopLeft(t);
                    boolean topRight = youIsTopRight(t);
                    boolean bottomLeft = youIsBottomLeft(t);
                    boolean bottomRight = youIsBottomRight(t);
                    if (top && bottom && left && right) {
                        track = youTopAndLeftAndRightAndBottom.clone();
                    } else if (top && bottom && left) {
                        track = youLeftAndBottomAndTop.clone();
                    } else if (top && bottom && right) {
                        track = youTopAndBottomAndRight.clone();
                    } else if (top && left && right) {
                        track = youTopAndLeftAndRight.clone();
                    } else if (left && right && bottom) {
                        track = youLeftAndBottomAndRight.clone();
                    } else if (bottom && left && topRight) {
                        track = youLeftAndBottomAndTopRight.clone();
                    } else if (top && left && bottomRight) {
                        track = youLeftAndTopAndBottomRight.clone();
                    } else if (bottom && right && topLeft) {
                        track = youRightAndBottomAndTopLeft.clone();
                    } else if (top && right && bottomLeft) {
                        track = youRightAndTopAndBottomLeft.clone();
                    } else if (left && bottom) {
                        track = youLeftAndBottom.clone();
                    } else if (left && right) {
                        track = youLeftAndRight.clone();
                    } else if (left && top) {
                        track = youLeftAndTop.clone();
                    } else if (right && bottom) {
                        track = youRightAndBottom.clone();
                    } else if (top && right) {
                        track = youRightAndTop.clone();
                    } else if (top && bottom) {
                        track = youTopAndBottom.clone();
                    } else if (bottom && topLeft && topRight) {
                        track = youTopLeftAndTopRightAndBottom.clone();
                    }  else if (left && topRight && bottomRight) {
                        track = youTopRightAndLeftAndBottomRight.clone();
                    } else if (top && bottomLeft && bottomRight) {
                        track = youTopAndBottomLeftAndBottomRight.clone();
                    } else if (topLeft && right && bottomLeft) {
                        track = youTopLeftAndRightAndBottomLeft.clone();
                    } else if (topLeft && bottom) {
                        track = youTopLeftAndBottom.clone();
                    } else if (topRight && bottom) {
                        track = youTopRightAndBottom.clone();
                    } else if (topRight && left) {
                        track = youTopRightAndLeft.clone();
                    } else if (left && bottomRight) {
                        track = youLeftAndBottomRight.clone();
                    } else if (top && bottomRight) {
                        track = youTopAndBottomRight.clone();
                    } else if (top && bottomLeft) {
                        track = youTopAndBottomLeft.clone();
                    } else if (right && bottomLeft) {
                        track = youRightAndBottomLeft.clone();
                    } else if (topLeft && right) {
                        track = youTopLeftAndRight.clone();
                    } else if (top) {
                        track = youTop.clone();
                    } else if (bottom) {
                        track = youBottom.clone();
                    } else if (left) {
                        track = youLeft.clone();
                    } else if (right) {
                        track = youRight.clone();
                    } else if (topLeft && topRight && bottomLeft && bottomRight) {
                        track = youTopLeftAndTopRightAndBottomLeftAndBottomRight.clone();
                    } else if (topRight && bottomRight && bottomLeft) {
                        track = youTopRightAndBottomLeftAndBottomRight.clone();
                    } else if (topLeft && topRight && bottomRight) {
                        track = youTopLeftAndTopRightAndBottomRight.clone();
                    } else if (topLeft && topRight && bottomLeft) {
                        track = youTopLeftAndTopRightAndBottomLeft.clone();
                    } else if (topRight && bottomLeft) {
                        track = youTopRightAndBottomLeft.clone();
                    } else if (topLeft && topRight) {
                        track = youTopLeftAndTopRight.clone();
                    } else if (topLeft && bottomRight) {
                        track = youTopLeftAndBottomRight.clone();
                    } else if (topLeft && bottomLeft) {
                        track = youTopLeftAndBottomLeft.clone();
                    } else if (topRight && bottomRight) {
                        track = youTopRightAndBottomRight.clone();
                    } else if (bottomRight && bottomLeft) {
                        track = youBottomLeftAndBottomRight.clone();
                    } else if (topLeft) {
                        track = youTopLeft.clone();
                    } else if (topRight) {
                        track = youTopRight.clone();
                    } else if (bottomLeft) {
                        track = youBottomLeft.clone();
                    } else if (bottomRight) {
                        track = youBottomRight.clone();
                    }
                    if (track != null) {
                        Animation anim = new Animation();
                        anim.addTrack(track);
                        anim.setTrack(0);
                        anim.update(0);
                        YouEdge edge = new YouEdge(anim, t, -1, -1);
                        updatedTiles.add(t);
                        edges.add(edge);
                        track = null;
                    }
                }
            }
        }
    }
    
    private boolean youIsTop(Tile tile) {
        return youGameState.getYou().isTop(tile);
    }
    
    private boolean youIsBottom(Tile tile) {
        return youGameState.getYou().isBottom(tile);
    }
    
    private boolean youIsLeft(Tile tile) {
        return youGameState.getYou().isLeft(tile);
    }
    
    private boolean youIsRight(Tile tile) {
        return youGameState.getYou().isRight(tile);
    }
    
    private boolean youIsTopLeft(Tile tile) {
        return youGameState.getYou().isTopLeft(tile);
    }
    
    private boolean youIsTopRight(Tile tile) {
        return youGameState.getYou().isTopRight(tile);
    }
    
    private boolean youIsBottomRight(Tile tile) {
        return youGameState.getYou().isBottomRight(tile);
    }
    
    private boolean youIsBottomLeft(Tile tile) {
        return youGameState.getYou().isBottomLeft(tile);
    }
    
    private Track makeTrack(String name) {
        return new Track(
            new BufferedImage[] { youGameState.getImage(name + "Frame1"), youGameState.getImage(name + "Frame2"), youGameState.getImage(name + "Frame3")  }, EDGE_MILLIS
        );
    }
    
    class YouEdge {
        private Animation animation;
        private Tile onTile;
        private int contextX, contextY;
        
        public YouEdge(Animation animation, Tile onTile, int contextX, int contextY) {
            this.animation = animation;
            this.onTile=onTile;
            this.contextX = contextX;
            this.contextY = contextY;
        }
        
        public void update(int dt) {
            animation.update(dt);
        }
        
        public void draw(Graphics g) {
            int x = youGameState.getRenderPositionXForTile(onTile.getX());
            if (x >= 300) {
                try {
                    g.drawImage(animation.getCurrentImage(), x, youGameState.getRenderPositionYForTile(onTile.getY()), null);
                } catch(NullPointerException npe) {} 
                }
        }
    }
}
