/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

/**
 *
 * @author Jonathon
 */
public class Stomach extends Organ {
    
    private float calories = 0;
    private float vitaminH = 0;
    private float vitaminX = 0;
    private float capacity = 30000;
    
    public Stomach(You you) {
        super(you);
        setCaloriesForUpgrade(1000);
        setVitaminHForUpgrade(0);
        setVitaminXForUpgrade(0);
    }

    @Override
    public void clickedOn() {
        SoundBox.getSoundBox().playStomachSound();
    }

    @Override
    public void upgradeClick() {
        super.upgradeClick();
        SoundBox.getSoundBox().playStomachSound();
    }    

    @Override
    public void upgrade(int newLevel) {
        if (newLevel == 2) {
            setCaloriesForUpgrade(1000);
            setVitaminHForUpgrade(500);
            setVitaminXForUpgrade(100);
            capacity = 30000*2.5f;
            SoundBox.getSoundBox().playUpgradeSound();
        } else if (newLevel == 3) {
            setCaloriesForUpgrade(0);
            setVitaminHForUpgrade(0);
            setVitaminXForUpgrade(0);
            capacity = 30000*3.5f;
            SoundBox.getSoundBox().playUpgradeSound();
        }
    }

    public float getCalories() {
        return calories;
    }

    public float getVitaminH() {
        return vitaminH;
    }

    public float getVitaminX() {
        return vitaminX;
    }

    public float getCapacity() {
        return capacity;
    }
    
    public boolean hasSpace() {
        return capacity > calories+vitaminH+vitaminX;
    }
    
    public float getOverflow(float amountToAdd) {
        float overflow = (calories+vitaminH+vitaminX+amountToAdd)-capacity;
        if (overflow>0) {
            return overflow;
        } else {
            return 0;
        }
    }
    
    public void eatCalories(float cal) {
        calories += cal;
    }
    
    public void eatVitaminH(float v) {
        vitaminH += v;
    }
    
    public void eatVitaminX(float v) {
        vitaminX += v;
    }
    
    public void digestCalories(float cal) {
        calories -= cal;
    }
    
    public void digestVitaminH(float v) {
        vitaminH -= v;
    }
    
    public void digestVitaminX(float v) {
        vitaminX -= v;
    }
}
