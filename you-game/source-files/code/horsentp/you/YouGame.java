/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

import bropals.lib.simplegame.AWTGameWindow;
import bropals.lib.simplegame.GameStateRunner;
import bropals.lib.simplegame.GameWindow;
import bropals.lib.simplegame.io.AssetManager;
import bropals.lib.simplegame.logger.InfoLogger;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Jonathon
 */
public class YouGame {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //generateLoaderCode();
        InfoLogger.setSilent(true);
        //Open the splash screen
        SplashScreen splash = SplashScreen.getSplashScreen();
        AssetManager assetManager = new AssetManager(YouGame.class, true);
        if (splash != null) {
            assetManager.loadImage("assets/images/splash.png", "splash");
            Graphics2D g2 = splash.createGraphics();
            g2.drawImage(assetManager.getImage("splash"), 0, 0, null);
            splash.update();
        }
        loadAssets(assetManager);
        SoundBox.createSoundBox(assetManager);
        SoundBox.getSoundBox().init();
        if (splash != null) {
            splash.close();
        }
        GameWindow window = new AWTGameWindow("Blob", SCREEN_WIDTH, SCREEN_HEIGHT);
        window.setIcon(assetManager.getImage("icon"));
        GameStateRunner runner = new GameStateRunner(window, assetManager);
        runner.setFps(30);
        runner.setState(new MainMenu());
        runner.loop();
    }

    public static void loadAssets(AssetManager assetManager) {
        assetManager.loadImage("assets/images/you/youBottomFrame1.png", "youBottomFrame1");
        assetManager.loadImage("assets/images/you/youBottomFrame2.png", "youBottomFrame2");
        assetManager.loadImage("assets/images/you/youBottomFrame3.png", "youBottomFrame3");
        assetManager.loadImage("assets/images/you/youBottomLeftAndBottomRightFrame1.png", "youBottomLeftAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youBottomLeftAndBottomRightFrame2.png", "youBottomLeftAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youBottomLeftAndBottomRightFrame3.png", "youBottomLeftAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youBottomLeftFrame1.png", "youBottomLeftFrame1");
        assetManager.loadImage("assets/images/you/youBottomLeftFrame2.png", "youBottomLeftFrame2");
        assetManager.loadImage("assets/images/you/youBottomLeftFrame3.png", "youBottomLeftFrame3");
        assetManager.loadImage("assets/images/you/youBottomRightFrame1.png", "youBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youBottomRightFrame2.png", "youBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youBottomRightFrame3.png", "youBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheBottomAlmostFrame1.png", "youGrowingToTheBottomAlmostFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheBottomAlmostFrame2.png", "youGrowingToTheBottomAlmostFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheBottomAlmostFrame3.png", "youGrowingToTheBottomAlmostFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheBottomKindaFrame1.png", "youGrowingToTheBottomKindaFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheBottomKindaFrame2.png", "youGrowingToTheBottomKindaFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheBottomKindaFrame3.png", "youGrowingToTheBottomKindaFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheBottomLotsFrame1.png", "youGrowingToTheBottomLotsFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheBottomLotsFrame2.png", "youGrowingToTheBottomLotsFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheBottomLotsFrame3.png", "youGrowingToTheBottomLotsFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheLeftAlmostFrame1.png", "youGrowingToTheLeftAlmostFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheLeftAlmostFrame2.png", "youGrowingToTheLeftAlmostFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheLeftAlmostFrame3.png", "youGrowingToTheLeftAlmostFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheLeftKindaFrame1.png", "youGrowingToTheLeftKindaFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheLeftKindaFrame2.png", "youGrowingToTheLeftKindaFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheLeftKindaFrame3.png", "youGrowingToTheLeftKindaFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheLeftLotsFrame1.png", "youGrowingToTheLeftLotsFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheLeftLotsFrame2.png", "youGrowingToTheLeftLotsFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheLeftLotsFrame3.png", "youGrowingToTheLeftLotsFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheRightAlmostFrame1.png", "youGrowingToTheRightAlmostFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheRightAlmostFrame2.png", "youGrowingToTheRightAlmostFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheRightAlmostFrame3.png", "youGrowingToTheRightAlmostFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheRightKindaFrame1.png", "youGrowingToTheRightKindaFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheRightKindaFrame2.png", "youGrowingToTheRightKindaFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheRightKindaFrame3.png", "youGrowingToTheRightKindaFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheRightLotsFrame1.png", "youGrowingToTheRightLotsFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheRightLotsFrame2.png", "youGrowingToTheRightLotsFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheRightLotsFrame3.png", "youGrowingToTheRightLotsFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheTopAlmostFrame1.png", "youGrowingToTheTopAlmostFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheTopAlmostFrame2.png", "youGrowingToTheTopAlmostFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheTopAlmostFrame3.png", "youGrowingToTheTopAlmostFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheTopKindaFrame1.png", "youGrowingToTheTopKindaFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheTopKindaFrame2.png", "youGrowingToTheTopKindaFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheTopKindaFrame3.png", "youGrowingToTheTopKindaFrame3");
        assetManager.loadImage("assets/images/you/youGrowingToTheTopLotsFrame1.png", "youGrowingToTheTopLotsFrame1");
        assetManager.loadImage("assets/images/you/youGrowingToTheTopLotsFrame2.png", "youGrowingToTheTopLotsFrame2");
        assetManager.loadImage("assets/images/you/youGrowingToTheTopLotsFrame3.png", "youGrowingToTheTopLotsFrame3");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndBottomRightFrame1.png", "youLeftAndBottomAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndBottomRightFrame2.png", "youLeftAndBottomAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndBottomRightFrame3.png", "youLeftAndBottomAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndRightFrame1.png", "youLeftAndBottomAndRightFrame1");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndRightFrame2.png", "youLeftAndBottomAndRightFrame2");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndRightFrame3.png", "youLeftAndBottomAndRightFrame3");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndTopFrame1.png", "youLeftAndBottomAndTopFrame1");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndTopFrame2.png", "youLeftAndBottomAndTopFrame2");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndTopFrame3.png", "youLeftAndBottomAndTopFrame3");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndTopRightFrame1.png", "youLeftAndBottomAndTopRightFrame1");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndTopRightFrame2.png", "youLeftAndBottomAndTopRightFrame2");
        assetManager.loadImage("assets/images/you/youLeftAndBottomAndTopRightFrame3.png", "youLeftAndBottomAndTopRightFrame3");
        assetManager.loadImage("assets/images/you/youLeftAndBottomFrame1.png", "youLeftAndBottomFrame1");
        assetManager.loadImage("assets/images/you/youLeftAndBottomFrame2.png", "youLeftAndBottomFrame2");
        assetManager.loadImage("assets/images/you/youLeftAndBottomFrame3.png", "youLeftAndBottomFrame3");
        assetManager.loadImage("assets/images/you/youLeftAndBottomRightFrame1.png", "youLeftAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youLeftAndBottomRightFrame2.png", "youLeftAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youLeftAndBottomRightFrame3.png", "youLeftAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youLeftAndRightFrame1.png", "youLeftAndRightFrame1");
        assetManager.loadImage("assets/images/you/youLeftAndRightFrame2.png", "youLeftAndRightFrame2");
        assetManager.loadImage("assets/images/you/youLeftAndRightFrame3.png", "youLeftAndRightFrame3");
        assetManager.loadImage("assets/images/you/youLeftAndTopAndBottomRightFrame1.png", "youLeftAndTopAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youLeftAndTopAndBottomRightFrame2.png", "youLeftAndTopAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youLeftAndTopAndBottomRightFrame3.png", "youLeftAndTopAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youLeftAndTopFrame1.png", "youLeftAndTopFrame1");
        assetManager.loadImage("assets/images/you/youLeftAndTopFrame2.png", "youLeftAndTopFrame2");
        assetManager.loadImage("assets/images/you/youLeftAndTopFrame3.png", "youLeftAndTopFrame3");
        assetManager.loadImage("assets/images/you/youLeftFrame1.png", "youLeftFrame1");
        assetManager.loadImage("assets/images/you/youLeftFrame2.png", "youLeftFrame2");
        assetManager.loadImage("assets/images/you/youLeftFrame3.png", "youLeftFrame3");
        assetManager.loadImage("assets/images/you/youRightAndBottomAndTopLeftFrame1.png", "youRightAndBottomAndTopLeftFrame1");
        assetManager.loadImage("assets/images/you/youRightAndBottomAndTopLeftFrame2.png", "youRightAndBottomAndTopLeftFrame2");
        assetManager.loadImage("assets/images/you/youRightAndBottomAndTopLeftFrame3.png", "youRightAndBottomAndTopLeftFrame3");
        assetManager.loadImage("assets/images/you/youRightAndBottomFrame1.png", "youRightAndBottomFrame1");
        assetManager.loadImage("assets/images/you/youRightAndBottomFrame2.png", "youRightAndBottomFrame2");
        assetManager.loadImage("assets/images/you/youRightAndBottomFrame3.png", "youRightAndBottomFrame3");
        assetManager.loadImage("assets/images/you/youRightAndBottomLeftFrame1.png", "youRightAndBottomLeftFrame1");
        assetManager.loadImage("assets/images/you/youRightAndBottomLeftFrame2.png", "youRightAndBottomLeftFrame2");
        assetManager.loadImage("assets/images/you/youRightAndBottomLeftFrame3.png", "youRightAndBottomLeftFrame3");
        assetManager.loadImage("assets/images/you/youRightAndTopAndBottomLeftFrame1.png", "youRightAndTopAndBottomLeftFrame1");
        assetManager.loadImage("assets/images/you/youRightAndTopAndBottomLeftFrame2.png", "youRightAndTopAndBottomLeftFrame2");
        assetManager.loadImage("assets/images/you/youRightAndTopAndBottomLeftFrame3.png", "youRightAndTopAndBottomLeftFrame3");
        assetManager.loadImage("assets/images/you/youRightAndTopFrame1.png", "youRightAndTopFrame1");
        assetManager.loadImage("assets/images/you/youRightAndTopFrame2.png", "youRightAndTopFrame2");
        assetManager.loadImage("assets/images/you/youRightAndTopFrame3.png", "youRightAndTopFrame3");
        assetManager.loadImage("assets/images/you/youRightFrame1.png", "youRightFrame1");
        assetManager.loadImage("assets/images/you/youRightFrame2.png", "youRightFrame2");
        assetManager.loadImage("assets/images/you/youRightFrame3.png", "youRightFrame3");
        assetManager.loadImage("assets/images/you/youTopAndBottomAndRightFrame1.png", "youTopAndBottomAndRightFrame1");
        assetManager.loadImage("assets/images/you/youTopAndBottomAndRightFrame2.png", "youTopAndBottomAndRightFrame2");
        assetManager.loadImage("assets/images/you/youTopAndBottomAndRightFrame3.png", "youTopAndBottomAndRightFrame3");
        assetManager.loadImage("assets/images/you/youTopAndBottomFrame1.png", "youTopAndBottomFrame1");
        assetManager.loadImage("assets/images/you/youTopAndBottomFrame2.png", "youTopAndBottomFrame2");
        assetManager.loadImage("assets/images/you/youTopAndBottomFrame3.png", "youTopAndBottomFrame3");
        assetManager.loadImage("assets/images/you/youTopAndBottomLeftAndBottomRightFrame1.png", "youTopAndBottomLeftAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youTopAndBottomLeftAndBottomRightFrame2.png", "youTopAndBottomLeftAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youTopAndBottomLeftAndBottomRightFrame3.png", "youTopAndBottomLeftAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youTopAndBottomLeftFrame1.png", "youTopAndBottomLeftFrame1");
        assetManager.loadImage("assets/images/you/youTopAndBottomLeftFrame2.png", "youTopAndBottomLeftFrame2");
        assetManager.loadImage("assets/images/you/youTopAndBottomLeftFrame3.png", "youTopAndBottomLeftFrame3");
        assetManager.loadImage("assets/images/you/youTopAndBottomRightFrame1.png", "youTopAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youTopAndBottomRightFrame2.png", "youTopAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youTopAndBottomRightFrame3.png", "youTopAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youTopAndLeftAndRightAndBottomFrame1.png", "youTopAndLeftAndRightAndBottomFrame1");
        assetManager.loadImage("assets/images/you/youTopAndLeftAndRightAndBottomFrame2.png", "youTopAndLeftAndRightAndBottomFrame2");
        assetManager.loadImage("assets/images/you/youTopAndLeftAndRightAndBottomFrame3.png", "youTopAndLeftAndRightAndBottomFrame3");
        assetManager.loadImage("assets/images/you/youTopAndLeftAndRightFrame1.png", "youTopAndLeftAndRightFrame1");
        assetManager.loadImage("assets/images/you/youTopAndLeftAndRightFrame2.png", "youTopAndLeftAndRightFrame2");
        assetManager.loadImage("assets/images/you/youTopAndLeftAndRightFrame3.png", "youTopAndLeftAndRightFrame3");
        assetManager.loadImage("assets/images/you/youTopFrame1.png", "youTopFrame1");
        assetManager.loadImage("assets/images/you/youTopFrame2.png", "youTopFrame2");
        assetManager.loadImage("assets/images/you/youTopFrame3.png", "youTopFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomFrame1.png", "youTopLeftAndBottomFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomFrame2.png", "youTopLeftAndBottomFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomFrame3.png", "youTopLeftAndBottomFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomLeftAndBottomRightFrame1.png", "youTopLeftAndBottomLeftAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomLeftAndBottomRightFrame2.png", "youTopLeftAndBottomLeftAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomLeftAndBottomRightFrame3.png", "youTopLeftAndBottomLeftAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomLeftFrame1.png", "youTopLeftAndBottomLeftFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomLeftFrame2.png", "youTopLeftAndBottomLeftFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomLeftFrame3.png", "youTopLeftAndBottomLeftFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomRightFrame1.png", "youTopLeftAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomRightFrame2.png", "youTopLeftAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftAndBottomRightFrame3.png", "youTopLeftAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftAndRightAndBottomLeftFrame1.png", "youTopLeftAndRightAndBottomLeftFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftAndRightAndBottomLeftFrame2.png", "youTopLeftAndRightAndBottomLeftFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftAndRightAndBottomLeftFrame3.png", "youTopLeftAndRightAndBottomLeftFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftAndRightFrame1.png", "youTopLeftAndRightFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftAndRightFrame2.png", "youTopLeftAndRightFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftAndRightFrame3.png", "youTopLeftAndRightFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomFrame1.png", "youTopLeftAndTopRightAndBottomFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomFrame2.png", "youTopLeftAndTopRightAndBottomFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomFrame3.png", "youTopLeftAndTopRightAndBottomFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomLeftAndBottomRightFrame1.png", "youTopLeftAndTopRightAndBottomLeftAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomLeftAndBottomRightFrame2.png", "youTopLeftAndTopRightAndBottomLeftAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomLeftAndBottomRightFrame3.png", "youTopLeftAndTopRightAndBottomLeftAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomLeftFrame1.png", "youTopLeftAndTopRightAndBottomLeftFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomLeftFrame2.png", "youTopLeftAndTopRightAndBottomLeftFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomLeftFrame3.png", "youTopLeftAndTopRightAndBottomLeftFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomRightFrame1.png", "youTopLeftAndTopRightAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomRightFrame2.png", "youTopLeftAndTopRightAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightAndBottomRightFrame3.png", "youTopLeftAndTopRightAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightFrame1.png", "youTopLeftAndTopRightFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightFrame2.png", "youTopLeftAndTopRightFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftAndTopRightFrame3.png", "youTopLeftAndTopRightFrame3");
        assetManager.loadImage("assets/images/you/youTopLeftFrame1.png", "youTopLeftFrame1");
        assetManager.loadImage("assets/images/you/youTopLeftFrame2.png", "youTopLeftFrame2");
        assetManager.loadImage("assets/images/you/youTopLeftFrame3.png", "youTopLeftFrame3");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomFrame1.png", "youTopRightAndBottomFrame1");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomFrame2.png", "youTopRightAndBottomFrame2");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomFrame3.png", "youTopRightAndBottomFrame3");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomLeftAndBottomRightFrame1.png", "youTopRightAndBottomLeftAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomLeftAndBottomRightFrame2.png", "youTopRightAndBottomLeftAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomLeftAndBottomRightFrame3.png", "youTopRightAndBottomLeftAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomLeftFrame1.png", "youTopRightAndBottomLeftFrame1");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomLeftFrame2.png", "youTopRightAndBottomLeftFrame2");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomLeftFrame3.png", "youTopRightAndBottomLeftFrame3");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomRightFrame1.png", "youTopRightAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomRightFrame2.png", "youTopRightAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youTopRightAndBottomRightFrame3.png", "youTopRightAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youTopRightAndLeftAndBottomRightFrame1.png", "youTopRightAndLeftAndBottomRightFrame1");
        assetManager.loadImage("assets/images/you/youTopRightAndLeftAndBottomRightFrame2.png", "youTopRightAndLeftAndBottomRightFrame2");
        assetManager.loadImage("assets/images/you/youTopRightAndLeftAndBottomRightFrame3.png", "youTopRightAndLeftAndBottomRightFrame3");
        assetManager.loadImage("assets/images/you/youTopRightAndLeftFrame1.png", "youTopRightAndLeftFrame1");
        assetManager.loadImage("assets/images/you/youTopRightAndLeftFrame2.png", "youTopRightAndLeftFrame2");
        assetManager.loadImage("assets/images/you/youTopRightAndLeftFrame3.png", "youTopRightAndLeftFrame3");
        assetManager.loadImage("assets/images/you/youTopRightFrame1.png", "youTopRightFrame1");
        assetManager.loadImage("assets/images/you/youTopRightFrame2.png", "youTopRightFrame2");
        assetManager.loadImage("assets/images/you/youTopRightFrame3.png", "youTopRightFrame3");
        assetManager.loadImage("assets/images/tile/buildings.png", "buildings");
        assetManager.loadImage("assets/images/tile/forest.png", "forest");
        assetManager.loadImage("assets/images/tile/mountain.png", "mountain");
        assetManager.loadImage("assets/images/tile/suburbs.png", "suburbs");
        assetManager.loadImage("assets/images/organs/brain.png", "brain");
        assetManager.loadImage("assets/images/organs/heartFrame1.png", "heartFrame1");
        assetManager.loadImage("assets/images/organs/heartFrame2.png", "heartFrame2");
        assetManager.loadImage("assets/images/organs/heartFrame3.png", "heartFrame3");
        assetManager.loadImage("assets/images/organs/heartFrame4.png", "heartFrame4");
        assetManager.loadImage("assets/images/organs/lungFrame1.png", "lungFrame1");
        assetManager.loadImage("assets/images/organs/lungFrame2.png", "lungFrame2");
        assetManager.loadImage("assets/images/organs/lungFrame3.png", "lungFrame3");
        assetManager.loadImage("assets/images/organs/lungFrame4.png", "lungFrame4");
        assetManager.loadImage("assets/images/organs/organBackground.png", "organBackground");
        assetManager.loadImage("assets/images/organs/pancreasFrame1.png", "pancreasFrame1");
        assetManager.loadImage("assets/images/organs/pancreasFrame2.png", "pancreasFrame2");
        assetManager.loadImage("assets/images/organs/pancreasFrame3.png", "pancreasFrame3");
        assetManager.loadImage("assets/images/organs/pancreasFrame4.png", "pancreasFrame4");
        assetManager.loadImage("assets/images/organs/stomach.png", "stomach");
        assetManager.loadImage("assets/images/gui/icon.png", "icon");
        assetManager.loadImage("assets/images/gui/mainMenu.png", "mainMenu");
        assetManager.loadImage("assets/images/gui/organToolTipBackground.png", "organToolTipBackground");
        assetManager.loadImage("assets/images/gui/separator.png", "separator");
        assetManager.loadImage("assets/images/gui/transition.png", "transition");
        assetManager.loadImage("assets/images/gui/upgradeDown.png", "upgradeDown");
        assetManager.loadImage("assets/images/gui/upgradeHover.png", "upgradeHover");
        assetManager.loadImage("assets/images/gui/upgradeUp.png", "upgradeUp");
        assetManager.loadImage("assets/images/gui/win.png", "win");
        assetManager.loadImage("assets/images/army/armyAttackDownFrame1.png", "armyAttackDownFrame1");
        assetManager.loadImage("assets/images/army/armyAttackDownFrame2.png", "armyAttackDownFrame2");
        assetManager.loadImage("assets/images/army/armyAttackDownFrame3.png", "armyAttackDownFrame3");
        assetManager.loadImage("assets/images/army/armyAttackDownFrame4.png", "armyAttackDownFrame4");
        assetManager.loadImage("assets/images/army/armyAttackLeftFrame1.png", "armyAttackLeftFrame1");
        assetManager.loadImage("assets/images/army/armyAttackLeftFrame2.png", "armyAttackLeftFrame2");
        assetManager.loadImage("assets/images/army/armyAttackLeftFrame3.png", "armyAttackLeftFrame3");
        assetManager.loadImage("assets/images/army/armyAttackLeftFrame4.png", "armyAttackLeftFrame4");
        assetManager.loadImage("assets/images/army/armyAttackRightFrame1.png", "armyAttackRightFrame1");
        assetManager.loadImage("assets/images/army/armyAttackRightFrame2.png", "armyAttackRightFrame2");
        assetManager.loadImage("assets/images/army/armyAttackRightFrame3.png", "armyAttackRightFrame3");
        assetManager.loadImage("assets/images/army/armyAttackRightFrame4.png", "armyAttackRightFrame4");
        assetManager.loadImage("assets/images/army/armyAttackUpFrame1.png", "armyAttackUpFrame1");
        assetManager.loadImage("assets/images/army/armyAttackUpFrame2.png", "armyAttackUpFrame2");
        assetManager.loadImage("assets/images/army/armyAttackUpFrame3.png", "armyAttackUpFrame3");
        assetManager.loadImage("assets/images/army/armyAttackUpFrame4.png", "armyAttackUpFrame4");
        assetManager.loadImage("assets/images/army/armyWalkDown.png", "armyWalkDown");
        assetManager.loadImage("assets/images/army/armyWalkLeft.png", "armyWalkLeft");
        assetManager.loadImage("assets/images/army/armyWalkRight.png", "armyWalkRight");
        assetManager.loadImage("assets/images/army/armyWalkUp.png", "armyWalkUp");
        assetManager.loadImage("assets/images/story/panel1.png", "panel1");
        assetManager.loadImage("assets/images/story/panel2.png", "panel2");
        assetManager.loadImage("assets/images/story/panel3.png", "panel3");
        assetManager.loadImage("assets/images/story/panel4.png", "panel4");
        assetManager.loadImage("assets/images/story/panel5.png", "panel5");
        assetManager.loadImage("assets/images/story/panel6.png", "panel6");
        assetManager.loadImage("assets/images/story/panel7.png", "panel7");
        assetManager.loadImage("assets/images/story/panel8.png", "panel8");
        assetManager.loadImage("assets/images/story/panel9.png", "panel9");
        assetManager.loadImage("assets/images/story/panel10.png", "panel10");
        assetManager.loadImage("assets/images/gui/howTo1.png", "howTo1");
        assetManager.loadImage("assets/images/gui/howTo2.png", "howTo2");
        assetManager.loadSoundEffect("assets/sounds/brain.wav", "brain");
        assetManager.loadSoundEffect("assets/sounds/die.wav", "die");
        assetManager.loadSoundEffect("assets/sounds/grow.wav", "grow");
        assetManager.loadSoundEffect("assets/sounds/heart.wav", "heart");
        assetManager.loadSoundEffect("assets/sounds/lung.wav", "lung");
        assetManager.loadSoundEffect("assets/sounds/pancreas.wav", "pancreas");
        assetManager.loadSoundEffect("assets/sounds/stomach.wav", "stomach");
        assetManager.loadSoundEffect("assets/sounds/upgrade.wav", "upgrade");
        assetManager.loadSoundEffect("assets/sounds/winState.wav", "winState");

    }

    public static void generateLoaderCode() {
        try {
            File f = new File("loadercode.txt");
            PrintWriter pw = new PrintWriter(f);
            generateLinesFor(pw, "assets/images/you", "loadImage");
            generateLinesFor(pw, "assets/images/tile", "loadImage");
            generateLinesFor(pw, "assets/images/organs", "loadImage");
            generateLinesFor(pw, "assets/images/gui", "loadImage");
            generateLinesFor(pw, "assets/images/army", "loadImage");
            generateLinesFor(pw, "assets/sounds", "loadSoundEffect");
            pw.flush();
            pw.close();
        } catch (IOException e) {
            System.err.println("Could not generate loader code");
        }
    }

    public static void generateLinesFor(PrintWriter pw, String pathToDir, String loadFunction) {
        File[] files = new File(pathToDir).listFiles();
        for (int i = 0; i < files.length; i++) {
            pw.println("assetManager." + loadFunction + "(\"" + pathToDir + "/" + files[i].getName() + "\", \"" + files[i].getName().split(Pattern.quote("."))[0] + "\");");
        }
    }
}
