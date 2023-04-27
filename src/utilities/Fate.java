package utilities;

import java.util.Random;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 *
 * This class is a helper class to mishap that re rolling numbers
 */
public class Fate {

    private static final Random rand = new Random();

    /**
     * @return return rolling true or false
     */
    public static boolean breakDown() {
        return rand.nextBoolean();
    }

    /**
     * @return rolling an int between 0-10 if its above 7 return true, alse return false
     */
    public static boolean generateFixable() {
        return rand.nextInt(10) > 7;
    }

    /**
     * @return return new mishap with random values
     */
    public static Mishap generateMishap() {
        return new Mishap(generateFixable(), generateTurns(), generateReduction());
    }

    /**
     * @return return a random float var
     */
    private static float generateReduction() {
        return rand.nextFloat();
    }

    /**
     * @return rolling an int var between 0-5 and return var+1
     */
    private static int generateTurns() {
        return rand.nextInt(5) + 1;
    }

    /**
     * @param seed a user choice para that set new seed
     */
    public static void setSeed(int seed) {
        rand.setSeed(seed);
    }

}
