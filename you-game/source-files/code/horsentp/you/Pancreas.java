/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

/**
 *
 * @author Jonathon
 */
public class Pancreas extends Organ {
    
    private float digestCaloriesPerSecond = 3000;
    private float digestVitaminHPerSecond = 150;
    private float digestVitaminXPerSecond = 60;
    private float digestCaloriesPerClick = 200;
    private float digestVitaminHPerClick = 50;
    private float digestVitaminXPerClick = 20;
    
    public Pancreas(You you) {
        super(you);
        setCaloriesForUpgrade(1000);
        setVitaminHForUpgrade(1000);
        setVitaminXForUpgrade(0);
    }

    @Override
    public void clickedOn() {
        if (getYou().getStomach().getCalories() > 0) {
            getYou().getStomach().digestCalories(digestCaloriesPerClick);
        }
        if (getYou().getStomach().getVitaminH() > 0) {
            getYou().getStomach().digestVitaminH(digestVitaminHPerClick);
        }
        if (getYou().getStomach().getVitaminX() > 0) {
            getYou().getStomach().digestVitaminX(digestVitaminXPerClick);
        }
        SoundBox.getSoundBox().playPancreasSound();
    }

    @Override
    public void upgradeClick() {
        super.upgradeClick();
        SoundBox.getSoundBox().playPancreasSound();
    }

    
    
    @Override
    public void upgrade(int newLevel) {
        if (newLevel == 2) {
            setCaloriesForUpgrade(2000);
            setVitaminHForUpgrade(200);
            setVitaminXForUpgrade(50);
            digestCaloriesPerClick = 300;
            digestVitaminHPerClick = 70;
            digestVitaminXPerClick = 30;
            digestCaloriesPerSecond = digestCaloriesPerSecond*1.3f;
            digestVitaminHPerSecond = digestVitaminHPerSecond*1.3f;
            digestVitaminXPerSecond = digestVitaminXPerSecond*1.3f;
            SoundBox.getSoundBox().playUpgradeSound();
        } else if (newLevel == 3) {
            setCaloriesForUpgrade(0);
            setVitaminHForUpgrade(0);
            setVitaminXForUpgrade(0);
            digestCaloriesPerClick = 400;
            digestVitaminHPerClick = 80;
            digestVitaminXPerClick = 40;
            digestCaloriesPerSecond = digestCaloriesPerSecond*1.3f;
            digestVitaminHPerSecond = digestVitaminHPerSecond*1.3f;
            digestVitaminXPerSecond = digestVitaminXPerSecond*1.3f;
            SoundBox.getSoundBox().playUpgradeSound();
        }
    }

    public float getDigestCaloriesPerSecond() {
        return digestCaloriesPerSecond;
    }

    public float getDigestVitaminHPerSecond() {
        return digestVitaminHPerSecond;
    }

    public float getDigestVitaminXPerSecond() {
        return digestVitaminXPerSecond;
    }
    
    
}
