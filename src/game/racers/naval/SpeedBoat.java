package game.racers.naval;

import game.racers.Racer;
import utilities.EnumContainer.BoatType;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Team;

import javax.swing.*;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 *
 * This class extends from racer and implements form naval racer witch means he belongs to the naval group
 *  * The class contains some vars that feet's her conditions
 */
public class SpeedBoat extends Racer implements NavalRacer {
    static final String CLASS_NAME = "SpeedBoat";
    static final double DEFAULT_MAX_SPEED = 170;
    static final double DEFAULT_ACCELERATION = 5;
    static final Color DEFAULT_color = Color.RED;
    BoatType boatType = BoatType.SKULLING;
    Team team = Team.DOUBLE;

    public SpeedBoat() {
        super(null, DEFAULT_MAX_SPEED, DEFAULT_ACCELERATION, DEFAULT_color);
    }

    /**
     * @param name name var by the user choice
     * @param maxSpeed max speed var by the user choice
     * @param acceleration acceleration by the user choice
     * @param color color var by the user choice
     */
    public SpeedBoat(String name, double maxSpeed, double acceleration, Color color) {
        super(name, maxSpeed, acceleration, color);

    }
    /**
     * @return return the boat type var
     */
    public BoatType getBoatType() {
        return boatType;
    }
    /**
     * @param boatType set the boat type var
     */
    public void setBoatType(BoatType boatType) {
        this.boatType = boatType;
    }
    /**
     * @return return the team var
     */
    public Team getTeam() {
        return team;
    }
    /**
     * @param team set the team var
     */
    public void setTeam(Team team) {
        this.team = team;
    }
    /**
     * @return return some info about the racer boat
     */
    @Override
    public String describeSpecific() {
        return " maxSpeed: " + this.getMaxSpeed() + ", acceleration: " + this.getAcceleration() + ", color: " + super.getColor() + ", Type: " + boatType + ", Team: " + team;
    }
    /**
     * @return return the class name
     */
    @Override
    public String className() {
        return CLASS_NAME;
    }
}
