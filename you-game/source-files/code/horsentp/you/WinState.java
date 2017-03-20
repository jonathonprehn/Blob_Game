/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

import bropals.lib.simplegame.state.GameState;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public class WinState extends GameState {

    private BufferedImage win;
    
    @Override
    public void update(int mills) {
        SoundBox.getSoundBox().update(mills);
    }

    @Override
    public void render(Object graphicsObj) {
        Graphics g = (Graphics)graphicsObj;
        g.drawImage(win, 0, 0, null);
    }

    @Override
    public void onEnter() {
        win = getImage("win");
        SoundBox.getSoundBox().playWinStateSound();
    }

    @Override
    public void onExit() {
    }
    
}
