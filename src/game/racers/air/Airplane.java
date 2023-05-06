package game.racers.air;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.Color;

import javax.swing.*;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 * <p>
 * This class extends from racer and implements form air racer witch means he belongs to the air group
 * The class contains some vars that feet's her conditions
 */
public class Airplane extends Racer implements AirRacer {

    static final String CLASS_NAME = "Airplane";
    static final int DEFAULT_WHEELS = 3;
    static final double DEFAULT_MAX_SPEED = 885;
    static final double DEFAULT_ACCELERATION = 100;
    static final Color DEFAULT_color = Color.BLACK;
    Wheeled wheeled;

    public Airplane() {
        super(null, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color);
        wheeled = new Wheeled(DEFAULT_WHEELS);
    }

    /**
     * @param name         name var set by the user
     * @param maxSpeed     max speed var set by the user
     * @param acceleration acceleration var set by the user
     * @param color        color var set by the user
     * @param NumOfWheels  num of wheels var set by the user
     */
    public Airplane(String name, double maxSpeed, double acceleration, Color color, int NumOfWheels) {
        super(name, maxSpeed, acceleration, color);
        wheeled = new Wheeled(NumOfWheels);
    }

    /**
     * @return return some info about the airplane racer
     */
    @Override
    public String describeSpecific() {
        return " maxSpeed: " + this.getMaxSpeed() + ", acceleration: " + this.getAcceleration() + ", color: " + super.getColor() + ", Number of Wheels: " + wheeled.getWheels();
    }

    /**
     * @return return the class name
     */
    @Override
    public String className() {
        return CLASS_NAME;
    }
}
