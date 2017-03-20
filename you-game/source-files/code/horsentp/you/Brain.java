/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

/**
 *
 * @author Jonathon
 */
public class Brain extends Organ {

    public Brain(You you) {
        super(you);
        setCaloriesForUpgrade(1000);
        setVitaminHForUpgrade(1000);
        setVitaminXForUpgrade(1000);
    }
    
    @Override
    public boolean isBrain() {
        return true;
    }

    @Override
    public void clickedOn() {
        SoundBox.getSoundBox().playBrainSound();
    }

    @Override
    public void upgradeClick() {
        super.upgradeClick(); 
        SoundBox.getSoundBox().playBrainSound();
    }
    
    

    @Override
    public void upgrade(int newLevel) {
        if (newLevel == 2) {
            setCaloriesForUpgrade(2000);
            setVitaminHForUpgrade(2000);
            setVitaminXForUpgrade(2000);
            SoundBox.getSoundBox().playUpgradeSound();
        } else if (newLevel == 3) {
            setCaloriesForUpgrade(0);
            setVitaminHForUpgrade(0);
            setVitaminXForUpgrade(0);
            SoundBox.getSoundBox().playUpgradeSound();
        }
    }
}
