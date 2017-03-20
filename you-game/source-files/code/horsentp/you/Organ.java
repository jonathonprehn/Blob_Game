/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

/**
 *
 * @author Jonathon
 */
public abstract class Organ {

    private int level = 1;
    private int caloriesForUpgrade = 5000;
    private int vitaminHForUpgrade = 1000;
    private int vitaminXForUpgrade = 500;
    private int caloriesContributedToUpgrade = 0;
    private int vitaminHContributedToUpgrade = 0;
    private int vitaminXContributedToUpgrade = 0;
    private You you;

    public Organ(You you) {
        this.you = you;
    }

    public You getYou() {
        return you;
    }
    
    

    public abstract void clickedOn();

    public void upgradeClick() {
        int diff = 0;
        if (caloriesContributedToUpgrade < caloriesForUpgrade && caloriesForUpgrade != 0 && you.hasEnoughCalories(you.getUpgradeCaloriesPerClick())) {
            caloriesContributedToUpgrade += you.getUpgradeCaloriesPerClick();
            if (caloriesContributedToUpgrade > caloriesForUpgrade) {
                diff = caloriesContributedToUpgrade - caloriesForUpgrade;
                caloriesContributedToUpgrade -= diff;
            }
            you.useCalories(you.getUpgradeCaloriesPerClick() - diff);
        }
        if (vitaminHContributedToUpgrade < vitaminHForUpgrade && vitaminHForUpgrade != 0 && you.hasEnoughVitaminH(you.getUpgradeVitaminHPerClick())) {
            vitaminHContributedToUpgrade += you.getUpgradeVitaminHPerClick();
            if (vitaminHContributedToUpgrade > vitaminHForUpgrade) {
                diff = vitaminHContributedToUpgrade - vitaminHForUpgrade;
                vitaminHContributedToUpgrade -= diff;
            }
            you.useVitaminH(you.getUpgradeVitaminHPerClick() - diff);
        }
        if (vitaminXContributedToUpgrade < vitaminXForUpgrade && vitaminXForUpgrade != 0 && you.hasEnoughVitaminX(you.getUpgradeVitaminXPerClick())) {
            vitaminXContributedToUpgrade += you.getUpgradeVitaminXPerClick();
            if (vitaminXContributedToUpgrade > vitaminXForUpgrade) {
                diff = vitaminXContributedToUpgrade - vitaminXForUpgrade;
                vitaminXContributedToUpgrade -= diff;
            }
            you.useVitaminX(you.getUpgradeVitaminXPerClick() - diff);
        }
        if ((caloriesContributedToUpgrade >= caloriesForUpgrade || caloriesForUpgrade == 0)
                && (vitaminHContributedToUpgrade >= vitaminHForUpgrade || vitaminHForUpgrade == 0)
                && (vitaminXContributedToUpgrade >= vitaminXForUpgrade || vitaminXForUpgrade == 0)
                && (isBrain() || level < you.getBrain().getLevel())) {
            caloriesContributedToUpgrade = 0;
            vitaminHContributedToUpgrade = 0;
            vitaminXContributedToUpgrade = 0;
            level++;
            upgrade(level);
        }
        updateUpgradePercents();
    }

    public void updateUpgradePercents() {
        if (caloriesForUpgrade > 0) {
            you.getYouGameState().setCalorieUpgradePercent((float) caloriesContributedToUpgrade / (float) caloriesForUpgrade);
        } else {
            you.getYouGameState().setCalorieUpgradePercent(1);
        }
        if (vitaminHForUpgrade > 0) {
            you.getYouGameState().setVitaminHUpgradePercent((float) vitaminHContributedToUpgrade / (float) vitaminHForUpgrade);
        } else {
            you.getYouGameState().setVitaminHUpgradePercent(1);
        }
        if (vitaminXForUpgrade > 0) {
            you.getYouGameState().setVitaminXUpgradePercent((float) vitaminXContributedToUpgrade / (float) vitaminXForUpgrade);
        } else {
            you.getYouGameState().setVitaminXUpgradePercent(1);
        }
    }

    public abstract void upgrade(int newLevel);

    public int getLevel() {
        return level;
    }
    
    public boolean isBrain() {
        return false;
    }

    public int getCaloriesForUpgrade() {
        return caloriesForUpgrade;
    }

    public void setCaloriesForUpgrade(int caloriesForUpgrade) {
        this.caloriesForUpgrade = caloriesForUpgrade;
    }

    public int getVitaminHForUpgrade() {
        return vitaminHForUpgrade;
    }

    public void setVitaminHForUpgrade(int vitaminHForUpgrade) {
        this.vitaminHForUpgrade = vitaminHForUpgrade;
    }

    public int getVitaminXForUpgrade() {
        return vitaminXForUpgrade;
    }

    public void setVitaminXForUpgrade(int vitaminXForUpgrade) {
        this.vitaminXForUpgrade = vitaminXForUpgrade;
    }

}
