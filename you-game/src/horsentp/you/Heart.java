/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

/**
 *
 * @author Jonathon
 */
public class Heart extends Organ {
    
    private float caloriesPerDurability = 30;
    private float durabilityPerSecond = 5;
    private float durabilityPerClick = 3;
    
    public Heart(You you) {
        super(you);
        setCaloriesForUpgrade(1000);
        setVitaminHForUpgrade(200);
        setVitaminXForUpgrade(0);
    }

    @Override
    public void clickedOn() {
        for (RepairArea ra : getYou().getRepairingAreas()) {
            ra.repair(durabilityPerClick/getYou().getRepairingAreas().size());
        }
        SoundBox.getSoundBox().playHeartSound();
    }

    @Override
    public void upgradeClick() {
        super.upgradeClick();
        SoundBox.getSoundBox().playHeartSound();
    }

    
    
    @Override
    public void upgrade(int newLevel) {
        if (newLevel == 2) {
            setCaloriesForUpgrade(13000);
            setVitaminHForUpgrade(1500);
            setVitaminXForUpgrade(0);
            caloriesPerDurability = 25;
            durabilityPerSecond = 7;
            durabilityPerClick = 6;
            SoundBox.getSoundBox().playUpgradeSound();
        } else if (newLevel == 3) {
            setCaloriesForUpgrade(0);
            setVitaminHForUpgrade(0);
            setVitaminXForUpgrade(0);
            caloriesPerDurability = 20;
            durabilityPerSecond = 9;
            durabilityPerClick = 9;
            SoundBox.getSoundBox().playUpgradeSound();
        }
    }

    public float getCaloriesPerDurability() {
        return caloriesPerDurability;
    }

    public float getDurabilityPerSecond() {
        return durabilityPerSecond;
    }

    public float getDurabilityPerClick() {
        return durabilityPerClick;
    }
    
    
}
