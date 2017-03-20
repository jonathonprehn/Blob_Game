/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

import java.util.ArrayList;

/**
 * The class that represents you!
 *
 * @author Jonathon
 */
public class You {

    /**
     * The calories that you can use.
     */
    private float calories = 1000;
    /**
     * The vitamin H that you can use.
     */
    private float vitaminH = 100;
    /**
     * The vitamin X that you can use.
     */
    private float vitaminX = 20;
    /**
     * The areas that you are growing in.
     */
    private final ArrayList<GrowthArea> growingAreas = new ArrayList<>();
    /**
     * The areas that you are repairing.
     */
    private final ArrayList<RepairArea> repairingAreas = new ArrayList<>();
    /**
     * The areas that you are in.
     */
    private final ArrayList<YouArea> youAreas = new ArrayList<>();
    /**
     * The lungs.
     */
    private final Lungs lungs;
    /**
     * The heart.
     */
    private final Heart heart;
    /**
     * The rate you eat calories from tiles.
     */
    private final int eatCaloriesPerSecond = 1000;
    /**
     * The rate you eat vitamin H from tiles.
     */
    private final int eatVitaminHPerSecond = 50;
    /**
     * The rate you eat vitamin X from tiles.
     */
    private final int eatVitaminXPerSecond = 20;
    /**
     * The stomach.
     */
    private final Stomach stomach;
    /**
     * The pancreas.
     */
    private final Pancreas pancreas;
    /**
     * How many calories you contribute to upgrading an organ with each click.
     */
    private final int upgradeCaloriesPerClick = 100;
    /**
     * How many calories you contribute to upgrading an organ with each click.
     */
    private final int upgradeVitaminHPerClick = 50;
    /**
     * How many calories you contribute to upgrading an organ with each click.
     */
    private final int upgradeVitaminXPerClick = 25;
    /**
     * The brain.
     */
    private final Brain brain;

    private final YouGameState youGameState;

    public You(YouGameState youGameState) {
        this.youGameState = youGameState;
        brain = new Brain(this);
        heart = new Heart(this);
        lungs = new Lungs(this);
        pancreas = new Pancreas(this);
        stomach = new Stomach(this);
    }

