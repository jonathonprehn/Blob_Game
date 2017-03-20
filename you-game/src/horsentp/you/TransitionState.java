/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

import bropals.lib.simplegame.KeyCode;
import bropals.lib.simplegame.state.GameState;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jonathon
 */
public class TransitionState extends GameState {
    
    private int toCity;
    private BufferedImage trans;
    
    public TransitionState(int toCity) {
        this.toCity = toCity;
    }

    @Override
    public void update(int mills) {
        
    }

    @Override
    public void render(Object graphicsObj) {
        Graphics g = (Graphics)graphicsObj;
        g.drawImage(trans, 0, 0, null);
    }

    @Override
    public void onEnter() {
        trans = getImage("transition");
        SoundBox.getSoundBox().playWinStateSound();
    }

    @Override
    public void onExit() {
    }

    @Override
    public void key(int keycode, boolean pressed) {
        if (pressed && keycode == KeyCode.KEY_SPACE) {
            getGameStateRunner().setState(new YouGameState(toCity));
        }
    }
    
}
