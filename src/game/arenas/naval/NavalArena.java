package game.arenas.naval;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.naval.RowBoat;
import game.racers.naval.SpeedBoat;
import utilities.EnumContainer.Body;
import utilities.EnumContainer.Water;
import utilities.EnumContainer.WaterSurface;

import java.util.Observable;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 * <p>
 * That class extends from arena class, building all the variable that feet's the Naval arena class
 * Every arena class will override the method add racer (this method will add new racer only if the racer's type feet's the arena
 * And if the arena is not full
 */
public class NavalArena extends Arena {
    static final double DEFAULT_FRICTION = 0.7;
    static final int DEFAULT_MAX_RACERS = 5;
    final static int DEFAULT_LENGTH = 1000;
    private Water water = Water.SWEET;
    private WaterSurface surface = WaterSurface.FLAT;
    private Body body = Body.LAKE;

    public NavalArena() {
        super(DEFAULT_LENGTH, DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
    }

    /**
     * @param length set the length
     * @param maxRacers set the max racers
     */
    public NavalArena(double length, int maxRacers) {
        super(length, maxRacers, DEFAULT_FRICTION);
    }

    /**
     * @return return the water
     */
    public Water getWater() {
        return water;
    }

    /**
     * @param water set the water
     */
    public void setWater(Water water) {
        this.water = water;
    }

    /**
     * @return return the surface
     */
    public WaterSurface getSurface() {
        return surface;
    }

    /**
     * @param surface set the surface
     */
    public void setSurface(WaterSurface surface) {
        this.surface = surface;
    }

    /**
     * @return return the body
     */
    public Body getBody() {
        return body;
    }

    /**
     * @param body set the body
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * @param newRacer racer var chosen by the user
     * @throws RacerLimitException racer limit exception
     * @throws RacerTypeException racer type exception
     */
    @Override
    public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {
        if (!(newRacer instanceof RowBoat) && !(newRacer instanceof SpeedBoat)) {
            throw new RacerTypeException("Invalid Racer of type \"" + newRacer.getClass().getSimpleName() + "\" for Naval arena.");
        }

        if (super.getActiveRacers().size() >= super.getMAX_RACERS()) {
            throw new RacerLimitException(super.getMAX_RACERS(), newRacer.getSerialNumber());
        }

        super.getActiveRacers().add(newRacer);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
