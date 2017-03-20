/*
 * Created by Jonathon Prehn for Ludum Dare 33
 */
package horsentp.you;

/**
 *
 * @author Jonathon
 */
public class Cities {
    
    public static int currentCity = 1;
    
    public static String getPath(int city) {
        return "city" + city + ".txt";
    }
    
    public static int getToWin(int city) {
        switch(city) {
            case 1:
                return 43;
            case 2:
                return 91;
            case 3:
                return 200;
            case 4:
                return 350;
        }
        return -1;
    }
    
    public static int getCurrentCity() {
        return currentCity;
    }
    
    public static void nextCity() {
        currentCity++;
    }
    
    public static boolean won() {
        return currentCity>4;
    }
    
    //How many seconds between army spawn
    public static int getArmySpawnSpeed(int city) {
        switch(city) {
            case 1:
                return 8000;
            case 2:
                return 5500;
            case 3:
                return 4000;
            case 4:
                return 3000;
        }
        return -1;
    }
}