    public YouGameState getYouGameState() {
        return youGameState;
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

    public ArrayList<GrowthArea> getGrowingAreas() {
        return growingAreas;
    }

    public ArrayList<RepairArea> getRepairingAreas() {
        return repairingAreas;
    }

    public ArrayList<YouArea> getYouAreas() {
        return youAreas;
    }

    public Lungs getLungs() {
        return lungs;
    }

    public Heart getHeart() {
        return heart;
    }

    public int getEatCaloriesPerSecond() {
        return eatCaloriesPerSecond;
    }

    public int getEatVitaminHPerSecond() {
        return eatVitaminHPerSecond;
    }

    public int getEatVitaminXPerSecond() {
        return eatVitaminXPerSecond;
    }

    public Stomach getStomach() {
        return stomach;
    }

    public Pancreas getPancreas() {
        return pancreas;
    }

    public int getUpgradeCaloriesPerClick() {
        return upgradeCaloriesPerClick;
    }

    public int getUpgradeVitaminHPerClick() {
        return upgradeVitaminHPerClick;
    }

    public int getUpgradeVitaminXPerClick() {
        return upgradeVitaminXPerClick;
    }

    public Brain getBrain() {
        return brain;
    }

    public void addCalories(float c) {
        calories += c;
    }

    public void addVitaminH(float v) {
        vitaminH += v;
    }

    public void addVitaminX(float v) {
        vitaminX += v;
    }

    public void useCalories(float c) {
        calories -= c;
    }

    public void useVitaminH(float v) {
        vitaminH -= v;
    }

    public void useVitaminX(float v) {
        vitaminX -= v;
    }

    public boolean hasEnoughCalories(float cost) {
        return calories >= cost;
    }

    public boolean hasEnoughVitaminH(float cost) {
        return vitaminH >= cost;
    }

    public boolean hasEnoughVitaminX(float cost) {
        return vitaminX >= cost;
    }

    public void startGrowingOn(Tile tile, Tile fromTile, YouEdgeManager edgeManager) {
        if (canGrowOn(tile)) {
            GrowthArea ga = new GrowthArea(tile, fromTile);
            growingAreas.add(ga);
            edgeManager.refreshEdges();
            SoundBox.getSoundBox().playGrowSound();
        }
    }

    public boolean canGrowOn(Tile tile) {
        if (getMatchingGrowthArea(tile) == null && tile.getCoverage() > 0) {
            for (YouArea y : youAreas) {
                if (y.getTile().isAdjacentNoDiagonal(tile)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void startRepairing(YouArea area) {
        repairingAreas.add(new RepairArea(area));
    }

    public void finishGrowth(GrowthArea ga) {
        growingAreas.remove(ga);
    }

    public void finishRepair(RepairArea ra) {
        repairingAreas.remove(ra);
    }

    /**
     * Update the growing areas.
     *
     * @param seconds how many seconds this frame is.
     */
    public void updateGrowing(float seconds, YouEdgeManager edgeManager) {
        float cals;
        for (int i = 0; i < growingAreas.size(); i++) {
            if (hasEnoughCalories(cals = lungs.getCoveragePerSecond() * lungs.getCaloriesPerCoverage() * seconds)) {
                float coverage = lungs.getCoveragePerSecond() * seconds;
                if (!growingAreas.get(i).isAlmost() && growingAreas.get(i).willBeAlmost(coverage)) {
                    growingAreas.get(i).grow(coverage);
                    getYouGameState().getYouEdgeManager().refreshEdges();
                } else if (!growingAreas.get(i).isLots() && growingAreas.get(i).willBeLots(coverage)) {
                    growingAreas.get(i).grow(coverage);
                    getYouGameState().getYouEdgeManager().refreshEdges();
                } else {
                    growingAreas.get(i).grow(coverage);
                }
                useCalories(cals);
            }
            if (growingAreas.get(i).fullyCovered()) {
                //Take control of tile
                takeControlOf(growingAreas.get(i).getTile());
                finishGrowth(growingAreas.get(i));
                edgeManager.refreshEdges();
                i--;
            }
        }
    }

    public void takeControlOf(Tile tile) {
        YouArea you = new YouArea(tile);
        youAreas.add(you);
        SoundBox.getSoundBox().playGrowSound();
        if (youAreas.size() == youGameState.getToWin()) {
            Cities.nextCity();
            if (Cities.won()) {
                youGameState.win();
            } else {
                youGameState.getGameStateRunner().setState(new TransitionState(Cities.getCurrentCity()));
            }
        }
        for (int i=0; i<youGameState.getArmies().size(); i++) {
            Army a = youGameState.getArmies().get(i);
            if (a.getOnTile().is(tile)) {
                i--;
                youGameState.getArmies().remove(a);
            }
        }
    }

    public void updateRepairing(float seconds, YouEdgeManager edgeManager) {
        int cals;
        for (int i = 0; i < repairingAreas.size(); i++) {
            if (hasEnoughCalories(cals = (int) (heart.getCaloriesPerDurability() * heart.getDurabilityPerSecond() * seconds))) {
                int durability = (int) (heart.getDurabilityPerSecond() * seconds);
                repairingAreas.get(i).repair(durability);
                useCalories(cals);
                if (repairingAreas.get(i).isRepaired()) {
                    repairingAreas.remove(repairingAreas.get(i));
                    i--;
                }
            }
        }
    }

    public void updateEating(float seconds, YouEdgeManager edgeManager) {
        for (YouArea ya : youAreas) {
            int calorieRate = eatCaloriesPerSecond;
            int vitaminHRate = eatVitaminHPerSecond;
            if (ya.getTile().hasVitaminX()) {
                if (stomach.hasSpace()) {
                    float v = eatVitaminXPerSecond * seconds;
                    float overflow = stomach.getOverflow(v);
                    if (overflow > 0) {
                        v -= overflow;
                    }
                    if (v > ya.getTile().getVitaminX()) {
                        v = ya.getTile().getVitaminX();
                    }
                    stomach.eatVitaminX(v);
                    ya.getTile().reduceVitaminX(v);
                }
            } else {
                vitaminHRate += eatVitaminXPerSecond;
            }
            if (ya.getTile().hasVitaminH()) {
                if (stomach.hasSpace()) {
                    float v = (vitaminHRate * seconds);
                    float overflow = stomach.getOverflow(v);
                    if (overflow > 0) {
                        v -= overflow;
                    }
                    if (v > ya.getTile().getVitaminH()) {
                        v = ya.getTile().getVitaminH();
                    }
                    stomach.eatVitaminH(v);
                    ya.getTile().reduceVitaminH(v);
                }
            } else {
                calorieRate += vitaminHRate;
            }
            if (ya.getTile().hasCalories() && stomach.hasSpace()) {
                float cals = (calorieRate * seconds);
                float overflow = stomach.getOverflow(cals);
                if (overflow > 0) {
                    cals -= overflow;
                }
                if (cals > ya.getTile().getCalories()) {
                    cals = ya.getTile().getCalories();
                }
                stomach.eatCalories(cals);
                ya.getTile().reduceCalories(cals);
            }
        }
    }

    public void updateDigesting(float seconds, YouEdgeManager edgeManager) {
        float calRate = pancreas.getDigestCaloriesPerSecond();
        float vhRate = pancreas.getDigestVitaminHPerSecond();
        if (stomach.getVitaminX() == 0) {
            vhRate += pancreas.getDigestVitaminXPerSecond();
        }
        if (stomach.getVitaminH() == 0) {
            calRate += vhRate;
        }
        float cals = (calRate * seconds);
        float vh =  (vhRate * seconds);
        float vx = (pancreas.getDigestVitaminXPerSecond() * seconds);
        if (stomach.getCalories() < cals) {
            cals = stomach.getCalories();
        }
        if (stomach.getVitaminH() < vh) {
            vh = stomach.getVitaminH();
        }
        if (stomach.getVitaminX() < vx) {
            vx = stomach.getVitaminX();
        }
        stomach.digestCalories(cals);
        stomach.digestVitaminH(vh);
        stomach.digestVitaminX(vx);
        addCalories(cals);
        addVitaminH(vh);
        addVitaminX(vx);
    }

    public void update(float seconds, YouEdgeManager edgeManager) {
        for (int i=0; i<youAreas.size(); i++) {
            if (youAreas.get(i).isDead()) {
                youAreas.remove(i);
                edgeManager.refreshEdges();
                SoundBox.getSoundBox().playDieSound();
                i--;
            }
        }
        updateEating(seconds, edgeManager);
        updateDigesting(seconds, edgeManager);
        updateRepairing(seconds, edgeManager);
        updateGrowing(seconds, edgeManager);
    }

    public GrowthArea getMatchingGrowthArea(Tile tile) {
        for (GrowthArea ga : growingAreas) {
            if (ga.getTile() == tile) {
                return ga;
            }
        }
        return null;
    }

    public RepairArea getMatchingRepairArea(Tile tile) {
        for (RepairArea ra : repairingAreas) {
            if (ra.getTile() == tile) {
                return ra;
            }
        }
        return null;
    }

    public YouArea getMatchingYouArea(Tile tile) {
        for (YouArea ya : youAreas) {
            if (ya.getTile() == tile) {
                return ya;
            }
        }
        return null;
    }

    public boolean isOn(int x, int y) {
        return getMatchingYouArea(getYouGameState().getCity().getTile(x, y)) != null;
    }

    public boolean isTopLeft(Tile tile) {
        return youGameState.getCity().tileExists(tile.getX() - 1, tile.getY() - 1) && youGameState.getYou().isOn(tile.getX() - 1, tile.getY() - 1);
    }

    public boolean isLeft(Tile tile) {
        return youGameState.getCity().tileExists(tile.getX() - 1, tile.getY()) && youGameState.getYou().isOn(tile.getX() - 1, tile.getY());
    }

    public boolean isTopRight(Tile tile) {
        return youGameState.getCity().tileExists(tile.getX() + 1, tile.getY() - 1) && youGameState.getYou().isOn(tile.getX() + 1, tile.getY() - 1);
    }

    public boolean isRight(Tile tile) {
        return youGameState.getCity().tileExists(tile.getX() + 1, tile.getY()) && youGameState.getYou().isOn(tile.getX() + 1, tile.getY());
    }

    public boolean isBottom(Tile tile) {
        return youGameState.getCity().tileExists(tile.getX(), tile.getY() + 1) && youGameState.getYou().isOn(tile.getX(), tile.getY() + 1);
    }

    public boolean isBottomRight(Tile tile) {
        return youGameState.getCity().tileExists(tile.getX() + 1, tile.getY() + 1) && youGameState.getYou().isOn(tile.getX() + 1, tile.getY() + 1);
    }

    public boolean isTop(Tile tile) {
        return youGameState.getCity().tileExists(tile.getX(), tile.getY() - 1) && youGameState.getYou().isOn(tile.getX(), tile.getY() - 1);
    }

    public boolean isBottomLeft(Tile tile) {
        return youGameState.getCity().tileExists(tile.getX() - 1, tile.getY() + 1) && youGameState.getYou().isOn(tile.getX() - 1, tile.getY() + 1);
    }
}
