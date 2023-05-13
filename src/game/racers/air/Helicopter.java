package game.racers.air;

import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer.Color;

import javax.swing.*;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 *
 * This class extends from racer and implements form air racer witch means he belongs to the air group
 *  * The class contains some vars that feet's her conditions
 */
public class Helicopter extends Racer implements AirRacer {
    static final String CLASS_NAME = "Helicopter";
    static final double DEFAULT_MAX_SPEED = 400;
    static final double DEFAULT_ACCELERATION = 50;
    static final Color DEFAULT_color = Color.BLUE;

    public Helicopter() {
        super(null, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color,null);
    }
    /**
     * @param name name var set by the user
     * @param maxSpeed max speed var set by the user
     * @param acceleration acceleration var set by the user
     * @param color color var set by the user
     */
    public Helicopter(String name, double maxSpeed, double acceleration, Color color, Arena arena) {
        super(name, maxSpeed, acceleration, color,arena);
        this.introduce();
    }

    /**
     * @return return some info about the heli copter racer
     */
    @Override
    public String describeSpecific() {
        return " maxSpeed: " + this.getMaxSpeed() + ", acceleration: " + this.getAcceleration() + ", color: " + super.getColor();
    }

    /**
     * @return return the class name
     */
    @Override
    public String className() {
        return CLASS_NAME;
    }
}
