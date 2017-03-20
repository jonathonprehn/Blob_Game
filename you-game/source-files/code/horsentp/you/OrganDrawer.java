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
public class OrganDrawer {
    
    private Animation organAnimation;
    private float x;
    private float y;
    private float originalX;
    private float originalY;
    private float velX = 0;
    private float velY = 0;
    
    public OrganDrawer(Animation organAnimation, float ox, float oy) {
        this.organAnimation = organAnimation;
        originalX = ox;
        originalY = oy;
        x = ox;
        y = oy;
    }
    
    public void render(Graphics g) {
        g.drawImage(organAnimation.getCurrentImage(), (int)x, (int)y, null);
    }
    
    public void update(int dt) {
        organAnimation.update(dt);
        x += velX;
        y += velY;
        x += ((originalX-x)*0.5f);
        y += ((originalY-y)*0.5f);
        velX = velX*0.95f;
        velY = velY*0.95f;
    }
    
    public void push() {
        velX += (float)(Math.random()*18 - 9f);
        velY += (float)(Math.random()*24 - 12f);
    }
}
