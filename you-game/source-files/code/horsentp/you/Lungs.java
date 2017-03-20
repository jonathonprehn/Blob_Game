/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

/**
 *
 * @author Jonathon
 */
public class Lungs extends Organ {
    
    private float caloriesPerCoverage = 10;
    private float coveragePerSecond = 50;
    private float coveragePerClick = 110;
    
    public Lungs(You you) {
        super(you);
        setCaloriesForUpgrade(1000);
        setVitaminHForUpgrade(150);
        setVitaminXForUpgrade(0);
    }

    @Override
    public void clickedOn() {
        for (GrowthArea ga : getYou().getGrowingAreas()) {
            ga.grow(coveragePerClick/getYou().getGrowingAreas().size());
            
        }
        SoundBox.getSoundBox().playLungSound();
    }

    @Override
    public void upgradeClick() {
        super.upgradeClick();
        SoundBox.getSoundBox().playLungSound();
    }
    
    

    @Override
    public void upgrade(int newLevel) {
        coveragePerClick = (coveragePerClick*1.5f);
        if (newLevel == 2) {
            coveragePerSecond = 500;
            caloriesPerCoverage = 4;
            setCaloriesForUpgrade(13000);
            setVitaminHForUpgrade(2500);
            setVitaminXForUpgrade(200);
            SoundBox.getSoundBox().playUpgradeSound();
        } else if (newLevel == 3) {
            caloriesPerCoverage = 3;
            coveragePerSecond = 700;
            setCaloriesForUpgrade(0);
            setVitaminHForUpgrade(0);
            setVitaminXForUpgrade(0);
            SoundBox.getSoundBox().playUpgradeSound();
        }
    }

    public float getCaloriesPerCoverage() {
        return caloriesPerCoverage;
    }

    public float getCoveragePerSecond() {
        return coveragePerSecond;
    }

    public float getCoveragePerClick() {
        return coveragePerClick;
    }

    public void setCaloriesPerCoverage(int caloriesPerCoverage) {
        this.caloriesPerCoverage = caloriesPerCoverage;
    }

    public void setCoveragePerSecond(int coveragePerSecond) {
        this.coveragePerSecond = coveragePerSecond;
    }

    public void setCoveragePerClick(int coveragePerClick) {
        this.coveragePerClick = coveragePerClick;
    }
    
    
}
