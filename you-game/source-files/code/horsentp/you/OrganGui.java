/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

import bropals.lib.simplegame.animation.Animation;
import bropals.lib.simplegame.gui.GuiButton;
import bropals.lib.simplegame.gui.GuiButtonAction;
import bropals.lib.simplegame.gui.GuiElement;
import bropals.lib.simplegame.gui.GuiText;
import bropals.lib.simplegame.io.AssetManager;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author Jonathon
 */
public class OrganGui extends GuiElement {

    private String text;
    private GuiText guiText;
    private GuiButton upgradeToken;
    private boolean show = false;
    private GuiButtonAction click;
    private OrganDrawer organDrawer;
    private Organ organ;
    
    public OrganGui(AssetManager assets, Organ organ, GuiText guiText, String text, GuiButtonAction upgrade, GuiButtonAction click, Animation organAnimation, int x, int y, int w, int h) {
        super(x, y, w, h);
        this.text=text;
        this.guiText = guiText;
        guiText.setPadding(10);
        guiText.setBackgroundImage(assets.getImage("organToolTipBackground"));
        upgradeToken = new GuiButton(x+40, y+40, 40, 40, assets.getImage("upgradeDown"), assets.getImage("upgradeUp"), assets.getImage("upgradeHover"), upgrade);
        upgradeToken.setDownTime(20);
        this.click = click;
        this.organ=organ;
        guiText.setX(0);
        guiText.setWidth(300);
        guiText.setHeight(150);
        organDrawer = new OrganDrawer(organAnimation, x, y);
    }

    @Override
    public void render(Object graphicsObject) {
        organDrawer.render((Graphics)graphicsObject);
        if (show) {
            upgradeToken.render(graphicsObject);
        }
    }

    public void updateOrgan(int dt) {
        organDrawer.update(dt);
    }
    
    @Override
    public void update(int mouseX, int mouseY) {
        boolean c = contains(mouseX, mouseY);
        if (c && !show) {
            if (!guiText.getText().equals(text)) {
                guiText.setText(text + " This organ is level " + organ.getLevel());
            }
            if (mouseY<300) {
                guiText.setY(450);
            } else {
                guiText.setY(50);
            }
            show = true;
            organ.updateUpgradePercents();
        } else if (!c) {
            show = false;
        }
        upgradeToken.update(mouseX, mouseY);
    }

    @Override
    public void mouseInput(int x, int y) {
        upgradeToken.mouseInput(x, y);
        if (show) {
            if (contains(x, y)) {
                click.onButtonPress();
                organDrawer.push();
            }
        }
    }    
}
