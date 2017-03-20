/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

import bropals.lib.simplegame.KeyCode;
import bropals.lib.simplegame.animation.Animation;
import bropals.lib.simplegame.animation.Track;
import bropals.lib.simplegame.gui.Gui;
import bropals.lib.simplegame.gui.GuiButtonAction;
import bropals.lib.simplegame.gui.GuiGroup;
import bropals.lib.simplegame.gui.GuiText;
import bropals.lib.simplegame.state.GameState;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonathon
 */
public class YouGameState extends GameState {

    public static final int ORGAN_ANIMATION_MILLIS = 150;

    private You you;
    private City city;
    private int topLeftTileX;
    private int topLeftTileY;
    private Gui gui;
    private int mouseX;
    private int mouseY;
    private Tile mouseTile;
    private boolean dragging = false;
    private Tile dragTile;
    private OrganGui stomach;
    private OrganGui brain;
    private BufferedImage organBackground;
    private BufferedImage separator;
    private OrganGui heart;
    private OrganGui lung;
    private OrganGui pancreas;
    private GuiText organTooltip;
    private Color youColor = new Color(210, 144, 212);
    private YouEdgeManager youEdgeManager;
    private ArrayList<Army> armies = new ArrayList<>();
    private float calorieUpgradePercent;
    private float vitaminHUpgradePercent;
    private float vitaminXUpgradePercent;
    private int armyCounter;
    private int forArmy;
    private Animation armyAnimation;
    private String cityPath;
    private int toWin;
    
    public YouGameState(int city) {
        this.cityPath = Cities.getPath(city);
        this.toWin = Cities.getToWin(city);
        forArmy = Cities.getArmySpawnSpeed(city);
    }    
    
    public ArrayList<Army> getArmies() {
        return armies;
    }

    @Override
    public void update(int mills) {
        if (you.getYouAreas().isEmpty()) {
            //You are dead!
            getWindow().destroy();
            JOptionPane.showMessageDialog(null, "You have died!");
        }
        Point mp = getWindow().getMousePosition();
        if (mp != null) {
            mouseX = mp.x;
            mouseY = mp.y;
            if (mouseX > 300 && mouseX < 800 && mouseY > 0 && mouseY < 600) {
                int mx = (mp.x - 300) / 100;
                int my = mp.y / 100;
                Tile t = city.getTile(mx + topLeftTileX, my + topLeftTileY);
                if (dragging) {
                    dragTile = t;
                } else {
                    mouseTile = t;
                }
            } else {
                mouseTile = null;
            }
        }
        armyCounter += mills;
        if (armyCounter >= forArmy) {
            armyCounter = 0;
            //Get a random border tile.
            int ran = (int) (Math.random() * 4);
            Tile ranTile = null;
            switch (ran) {
                case 0:
                    ranTile = city.getTile((int) (Math.random() * city.getWidth()), 0);
                    break;
                case 1:
                    ranTile = city.getTile((int) (Math.random() * city.getWidth()), city.getHeight() - 1);
                    break;
                case 2:
                    ranTile = city.getTile(0, (int) (Math.random() * city.getHeight()));
                    break;
                case 3:
                    ranTile = city.getTile(city.getWidth() - 1, (int) (Math.random() * city.getHeight()));
                    break;
            }
            if (!you.isOn(ranTile.getX(), ranTile.getY())) {
                armies.add(new Army(ranTile, armyAnimation.clone(), you, city, this));
            }
        }
        for (int a=0; a<armies.size(); a++) { 
            if (you.getMatchingYouArea(armies.get(a).getOnTile()) == null) {
                armies.get(a).update(mills);
            } else {
                armies.remove(a);
                a--;
            }
        }
        stomach.updateOrgan(mills);
        brain.updateOrgan(mills);
        heart.updateOrgan(mills);
        lung.updateOrgan(mills);
        pancreas.updateOrgan(mills);
        youEdgeManager.update(mills);
        organTooltip.setEnabled(stomach.contains(mouseX, mouseY) || brain.contains(mouseX, mouseY) || heart.contains(mouseX, mouseY) || lung.contains(mouseX, mouseY) || pancreas.contains(mouseX, mouseY));
        you.update((float) mills / 1000.0f, youEdgeManager);
        gui.update(mouseX, mouseY);
        SoundBox.getSoundBox().update(mills);
    }

