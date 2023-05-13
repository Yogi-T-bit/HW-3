package game.racers.land;

import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer.Breed;
import utilities.EnumContainer.Color;

import javax.swing.*;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 *
 * This class extends from racer and implements form land racer witch means he belongs to the land group
 *  * The class contains some vars that feet's her conditions
 */
public class Horse extends Racer implements LandRacer {

    private static final String CLASS_NAME = "Horse";
    private static final double DEFAULT_MAX_SPEED = 50;
    private static final double DEFAULT_ACCELERATION = 3;
    private static final Color DEFAULT_color = Color.BLACK;
    Breed breed = Breed.THOROUGHBRED;

    /**
     * @return return the breed var
     */
    public Breed getBreed() {
        return breed;
    }

    /**
     * @param breed set the breed var
     */
    public void setBreed(Breed breed) {
        this.breed = breed;
    }


    public Horse() {
        super(null, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color,null);
    }

    /**
     * @param name name var set by the user
     * @param maxSpeed max speed var set by the user
     * @param acceleration acceleration var set by the user
     * @param color color var set by the user
     */
    public Horse(String name, double maxSpeed, double acceleration, Color color, Arena arena) {
        super(name, maxSpeed, acceleration, color,arena);
        this.introduce();

    }

    /**
     * @return return some info about the horse racer
     */
    @Override
    public String describeSpecific() {
        return " maxSpeed: " + this.getMaxSpeed() + ", acceleration: " + this.getAcceleration() + ", color: " + super.getColor() + ", Breed: " + breed;
    }

    /**
     * @return return the class name
     */
    @Override
    public String className() {
        return CLASS_NAME;
    }
}
