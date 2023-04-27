package game.racers;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 *
 * This class in a helper class that help the racer class
 */
public class Wheeled {
    private int numOFWheels;

    /**
     * @param wheels number of wheels chosen by the user in the constructor
     */
    public Wheeled(int wheels) {
        this.numOFWheels = wheels;
    }

    Wheeled() {
    }

    /**
     * @return return the number of wheels
     */
    public int getWheels() {
        return numOFWheels;
    }

    /**
     * @param wheels number of wheels chosen by the user
     */
    public void setWheels(int wheels) {
        this.numOFWheels = wheels;
    }

    /**
     * @return return describe of number of wheels
     */
    public String describeSpecific() {
        return "Number of wheels: " + numOFWheels;
    }
}
