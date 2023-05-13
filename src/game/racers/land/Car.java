package game.racers.land;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Engine;

import javax.swing.*;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216\
 *
 * This class extends from racer and implements form land racer witch means he belongs to the land group
 *  * The class contains some vars that feet's her conditions
 */
public class Car extends Racer implements LandRacer {

    static final String CLASS_NAME = "Car";
    static final int DEFAULT_WHEELS = 4;
    static final double DEFAULT_MAX_SPEED = 400;
    static final double DEFAULT_ACCELERATION = 20;
    static final Color DEFAULT_color = Color.RED;
    private Wheeled wheeled;
    private Engine engine = Engine.FOURSTROKE;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Car() {
        super(CLASS_NAME, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color,null);
        wheeled = new Wheeled(DEFAULT_WHEELS);
    }

    /**
     * @param name name var set by the user
     * @param maxSpeed max speed var set by the user
     * @param acceleration acceleration var set by the user
     * @param color color var set by the user
     * @param NumOfWheels num of wheels var set by the user
     */
    public Car(String name, double maxSpeed, double acceleration, Color color, int NumOfWheels, Arena arena) {
        super(name, maxSpeed, acceleration, color,arena);
        wheeled = new Wheeled(NumOfWheels);
        this.introduce();

    }

    /**
     * @return return some info about the car
     */
    @Override
    public String describeSpecific() {
        return " maxSpeed: " + this.getMaxSpeed() + ", acceleration: " + this.getAcceleration() + ", color: " + super.getColor() + ", Number of Wheels: " + wheeled.getWheels() + ", Engine Type: " + engine;
    }

    /**
     * @return return the class name
     */
    @Override
    public String className() {
        return CLASS_NAME;
    }
}