    @Override
    public void render(Object graphicsObj) {
        Graphics g = (Graphics) graphicsObj;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, YouGame.SCREEN_WIDTH, YouGame.SCREEN_HEIGHT);
        g.drawImage(organBackground, 0, 50, null);
        //Draw stomach progress bar
        gui.render(graphicsObj);
        float stomachPercent = (float) (you.getStomach().getCalories() + you.getStomach().getVitaminH() + you.getStomach().getVitaminX()) / (float) you.getStomach().getCapacity();
        drawGenericProgressBar(g, "" + (int) (100 * stomachPercent) + "%", 80, 430, 90, 30, stomachPercent);
        if (organTooltip.isEnabled()) {
            organTooltip.render(graphicsObj);
            FontMetrics fm = g.getFontMetrics();
            g.setColor(Color.BLACK);
            if (organTooltip.getY() == 50) {
                g.drawString("Calories to Upgrade", 10, 110 + fm.getHeight());
                g.drawString("Vitamin H to Upgrade", 10, 140 + fm.getHeight());
                g.drawString("Vitamin X to Upgrade", 10, 170 + fm.getHeight());
                drawGenericProgressBar(g, "" + (int) (calorieUpgradePercent * 100) + "%", 135, 115, 150, fm.getHeight(), calorieUpgradePercent);
                drawGenericProgressBar(g, "" + (int) (vitaminHUpgradePercent * 100) + "%", 135, 145, 150, fm.getHeight(), vitaminHUpgradePercent);
                drawGenericProgressBar(g, "" + (int) (vitaminXUpgradePercent * 100) + "%", 135, 175, 150, fm.getHeight(), vitaminXUpgradePercent);
            } else {
                g.drawString("Calories to Upgrade", 10, 510 + fm.getHeight());
                g.drawString("Vitamin H to Upgrade", 10, 540 + fm.getHeight());
                g.drawString("Vitamin X to Upgrade", 10, 570 + fm.getHeight());
                drawGenericProgressBar(g, "" + (int) (calorieUpgradePercent * 100) + "%", 135, 515, 150, fm.getHeight(), calorieUpgradePercent);
                drawGenericProgressBar(g, "" + (int) (vitaminHUpgradePercent * 100) + "%", 135, 545, 150, fm.getHeight(), vitaminHUpgradePercent);
                drawGenericProgressBar(g, "" + (int) (vitaminXUpgradePercent * 100) + "%", 135, 575, 150, fm.getHeight(), vitaminXUpgradePercent);
            }
        }
        //Draw the count of resources
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 300, 50);
        g.setColor(Color.BLACK);
        g.drawString("" + (int) you.getCalories() + " Calories", 10, g.getFontMetrics().getHeight());
        g.drawString("" + (int) you.getVitaminH() + " Vitamin H", 10, 25 + g.getFontMetrics().getHeight());
        g.drawString("" + (int) you.getVitaminX() + " Vitamin X", 160, 25 + g.getFontMetrics().getHeight());
        g.drawImage(separator, 297, 0, null);
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 6; y++) {
                Tile tile = city.getTile(topLeftTileX + x, topLeftTileY + y);
                int drawX = 300 + (x * 100);
                if (drawX >= 300) {
                    g.drawImage(tile.getSprite(), drawX, y * 100, null);
                }
            }
        }
        //Draw armies
        for (Army a : armies) {
            a.render(g);
        }
        for (YouArea youArea : you.getYouAreas()) {
            //Draw you on the tile
            g.setColor(youColor);
            int x = getRenderPositionXForTile(youArea.getTile().getX());
            if (x >= 300) {
                g.fillRect(x, getRenderPositionYForTile(youArea.getTile().getY()), 100, 100);
            }
        }
        youEdgeManager.renderEdges(g);
        if (mouseTile != null) {
            GrowthArea ga;
            if ((ga = you.getMatchingGrowthArea(mouseTile)) != null) {
                drawProgressTooltipTile(g, ga.getTile(), "Growth", (float) ga.getCoverage() / (float) ga.getTile().getCoverage());
            }
            YouArea ya;
            if ((ya = you.getMatchingYouArea(mouseTile)) != null && mouseX>getRenderPositionXForTile(mouseTile.getX()) && mouseX<getRenderPositionXForTile(mouseTile.getX())+100 && mouseY>getRenderPositionYForTile(mouseTile.getY()) && mouseY<getRenderPositionYForTile(mouseTile.getY())+100) {
                drawProgressTooltipTile(g, ya.getTile(), "Durability", (float) ya.getDurability() / (float) YouArea.MAX_DURABILITY);
            }
            if (dragTile != null && you.canGrowOn(dragTile)) {
                g.setColor(youColor.brighter());
                ((Graphics2D) g).setStroke(new BasicStroke(5));
                g.drawLine(getRenderPositionXForTile(mouseTile.getX())+50, getRenderPositionYForTile(mouseTile.getY())+50, getRenderPositionXForTile(dragTile.getX())+50, getRenderPositionYForTile(dragTile.getY())+50);
            }
        }
    }

    private void drawGenericProgressBar(Graphics g, String text, int x, int y, int w, int h, float percent) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, w, h);
        g.setColor(Color.GREEN);
        g.fillRect(x, y, (int) (w * percent), h);
        g.setColor(Color.BLACK);
        g.drawString(text, x + (w / 2) - (g.getFontMetrics().stringWidth(text) / 2), y + g.getFontMetrics().getHeight() - 2);
    }

    public YouEdgeManager getYouEdgeManager() {
        return youEdgeManager;
    }

    private void drawProgressTooltipTile(Graphics g, Tile tile, String text, float percent) {
        //Draw tooltip
        int gaX;
        int gaY;
        if (mouseY < 100) {
            gaY = 100;
        } else {
            gaY = getRenderPositionYForTile(tile.getY()) - 60;
        }
        gaX = getRenderPositionXForTile(tile.getX());
        //Actually drawing it now
        g.setColor(Color.WHITE);
        g.fillRect(gaX, gaY, 100, 60);
        g.setColor(Color.BLUE);
        g.fillRect(gaX + 10, gaY + 30, 80, 20);
        g.setColor(Color.GREEN);
        g.fillRect(gaX + 10, gaY + 30, (int) (80 * percent), 20);
        g.setColor(Color.BLACK);
        g.drawString(text, gaX + 10, gaY + 5 + g.getFontMetrics().getHeight());
    }

    public int getRenderPositionXForTile(int tileX) {
        return 300 + ((tileX - topLeftTileX) * 100);
    }

    public int getRenderPositionYForTile(int tileY) {
        return (tileY - topLeftTileY) * 100;
    }

    @Override
    public void onEnter() {
        you = new You(this);
        gui = new Gui();
        try {
            city = new City(getAssetManager(), "assets/data/" + cityPath);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cannot load City: " + ex);
        }
        topLeftTileX = (city.getWidth()/2) - 2;
        if (topLeftTileX < 0) topLeftTileX = 0;
        topLeftTileY = (city.getHeight()/2) - 2;
        if (topLeftTileY < 0) topLeftTileY = 0;
        youEdgeManager = new YouEdgeManager(city, this);
        you.takeControlOf(city.getTile(city.getWidth() / 2, city.getHeight() / 2));
        youEdgeManager.refreshEdges();
        armyAnimation = new Animation();
        armyAnimation.addTrack(new Track(new BufferedImage[]{getImage("armyWalkUp")}, Army.ARMY_MILLIS));
        armyAnimation.addTrack(new Track(new BufferedImage[]{getImage("armyWalkDown")}, Army.ARMY_MILLIS));
        armyAnimation.addTrack(new Track(new BufferedImage[]{getImage("armyWalkLeft")}, Army.ARMY_MILLIS));
        armyAnimation.addTrack(new Track(new BufferedImage[]{getImage("armyWalkRight")}, Army.ARMY_MILLIS));
        armyAnimation.addTrack(new Track(new BufferedImage[]{getImage("armyAttackUpFrame1"), getImage("armyAttackUpFrame2"), getImage("armyAttackUpFrame3"), getImage("armyAttackUpFrame4")}, Army.ARMY_MILLIS));
        armyAnimation.addTrack(new Track(new BufferedImage[]{getImage("armyAttackDownFrame1"), getImage("armyAttackDownFrame2"), getImage("armyAttackDownFrame3"), getImage("armyAttackDownFrame4")}, Army.ARMY_MILLIS));
        armyAnimation.addTrack(new Track(new BufferedImage[]{getImage("armyAttackLeftFrame1"), getImage("armyAttackLeftFrame2"), getImage("armyAttackLeftFrame3"), getImage("armyAttackLeftFrame4")}, Army.ARMY_MILLIS));
        armyAnimation.addTrack(new Track(new BufferedImage[]{getImage("armyAttackRightFrame1"), getImage("armyAttackRightFrame2"), getImage("armyAttackRightFrame3"), getImage("armyAttackRightFrame4")}, Army.ARMY_MILLIS));
        //Init animations and images
        organTooltip = new GuiText("", 0, 50, 300, 100);
        organBackground = getImage("organBackground");
        separator = getImage("separator");
        Animation stomachAnimation = new Animation();
        stomachAnimation.addTrack(new Track(new BufferedImage[]{
            getImage("stomach")
        }, ORGAN_ANIMATION_MILLIS));
        stomachAnimation.setTrack(0);
        Animation brainAnimation = new Animation();
        brainAnimation.addTrack(new Track(new BufferedImage[]{
            getImage("brain")
        }, ORGAN_ANIMATION_MILLIS));
        brainAnimation.setTrack(0);
        Animation heartAnimation = new Animation();
        heartAnimation.addTrack(new Track(new BufferedImage[]{
            getImage("heartFrame1"), getImage("heartFrame2"), getImage("heartFrame3"), getImage("heartFrame4")
        }, ORGAN_ANIMATION_MILLIS));
        heartAnimation.setTrack(0);
        Animation lungAnimation = new Animation();
        lungAnimation.addTrack(new Track(new BufferedImage[]{
            getImage("lungFrame1"), getImage("lungFrame2"), getImage("lungFrame3"), getImage("lungFrame4")
        }, ORGAN_ANIMATION_MILLIS));
        lungAnimation.setTrack(0);
        Animation pancreasAnimation = new Animation();
        pancreasAnimation.addTrack(new Track(new BufferedImage[]{
            getImage("pancreasFrame1"), getImage("pancreasFrame2"), getImage("pancreasFrame3"), getImage("pancreasFrame4")
        }, ORGAN_ANIMATION_MILLIS));
        pancreasAnimation.setTrack(0);
        stomach = new OrganGui(
                getAssetManager(),
                you.getStomach(),
                organTooltip,
                "Your stomach. Holds the food you eat. You cannot eat if you have a full stomach.",
                new GuiButtonAction() {
                    @Override
                    public void onButtonPress() {
                        you.getStomach().upgradeClick();
                    }
                },
                new GuiButtonAction() {
                    @Override
                    public void onButtonPress() {
                        you.getStomach().clickedOn();
                    }
                }, stomachAnimation,
                26,
                288 + 50,
                179,
                156);
        brain = new OrganGui(
                getAssetManager(),
                you.getBrain(),
                organTooltip,
                "Your brain. Controls how smart your other organs are.",
                new GuiButtonAction() {
                    @Override
                    public void onButtonPress() {
                        you.getBrain().upgradeClick();
                    }
                },
                new GuiButtonAction() {
                    @Override
                    public void onButtonPress() {
                        you.getBrain().clickedOn();
                    }
                }, brainAnimation,
                179,
                33 + 50,
                118,
                119);
        heart = new OrganGui(
                getAssetManager(),
                you.getHeart(),
                organTooltip,
                "Your heart. Controls how quickly and efficiently you can repair yourself.",
                new GuiButtonAction() {
                    @Override
                    public void onButtonPress() {
                        you.getHeart().upgradeClick();
                    }
                },
                new GuiButtonAction() {
                    @Override
                    public void onButtonPress() {
                        you.getHeart().clickedOn();
                    }
                }, heartAnimation,
                198,
                188 + 50,
                94,
                128);
        lung = new OrganGui(
                getAssetManager(),
                you.getLungs(),
                organTooltip,
                "Your lung. Controls how quickly and efficiently you can grow yourself.",
                new GuiButtonAction() {
                    @Override
                    public void onButtonPress() {
                        you.getLungs().upgradeClick();
                    }
                },
                new GuiButtonAction() {
                    @Override
                    public void onButtonPress() {
                        you.getLungs().clickedOn();
                    }
                }, lungAnimation,
                29,
                72 + 50,
                142,
                163);
        pancreas = new OrganGui(
                getAssetManager(),
                you.getPancreas(),
                organTooltip,
                "You pancreas. Controls how quickly and efficiently you can digest food.",
                new GuiButtonAction() {
                    @Override
                    public void onButtonPress() {
                        you.getPancreas().upgradeClick();
                    }
                },
                new GuiButtonAction() {
                    @Override
                    public void onButtonPress() {
                        you.getPancreas().clickedOn();
                    }
                }, pancreasAnimation,
                22,
                457 + 50,
                206,
                66);
        GuiGroup everything = new GuiGroup();
        everything.addElement(stomach);
        everything.addElement(brain);
        everything.addElement(heart);
        everything.addElement(lung);
        everything.addElement(pancreas);
        everything.addElement(organTooltip);
        gui.addGroup("everything", everything);
    }

    @Override
    public void onExit() {
    }

    @Override
    public void mouse(int mousebutton, int x, int y, boolean pressed) {
        if (pressed) {
            gui.mouseInput(x, y);
            dragging = true;
            if (mouseTile != null) {
                //Click to rush
                RepairArea ra;
                YouArea ya;
                GrowthArea ga = you.getMatchingGrowthArea(mouseTile);
                if (ga != null) {
                    ga.grow(you.getLungs().getCoveragePerClick());
                } else if ((ra = you.getMatchingRepairArea(mouseTile)) != null) {
                    ra.repair(you.getHeart().getDurabilityPerClick());
                } else if ((ya = you.getMatchingYouArea(mouseTile)) != null && ya.getDurability() < YouArea.MAX_DURABILITY) {
                    you.startRepairing(ya);
                }
            }
        } else {
            dragging = false;
            if (mouseTile != null && dragTile != null && !mouseTile.is(dragTile) && you.canGrowOn(dragTile)) {
                you.startGrowingOn(dragTile, mouseTile, youEdgeManager);
            }
            dragTile = null;
        }
    }

    @Override
    public void key(int keycode, boolean pressed) {
        if (pressed) {
            if (keycode == KeyCode.KEY_W || keycode == KeyCode.KEY_UP) {
                if (topLeftTileY > 0) {
                    topLeftTileY--;
                }
            } else if (keycode == KeyCode.KEY_D || keycode == KeyCode.KEY_RIGHT) {
                if (topLeftTileX < city.getWidth() - 5) {
                    topLeftTileX++;
                }
            } else if (keycode == KeyCode.KEY_S || keycode == KeyCode.KEY_DOWN) {
                if (topLeftTileY < city.getHeight() - 6) {
                    topLeftTileY++;
                }
            } else if (keycode == KeyCode.KEY_A || keycode == KeyCode.KEY_LEFT) {
                if (topLeftTileX > 0) {
                    topLeftTileX--;
                }
            }
        }
    }

    public String getCityPath() {
        return cityPath;
    }

    public int getToWin() {
        return toWin;
    }
    
    

    public You getYou() {
        return you;
    }

    public void setCalorieUpgradePercent(float calorieUpgradePercent) {
        this.calorieUpgradePercent = calorieUpgradePercent;
    }

    public void setVitaminHUpgradePercent(float vitaminHUpgradePercent) {
        this.vitaminHUpgradePercent = vitaminHUpgradePercent;
    }

    public void setVitaminXUpgradePercent(float vitaminXUpgradePercent) {
        this.vitaminXUpgradePercent = vitaminXUpgradePercent;
    }

    public City getCity() {
        return city;
    }

    public void win() {
        //Win the game because you are big enough
        getGameStateRunner().setState(new WinState());
    }
}
