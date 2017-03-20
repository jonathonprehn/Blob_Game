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
public class MainMenu extends GameState {

    private BufferedImage currentImage;
    private int panel = 1;
    
    @Override
    public void update(int mills) {
        SoundBox.getSoundBox().update(mills);
    }

    @Override
    public void render(Object graphicsObj) {
        Graphics g = (Graphics)graphicsObj;
        g.drawImage(currentImage, 0, 0, null);
    }

    @Override
    public void onEnter() {
        currentImage = getImage("panel" + panel);
    }

    @Override
    public void onExit() {
    }

    @Override
    public void mouse(int mousebutton, int x, int y, boolean pressed) {
        if (pressed) {
            next();
        }
    }    

    @Override
    public void key(int keycode, boolean pressed) {
        if (pressed && keycode == KeyCode.KEY_SPACE) {
            next();
        }
    }
    
    private void next() {
        panel++;
        if (panel<=10) {
            currentImage = getImage("panel" + panel);
        } else if (panel == 11) {
            currentImage = getImage("mainMenu");
        } else if (panel == 12) {
            currentImage = getImage("howTo1");
        } else if (panel == 13) {
            currentImage = getImage("howTo2");
        } else {
            getGameStateRunner().setState(new YouGameState(1));
        }
    }
    
}
