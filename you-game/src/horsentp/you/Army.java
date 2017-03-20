/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

import bropals.lib.simplegame.animation.Animation;
import java.awt.Graphics;

/**
 *
 * @author Jonathon
 */
public class Army {

    public static final int ARMY_MILLIS = 200;
    
    private Tile onTile;
    private You you;
    private City city;
    private YouGameState youGameState;
    private int currentWalk;
    private static int toWalk = 2000;
    private Tile walkingTo;
    private YouArea attacking;
    private int currentAttack;
    private static int toAttack = 400;
    private static int durabilityDamage = 10;
    private Animation animation;

    public Army(Tile onTile, Animation animation, You you, City city, YouGameState youGameState) {
        this.onTile = onTile;
        this.you = you;
        this.city = city;
        this.youGameState = youGameState;
        this.animation = animation;
        this.animation.setTrack(0);
        this.animation.update(0);
    }

    public Tile getOnTile() {
        return onTile;
    }

    
    
    public void render(Graphics g) {
        int x = youGameState.getRenderPositionXForTile(onTile.getX());
        if (x >= 300) {
            g.drawImage(animation.getCurrentImage(), x, youGameState.getRenderPositionYForTile(onTile.getY()), null);
        }
    }
    
    public void update(int dt) {
        //First see if it is inside you (kill it then)
        animation.update(dt);
        if (walkingTo != null) {
            currentWalk += dt;
            if (currentWalk >= toWalk) {
                if (you.getMatchingYouArea(walkingTo) == null) {
                    onTile = walkingTo;
                }
                walkingTo = null;
                currentWalk = 0;
                if (you.isBottom(onTile)) {
                    attacking = you.getMatchingYouArea(city.getTile(onTile.getX(), onTile.getY() + 1));
                    animation.setTrack(5);
                } else if (you.isLeft(onTile)) {
                    attacking = you.getMatchingYouArea(city.getTile(onTile.getX() - 1, onTile.getY()));
                    animation.setTrack(6);
                } else if (you.isRight(onTile)) {
                    attacking = you.getMatchingYouArea(city.getTile(onTile.getX() + 1, onTile.getY()));
                    animation.setTrack(7);
                } else if (you.isTop(onTile)) {
                    attacking = you.getMatchingYouArea(city.getTile(onTile.getX(), onTile.getY() - 1));
                    animation.setTrack(4);
                }
            }
        } else if (attacking != null) {
            currentAttack += dt;
            if (currentAttack >= toAttack) {
                attacking.getHurt(durabilityDamage);
                currentAttack = 0;
                if (attacking.isDead()) {
                    attacking = null;
                }
            }
        } else {
            //Walk around
            int dir = (int) (Math.random() * 2);
            if (dir == 0) {
                dir = (int) (Math.random() * 2);
                if (dir == 0) {
                    if (city.tileExists(onTile.getX() + 1, onTile.getY()) && city.getTile(onTile.getX()+1, onTile.getY()).getCoverage() > 0) {
                        walkingTo = city.getTile(onTile.getX()+1, onTile.getY());
                        animation.setTrack(3);
                    }
                } else {
                    if (city.tileExists(onTile.getX() - 1, onTile.getY()) && city.getTile(onTile.getX()-1, onTile.getY()).getCoverage() > 0) {
                        walkingTo = city.getTile(onTile.getX() - 1, onTile.getY());
                        animation.setTrack(2);
                    }
                }
            } else {
                dir = (int) (Math.random() * 2);
                if (dir == 0) {
                    if (city.tileExists(onTile.getX(), onTile.getY() + 1) && city.getTile(onTile.getX(), onTile.getY()+1).getCoverage() > 0) {
                        walkingTo = city.getTile(onTile.getX(), onTile.getY() + 1);
                        animation.setTrack(1);
                    }
                } else {
                    if (city.tileExists(onTile.getX(), onTile.getY() - 1) && city.getTile(onTile.getX(), onTile.getY()-1).getCoverage() > 0) {
                        walkingTo = city.getTile(onTile.getX(), onTile.getY() - 1);
                        animation.setTrack(0);
                    }
                }
            }
        }
    }
}
