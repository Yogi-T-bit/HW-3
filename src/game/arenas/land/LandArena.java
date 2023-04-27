package game.arenas.land;

import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.land.Bicycle;
import game.racers.land.Car;
import game.racers.land.Horse;
import utilities.EnumContainer.Coverage;
import utilities.EnumContainer.LandSurface;

/**
 * @author: Gil Cohen - 314800558, Yogev Tamir - 203762216
 *
 *  * That class extends from arena class, building all the variable that feet's the Land arena class
 *  * Every arena class will override the method add racer (this method will add new racer only if the racer's type feet's the arena
 *  * And if the arena is not full
 *  */
public class LandArena extends Arena {
    static final double DEFAULT_FRICTION = 0.5;
    static final int DEFAULT_MAX_RACERS = 8;
    final static int DEFAULT_LENGTH = 800;
    Coverage coverage = Coverage.GRASS;
    LandSurface landSurface = LandSurface.FLAT;

    public LandArena() {
        super(DEFAULT_LENGTH, DEFAULT_MAX_RACERS, DEFAULT_FRICTION);
    }

    /**
     * @param length set the length
     * @param maxRacers set the max racers
     */
    public LandArena(double length, int maxRacers) {
        super(length, maxRacers, DEFAULT_FRICTION);
    }

    /**
     * @return return the coverage
     */
    public Coverage getCoverage() {
        return coverage;
    }

    /**
     * @param coverage set the coverage
     */
    public void setCoverage(Coverage coverage) {
        this.coverage = coverage;
    }

    /**
     * @return return the land surface
     */
    public LandSurface getLandSurface() {
        return landSurface;
    }

    /**
     * @param landSurface set the land surface
     */
    public void setLandSurface(LandSurface landSurface) {
        this.landSurface = landSurface;
    }

    /**
     * @param newRacer racer var chosen by the user
     * @throws RacerLimitException racer limit exception
     * @throws RacerTypeException racer type exception
     */
    @Override
    public void addRacer(Racer newRacer) throws RacerLimitException, RacerTypeException {

        if (!(newRacer instanceof Car) && !(newRacer instanceof Bicycle) && !(newRacer instanceof Horse)) {
            throw new RacerTypeException("Invalid Racer of type \"" + newRacer.getClass().getSimpleName() + "\" for Land arena.");
        }
        if (super.getActiveRacers().size() >= super.getMAX_RACERS()) {
            throw new RacerLimitException(super.getMAX_RACERS(), newRacer.getSerialNumber());
        }
        super.getActiveRacers().add(newRacer);
    }
}

